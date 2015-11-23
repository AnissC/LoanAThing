package com.lat.forms;

import com.lat.beans.User;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public final class RegisterForm
{
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "motdepasse";
    private static final String CHAMP_VALID = "confirmation";
    private static final String CHAMP_NAME = "nom";

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

    public User processUser(HttpServletRequest request)
    {
        String email = getFieldValue(request, CHAMP_EMAIL);
        String password = getFieldValue(request, CHAMP_PASS);
        String valid = getFieldValue(request, CHAMP_VALID);
        String name = getFieldValue(request, CHAMP_NAME);

        User user = new User();

        try {
            checkEmail(email);
        } catch (Exception e) {
            setError(CHAMP_EMAIL, e.getMessage());
        }
        user.setEmail(email);

        try {
            checkPassword(password, valid);
        } catch (Exception e) {
            setError(CHAMP_PASS, e.getMessage());
            setError(CHAMP_VALID, null);
        }
        user.setPassword(password);

        try {
            checkName(name);
        } catch (Exception e) {
            setError(CHAMP_NAME, e.getMessage());
        }
        user.setName(name);

        if (errors.isEmpty()) {
            results = "Succès de l'inscription.";
        } else {
            results = "Échec de l'inscription.";
        }

        return user;
    }

    private void checkEmail(String email) throws Exception
    {
        if (email != null) {
            if (!email.matches("([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new Exception("Merci de saisir une adresse mail valide.");
            }
        } else {
            throw new Exception("Merci de saisir une adresse mail.");
        }
    }

    private void checkPassword(String password, String valid) throws Exception
    {
        if (password != null && valid != null) {
            if (!password.equals(valid)) {
                throw new Exception("Les mots de passe entrés sont différents, merci de les saisir à nouveau.");
            } else if (password.length() < 3) {
                throw new Exception("Les mots de passe doivent contenir au moins 3 caractères.");
            }
        } else {
            throw new Exception("Merci de saisir et confirmer votre mot de passe.");
        }
    }

    private void checkName(String name) throws Exception
    {
        if (name != null && name.length() < 3) {
            throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setError(String field, String message)
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
            return value.trim();
        }
    }
}
