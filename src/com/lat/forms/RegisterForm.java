package com.lat.forms;

import com.lat.beans.User;
import com.lat.dao.DAOException;
import com.lat.dao.UserDao;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import java.util.HashMap;
import java.util.Map;

public final class RegisterForm
{
    private UserDao userDao;
    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public RegisterForm(UserDao userDao)
    {
        this.userDao = userDao;
    }

    public String getResult()
    {
        return result;
    }

    public Map<String, String> getErrors()
    {
        return errors;
    }

    public User processUser(String email, String password, String valid, String lastname)
    {
        User user = new User();

        try {
            checkEmail(email, user);
            checkPassword(password, valid, user);
            checkLastname(lastname, user);

            if (errors.isEmpty()) {
                userDao.create(user);
            } else {
                setError("registerFail", "Échec de l'inscription.");
            }
        } catch (DAOException e) {
            result = "Échec de l'inscription : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
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

    private void checkEmail(String email, User user)
    {
        try {
            checkEmail(email);
        } catch (FormValidationException e) {
            setError("email", e.getMessage());
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

    private void checkPassword(String password, String valid, User user)
    {
        try {
            checkPassword(password, valid);
        } catch (FormValidationException e) {
            setError("password", e.getMessage());
            setError("valid", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        ConfigurablePasswordEncryptor passwordEncryptor;
        passwordEncryptor = new ConfigurablePasswordEncryptor();
        passwordEncryptor.setAlgorithm("SHA-256");
        passwordEncryptor.setPlainDigest(true);
        String encryptedPassword = passwordEncryptor.encryptPassword(password);

        user.setPassword(encryptedPassword);
    }

    private void checkLastname(String lastname, User user)
    {
        if (lastname != null && lastname.length() < 3) {
            setError("lastname", "Le nom d'utilisateur doit contenir au moins 3 caractères.");
        } else {
            user.setLastname(lastname);
        }
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setError(String field, String message)
    {
        errors.put(field, message);
    }

    public void resetError()
    {
        errors.clear();
    }
}
