package com.lat.forms;

import com.lat.beans.Users;
import com.lat.dao.DAOException;
import com.lat.dao.UserDao;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

public final class RegisterForm
{
    private static final String CHAMP_EMAIL = "email";
    private static final String CHAMP_PASS = "password";
    private static final String CHAMP_VALID = "valid";
    private static final String CHAMP_NAME = "name";
    private static final String ALGO_CHIFFREMENT = "SHA-256";

    private UserDao userDao;

    private String results;
    private Map<String, String> errors = new HashMap<String, String>();

    public RegisterForm(UserDao userDao)
    {
        this.userDao = userDao;
    }

    public String getResults()
    {
        return results;
    }

    public Map<String, String> getErrors()
    {
        return errors;
    }

    public Users processUser(HttpServletRequest request)
    {
        String email = getFieldValue(request, CHAMP_EMAIL);
        String password = getFieldValue(request, CHAMP_PASS);
        String valid = getFieldValue(request, CHAMP_VALID);
        String name = getFieldValue(request, CHAMP_NAME);

        Users user = new Users();

        try {
            checkEmail(email, user);
            checkPassword(password, valid, user);
            checkName(name, user);

            if (errors.isEmpty()) {
                userDao.create(user);
                results = "Succès de l'inscription.";
            } else {
                results = "Échec de l'inscription.";
            }
        } catch (DAOException e) {
            results = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    private void checkEmail(String email) throws Exception
    {
        if (email != null) {
            if (!email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)")) {
                throw new FormValidationException("Merci de saisir une adresse mail valide.");
            } else if (userDao.find(email) != null) {
                throw new FormValidationException("Cette adresse email est déjà utilisée, merci d'en choisir une autre.");
            }
        } else {
            throw new FormValidationException("Merci de saisir une adresse mail.");
        }
    }

    private void checkEmail(String email, Users user)
    {
        try {
            checkEmail(email);
        } catch (FormValidationException e) {
            setError(CHAMP_EMAIL, e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        user.setEmail(email);
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

    private void checkPassword(String password, String valid, Users user)
    {
        try {
            checkPassword(password, valid);
        } catch (FormValidationException e) {
            setError(CHAMP_PASS, e.getMessage());
            setError(CHAMP_VALID, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        /*
         * Utilisation de la bibliothèque Jasypt pour chiffrer le mot de passe
         * efficacement.
         *
         * L'algorithme SHA-256 est ici utilisé, avec par défaut un salage
         * aléatoire et un grand nombre d'itérations de la fonction de hashage.
         *
         * La String retournée est de longueur 56 et contient le hash en Base64.
         */
        ConfigurablePasswordEncryptor passwordEncryptor;
        passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm(ALGO_CHIFFREMENT);
        passwordEncryptor.setPlainDigest(false);
        String encryptedPassword = passwordEncryptor.encryptPassword(password);

        user.setPassword(encryptedPassword);
    }

    private void checkName(String name, Users user) throws Exception
    {
        if (name != null && name.length() < 3) {
            throw new Exception("Le nom d'utilisateur doit contenir au moins 3 caractères.");
        } else {
            user.setLastname(name);
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
