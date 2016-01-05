package com.lat.forms;

import com.lat.beans.User;
import com.lat.dao.DAOException;
import com.lat.dao.UserDao;
import org.jasypt.util.password.ConfigurablePasswordEncryptor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class UserForm
{
    private UserDao userDao;
    private String result;
    private Map<String, String> errors = new HashMap<String, String>();

    public UserForm(UserDao userDao)
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

    public User processUser(String email, String password, String valid, String nickname)
    {
        User user = new User();

        try {
            checkEmail(email, user);
            checkPassword(password, valid, user);
            checkNickname(nickname, user);

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

    public void updateUser(User user, String lastname, String firstname, String nickname, String email, String address, String city, Integer zipcode, String birthday)
    {
        try {
            checkUpdatedValues(user, lastname, firstname, nickname, email, address, city, zipcode, birthday);

            if (errors.isEmpty()) {
                userDao.update(user);

                if (user.getId() == null) {
                    setError("userFail", "Tous les champs doivent être remplis");
                }
            } else {
                setError("userFail", "Échec lors de la modification de l'utilisateur.");
            }
        } catch (DAOException e) {
            result = "Échec lors de la modification de l'utilisateur : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void checkUpdatedValues(User user, String lastname, String firstname, String nickname, String email, String address, String city, Integer zipcode, String birthday) throws Exception
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.FRANCE);

        if (lastname != null) {
            user.setLastname(lastname);
        }

        if (firstname != null) {
            user.setFirstname(firstname);
        }

        if (nickname != null) {
            user.setNickname(nickname.toString());
        }

        if (email != null) {
            user.setEmail(email);
        }

        if (address != null) {
            user.setAddress(address);
        }

        if (city != null) {
            user.setCity(city);
        }

        if (zipcode != null) {
            user.setZipCode(zipcode);
        }

        if (birthday != null) {
            LocalDate date = LocalDate.parse(birthday, formatter);
            user.setBirthday(date.toString());
        }
    }

    public User connectUser(String email, String password)
    {
        User user = new User();

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

    private void checkPassword(String password, User user)
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

    private void checkNickname(String nickname, User user)
    {
        if (nickname != null && nickname.length() < 3) {
            setError("nickname", "Le nom d'utilisateur doit contenir au moins 3 caractères.");
        } else {
            user.setNickname(nickname);
        }
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