package com.lat.dao;

import com.lat.beans.ReportingAdvert;
import com.lat.beans.ReportingUser;
import com.lat.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.lat.dao.DAOUtilities.initialisationRequetePreparee;
import static com.lat.dao.DAOUtilities.silentClosures;

public class ReportingUserDao
{
    private DAOFactory daoFactory;
    private static final String SQL_SELECT_ALL = "SELECT * FROM reporting_user ORDER BY id";
    private static final String SQL_SELECT_ALL_BY_ADVERT_ID = "SELECT * from reporting_user WHERE user_id = ?";
    private static final String SQL_INSERT = "INSERT INTO reporting_user (user_id) VALUES (?)";
    private static final String SQL_DELETE = "DELETE FROM reporting_user WHERE id = ?";

    ReportingUserDao(DAOFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    public List<ReportingUser> find() throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ReportingUser> reportingUsers = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reportingUsers.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return reportingUsers;
    }

    private ReportingUser map(ResultSet resultSet) throws SQLException
    {
        ReportingUser reportingUser= new ReportingUser();
        UserDao userDao = this.daoFactory.getUserDao();

        reportingUser.setId(resultSet.getInt("id"));
        reportingUser.setUser(userDao.findOneById(resultSet.getInt("user_id")));

        return reportingUser;
    }

    public void create(ReportingUser reportingUser) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, reportingUser.getUser().getId());

            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if (statut == 0) {
                throw new DAOException("Échec de la création du signalement de l'offre, aucune ligne ajoutée dans la table.");
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                /* Puis initialisation de la propriété id du bean reportingAdvert avec sa valeur */
                reportingUser.setId(valeursAutoGenerees.getInt(1));
            } else {
                throw new DAOException("Échec de la création du signalement de l'offre, aucun ID auto-généré retourné.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    public List<ReportingUser> findAllByIdUser(long userId) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ReportingUser> reportingUsers = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_ALL_BY_ADVERT_ID, false, userId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reportingUsers.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return reportingUsers;
    }

    public void delete(ReportingUser reportingUser) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_DELETE, false, reportingUser.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }
    }
}
