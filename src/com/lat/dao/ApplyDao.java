package com.lat.dao;


import com.lat.beans.Apply;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.lat.dao.DAOUtilities.initialisationRequetePreparee;
import static com.lat.dao.DAOUtilities.silentClosures;

public class ApplyDao
{

    private DAOFactory daoFactory;
    private static final String SQL_SELECT_COUNT = "SELECT COUNT(*) as Nb_Applies FROM apply";
    private static final String SQL_INSERT = "INSERT INTO apply (start_date, end_date, accepted) VALUES (?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM apply WHERE id = ?";

    ApplyDao(DAOFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    public ResultSet countApplies() throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_COUNT, false);
            resultSet = preparedStatement.executeQuery();
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return resultSet;
    }


    public void delete(Apply apply) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_DELETE, true);
            resultSet = preparedStatement.executeQuery();
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }
    }


    public Apply create(Apply apply) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, apply.getStartDate(), apply.getEndDate(), apply.isAccepted());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if (statut == 0) {
                throw new DAOException("Échec de la création d'une demande de prêt, aucune ligne ajoutée dans la table.");
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                /* Puis initialisation de la propriété id du bean Advert avec sa valeur */
                apply.setId(valeursAutoGenerees.getLong(1));
            } else {
                throw new DAOException("Échec de la création de la demande de prêt en base, aucun ID auto-généré retourné.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(valeursAutoGenerees, preparedStatement, connexion);
        }
        return apply;
    }

    private static Apply map(ResultSet resultSet) throws SQLException
    {
        Apply apply = new Apply();
        apply.setId(resultSet.getLong("id"));
        apply.setAccepted(resultSet.getBoolean("accepted"));
        apply.setStartDate(resultSet.getString("start_date"));
        apply.setEndDate(resultSet.getString("end_date"));

        return apply;
    }
}
