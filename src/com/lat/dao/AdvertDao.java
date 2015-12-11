package com.lat.dao;

import com.lat.beans.Category;
import com.lat.beans.Users;
import com.lat.beans.Adverts;

import static com.lat.dao.DAOUtilities.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdvertDao
{
    private DAOFactory daoFactory;
    private static final String SQL_SELECT_WHITH_ID = "SELECT id, title, description, date_start, date_end, id_category FROM adverts WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT id, title, description, date_start, date_end, id_category FROM adverts ORDER BY id";
    private static final String SQL_INSERT = "INSERT INTO adverts (title, description, date_start, date_end, id_category, id_users) VALUES (?, ?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM adverts WHERE id = ?";
    private static final String SQL_COUNT_ADVERT = "SELECT COUNT(*) FROM adverts";
    private static final String SQL_COUNT_ADVERT_BY_CATEGORY = "SELECT COUNT(*) FROM adverts A, category C WHERE A.id_category = ? OR (? = C.parent_category AND A.id_category = C.id)";

    AdvertDao(DAOFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }
    
    public ResultSet count()
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_COUNT_ADVERT, false);
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return resultSet;
    }
    
    public ResultSet countByCategory(Category category)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_COUNT_ADVERT, false, category.getId(), category.getId());
            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return resultSet;
    }

    public void create(Adverts advert, Users user) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, advert.getTitle(), advert.getDescription(), advert.getDateStart(), advert.getDateEnd(), advert.getCategoryId(), user.getId());
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

    public List<Adverts> find() throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Adverts> adverts = new ArrayList<Adverts>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                adverts.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return adverts;
    }

    public Adverts findOneById(int id) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Adverts advert = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WHITH_ID, false, id);
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

    public void delete(Adverts advert) throws DAOException
    {
    }

    private static Adverts map(ResultSet resultSet) throws SQLException
    {
        Adverts advert = new Adverts();
        advert.setId(resultSet.getLong("id"));
        advert.setTitle(resultSet.getString("title"));
        advert.setDescription(resultSet.getString("description"));
        advert.setDateStart(resultSet.getString("date_start"));
        advert.setDateEnd(resultSet.getString("date_end"));
        advert.setCategoryId(resultSet.getString("id_category"));

        return advert;
    }
}
