package com.lat.forms;

import com.lat.beans.Users;
import com.lat.dao.DAOException;
import com.lat.dao.UserDao;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import java.util.HashMap;
import java.util.Map;

public final class LoginForm
{
    private UserDao userDao;
    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public LoginForm(UserDao userDao)
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

    public Users connectUser(String email,String password)
    {
        Users user = new Users();

        try {
            checkEmail(email, user);
            checkPassword(password, user);

            if (errors.isEmpty()) {
                user = userDao.findOneByEmailAndPassword(user);

                if (user.getId() == null) {
                    setError("loginFail", "Mauvais email ou mot de passe");
                }
            } else {
                setError("loginFail", "Échec de la connexion.");
            }
        } catch (DAOException e) {
            result = "Échec de la connexion : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return user;
    }

    private void checkEmail(String email) throws Exception
    {
        if (email != null && !email.matches( "([^.@]+)(\\.[^.@]+)*@([^.@]+\\.)+([^.@]+)" )) {
            throw new Exception("Merci de saisir une adresse mail valide.");
        }
    }

    private void checkEmail(String email, Users user)
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

    private void checkPassword(String password, Users user)
    {
        try {
            checkPassword(password);
        } catch (FormValidationException e) {
            setError("password", e.getMessage());
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

    private void setError(String field, String message)
    {
        errors.put(field, message);
    }

    public void resetError()
    {
        errors.clear();
    }
}