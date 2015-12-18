package com.lat.dao;

import com.lat.beans.Category;
import com.lat.beans.Advert;

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
    private static final String SQL_SELECT_WITH_ID = "SELECT * FROM advert WHERE id = ?";
    private static final String SQL_SELECT_ALL_BY_USER_ID = "SELECT * FROM advert WHERE user_id = ?";
    private static final String SQL_SELECT_ALL_BY_NAME = "SELECT * FROM advert WHERE title LIKE ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM advert ORDER BY id";
    private static final String SQL_INSERT = "INSERT INTO advert (title, description, date_start, date_end, category_id, user_id, is_publish, is_suspend) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE advert SET title = ?, description = ?, date_start = ?, date_end = ?, category_id = ?, is_publish = ?, is_suspend = ? WHERE id = ?";
    private static final String SQL_DELETE = "DELETE FROM advert WHERE id = ?";
    private static final String SQL_COUNT_ADVERT = "SELECT COUNT(*) FROM advert";
    private static final String SQL_COUNT_ADVERT_BY_CATEGORY = "SELECT COUNT(*) FROM advert A, category C WHERE A.category_id = ? OR (? = C.parent_category AND A.category_id = C.id)";

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

    /* TODO : This method is wrong, please fix it */
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

    public void create(Advert advert) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, advert.getTitle(), advert.getDescription(), advert.getDateStart(), advert.getDateEnd(), advert.getCategory().getId(), advert.getUser().getId(), advert.getPublish(), advert.getSuspend());

            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if (statut == 0) {
                throw new DAOException("Échec de la création d'une offre de prêt, aucune ligne ajoutée dans la table.");
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                /* Puis initialisation de la propriété id du bean Advert avec sa valeur */
                advert.setId(valeursAutoGenerees.getInt(1));
            } else {
                throw new DAOException("Échec de la création de l'offre de prêt en base, aucun ID auto-généré retourné.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    public void update(Advert advert) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE, false, advert.getTitle(), advert.getDescription(), advert.getDateStart(), advert.getDateEnd(), advert.getCategory().getId(), advert.getPublish(), advert.getSuspend(), advert.getId());

            int statut = preparedStatement.executeUpdate();
            if (statut == 0) {
                throw new DAOException("Échec de la modification d'une offre de prêt, aucune ligne ajoutée dans la table.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connexion);
        }
    }

    public List<Advert> find() throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Advert> adverts = new ArrayList<Advert>();

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

    public List<Advert> findAllByUserId(Integer userId) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Advert> adverts = new ArrayList<Advert>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_ALL_BY_USER_ID, false, userId);
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

    public Advert findOneById(Integer id) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Advert advert = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WITH_ID, false, id);
            resultSet = preparedStatement.executeQuery();
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

    public void delete(Advert advert) throws DAOException
    {
    }

    private Advert map(ResultSet resultSet) throws SQLException
    {
        Advert advert = new Advert();
        UserDao userDao = this.daoFactory.getUserDao();
        CategoryDao categoryDao = this.daoFactory.getCategoryDao();

        advert.setId(resultSet.getInt("id"));
        advert.setTitle(resultSet.getString("title"));
        advert.setDescription(resultSet.getString("description"));
        advert.setImage(resultSet.getString("image"));
        advert.setDateStart(resultSet.getString("date_start"));
        advert.setDateEnd(resultSet.getString("date_end"));
        advert.setShortDescription(resultSet.getString("short_description"));
        advert.setUser(userDao.findOneById(resultSet.getInt("user_id")));
        advert.setCategory(categoryDao.findOneById(resultSet.getInt("category_id")));
        advert.setPublish(resultSet.getBoolean("is_publish"));
        advert.setSuspend(resultSet.getBoolean("is_suspend"));

        return advert;
    }

    public List<Advert> findAllByName(String name) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Advert> adverts = new ArrayList<Advert>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_ALL_BY_NAME, false, name);
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

}
