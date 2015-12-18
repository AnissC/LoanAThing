package com.lat.dao;

import com.lat.beans.Group;
import com.lat.beans.User;
import static com.lat.dao.DAOUtilities.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao
{
    private DAOFactory daoFactory;

    private static final String SQL_SELECT_WITH_EMAIL = "SELECT * FROM user WHERE email = ?";
    private static final String SQL_SELECT_WITH_EMAIL_AND_PASSWORD = "SELECT * FROM user WHERE email = ? AND password = ?";
    private static final String SQL_SELECT_WITH_ID = "SELECT * FROM user WHERE id = ?";
    private static final String SQL_UPDATE = "UPDATE user SET lastname = ?, firstname = ?, nickname = ?, email = ?, address = ?, city = ?, zipcode = ?, birthday = ? WHERE id = ?";
    private static final String SQL_INSERT = "INSERT INTO user (email, password, lastname) VALUES (?, ?, ?)";

    UserDao(DAOFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    /* Implémentation de la méthode définie dans l'interface UtilisateurDao */
    public void create(User user) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;
        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, user.getEmail(), user.getPassword(), user.getLastname());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if (statut == 0) {
                throw new DAOException("Échec de la création de l'utilisateur, aucune ligne ajoutée dans la table.");
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                /* Puis initialisation de la propriété id du bean Utilisateur avec sa valeur */
                user.setId(valeursAutoGenerees.getInt(1));
            } else {
                throw new DAOException("Échec de la création de l'utilisateur en base, aucun ID auto-généré retourné.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    public void update(User user) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE, false, user.getLastname(), user.getFirstname(), user.getNickname(), user.getEmail(), user.getAddress(), user.getCity(), user.getZipCode(), user.getBirthday(), user.getId());

            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la création de l'utilisateur en base, aucune ligne modifiée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connexion);
        }
    }

    /* Implémentation de la méthode définie dans l'interface UtilisateurDao */
    public User find(String email) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WITH_EMAIL, false, email);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connexion);
        }

        return user;
    }

    public User findOneById(Integer id)
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        User user = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WITH_ID, false, id);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connexion);
        }

        return user;
    }

    public User findOneByEmailAndPassword(User user) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WITH_EMAIL_AND_PASSWORD, false, user.getEmail(), user.getPassword());
            resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connexion);
        }

        return user;
    }

    private User map(ResultSet resultSet) throws SQLException
    {
        User user = new User();
        GroupDAO groupDAO = this.daoFactory.getGroupDAO();

        user.setId(resultSet.getInt("id"));
        user.setLastname(resultSet.getString("lastname"));
        user.setFirstname(resultSet.getString("firstname"));
        user.setNickname(resultSet.getString("nickname"));
        user.setEmail(resultSet.getString("email"));
        user.setAddress(resultSet.getString("address"));
        user.setCity(resultSet.getString("city"));
        user.setZipCode(resultSet.getInt("zipcode"));
        user.setPassword(resultSet.getString("password"));
        user.setBirthday(resultSet.getString("birthday"));
        user.setPreferences(resultSet.getString("preferences"));
        user.setSchoolDomain(resultSet.getString("school_domain"));
        user.setEducationFormation(resultSet.getString("education_formation"));
        user.setImage(resultSet.getString("image"));
        user.setGroup(groupDAO.findOneById(resultSet.getInt("group_id")));

        return user;
    }
}
