package com.lat.forms;

import com.lat.beans.Users;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public final class LoginForm
{
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS  = "password";

    private String results;
    private Map<String, String> errors = new HashMap<String, String>();

    public String getResults()
    {
        return results;
    }

    public Map<String, String> getErrors()
    {
        return errors;
    }

    public Users connectUser(HttpServletRequest request)
    {
        /* Récupération des champs du formulaire */
        String email = getFieldValue(request, CHAMP_EMAIL);
        String password = getFieldValue(request, CHAMP_PASS);

        Users users = new Users();

        /* Validation du champ email. */
        try {
            checkEmail(email);
        } catch (Exception e) {
            setErrors(CHAMP_EMAIL, e.getMessage());
        }
        users.setEmail(email);

        /* Validation du champ mot de passe. */
        try {
            checkPassword(password);
        } catch (Exception e) {
            setErrors(CHAMP_PASS, e.getMessage());
        }
        users.setPassword(password);

        /* Initialisation du résultat global de la validation. */
        if (errors.isEmpty()) {
            results = "Succès de la connexion.";
        } else {
            results = "Échec de la connexion.";
        }

        return users;
    }

    /**
     * Valide l'adresse email saisie.
     */
    private void checkEmail(String email) throws Exception
    {
        if (email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" )) {
            throw new Exception("Merci de saisir une adresse mail valide.");
        }
    }

    /**
     * Valide le mot de passe saisi.
     */
    private void checkPassword(String password) throws Exception
    {
        if (password != null) {
            if (password.length() < 3) {
                throw new Exception("Le mot de passe doit contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir votre mot de passe.");
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setErrors(String field, String message)
    {
        errors.put(field, message);
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getFieldValue(HttpServletRequest request, String fieldName)
    {
        String value = request.getParameter(fieldName);
        if (value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value;
        }
    }
}