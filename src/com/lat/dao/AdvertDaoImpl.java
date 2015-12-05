package com.lat.dao;

import com.lat.beans.Advert;
import static com.lat.dao.DAOUtilities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class AdvertDaoImpl implements AdvertDao
{
    private DAOFactory daoFactory;
    private static final String SQL_SELECT_WHITH_TITLE = "SELECT id, title, description, date_start, date_end, active FROM advert WHERE title = ?";
    private static final String SQL_INSERT = "INSERT INTO advert (title, description, date_start, date_end, active) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM advert WHERE id = ?";

    AdvertDaoImpl(DAOFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    /* Implémentation de la méthode définie dans l'interface AdvertDao */
    @Override
    public void create(Advert advert) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, advert.getTitle(), advert.getDescription(), advert.getDateStart(), advert.getDateEnd(), advert.isActive());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if (statut == 0) {
                throw new DAOException("Échec de la création d'une offre de prêt, aucune ligne ajoutée dans la table.");
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                /* Puis initialisation de la propriété id du bean Advert avec sa valeur */
                advert.setId(valeursAutoGenerees.getLong(1));
            } else {
                throw new DAOException("Échec de la création de l'offre de prêt en base, aucun ID auto-généré retourné.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    @Override
    public Advert find(String title) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Advert advert = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WHITH_TITLE, false, title);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                advert = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connexion);
        }

        return advert;
    }

    @Override
    public void delete(Advert advert) throws DAOException
    {
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table advert (un
     * ResultSet) et un bean Advert.
     */
    private static Advert map(ResultSet resultSet) throws SQLException
    {
        Advert advert = new Advert();
        advert.setId(resultSet.getLong("id"));
        advert.setTitle(resultSet.getString("title"));
        advert.setDescription(resultSet.getString("description"));
        advert.setDateStart(resultSet.getDate("date_start"));
        advert.setDateEnd(resultSet.getDate("date_end"));
        advert.setActive(resultSet.getBoolean("active"));

        return advert;
    }
}