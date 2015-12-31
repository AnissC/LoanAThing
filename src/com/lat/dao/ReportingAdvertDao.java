package com.lat.dao;

import com.lat.beans.Advert;
import com.lat.beans.ReportingAdvert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.lat.dao.DAOUtilities.initialisationRequetePreparee;
import static com.lat.dao.DAOUtilities.silentClosures;

public class ReportingAdvertDao {
    private DAOFactory daoFactory;
    private static final String SQL_SELECT_ALL = "SELECT * FROM reporting_advert ORDER BY id";
    private static final String SQL_SELECT_ALL_BY_ADVERT_ID = "SELECT * from reporting_advert WHERE advert_id = ?";
    private static final String SQL_INSERT = "INSERT INTO reporting_advert (advert_id) VALUES (?)";
    private static final String SQL_DELETE = "DELETE FROM reporting_advert WHERE id = ?";

    ReportingAdvertDao(DAOFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    public List<ReportingAdvert> find() throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ReportingAdvert> reportingAdverts = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reportingAdverts.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return reportingAdverts;
    }

    private ReportingAdvert map(ResultSet resultSet) throws SQLException
    {
        ReportingAdvert reportingAdvert = new ReportingAdvert();
        AdvertDao advertDao = this.daoFactory.getAdvertDao();

        reportingAdvert.setId(resultSet.getInt("id"));
        reportingAdvert.setAdvert(advertDao.findOneById(resultSet.getInt("advert_id")));

        return reportingAdvert;
    }

    public void create(ReportingAdvert reportingAdvert) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, reportingAdvert.getAdvert().getId());

            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if (statut == 0) {
                throw new DAOException("Échec de la création du signalement de l'offre, aucune ligne ajoutée dans la table.");
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                /* Puis initialisation de la propriété id du bean reportingAdvert avec sa valeur */
                reportingAdvert.setId(valeursAutoGenerees.getInt(1));
            } else {
                throw new DAOException("Échec de la création du signalement de l'offre, aucun ID auto-généré retourné.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    public List<ReportingAdvert> findAllByIdAdvert(long advertId) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<ReportingAdvert> reportingAdverts = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_ALL_BY_ADVERT_ID, false, advertId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                reportingAdverts.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return reportingAdverts;
    }

    public void delete(ReportingAdvert reportingAdvert) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_DELETE, false, reportingAdvert.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }
    }
}
