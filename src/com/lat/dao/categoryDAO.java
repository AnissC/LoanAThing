package com.lat.dao;

import com.lat.beans.Category;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.lat.dao.DAOUtilities.initialisationRequetePreparee;
import static com.lat.dao.DAOUtilities.silentClosures;

/**
 * Created by Nico on 10/12/2015.
 */
public class CategoryDao 
{
    private DAOFactory daoFactory;
    private static final String SQL_SELECT_WHITH_ID = "SELECT id, category_name, description FROM category WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT id, category_name, description FROM category ORDER BY id";
    private static final String SQL_INSERT = "INSERT INTO category (category_name, description) VALUES (?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM category WHERE id = ?";

    CategoryDao(DAOFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    /* Implémentation de la méthode définie dans l'interface AdvertDao */
    public void create(Category category) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, category.getCategoryName(), category.getDescription());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if (statut == 0) {
                throw new DAOException("Échec de la création d'une category, aucune ligne ajoutée dans la table.");
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                /* Puis initialisation de la propriété id du bean Advert avec sa valeur */
                category.setId(valeursAutoGenerees.getLong(1));
            } else {
                throw new DAOException("Échec de la création de la category en base, aucun ID auto-généré retourné.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    public List<Category> find() throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Category> categories = new ArrayList<Category>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                categories.add(map(resultSet));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return categories;
    }

    public Category findOneById(int id) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Category category = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WHITH_ID, false, id);
            resultSet = preparedStatement.executeQuery();
            /* Parcours de la ligne de données de l'éventuel ResulSet retourné */
            if (resultSet.next()) {
                category = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connexion);
        }

        return category;
    }

    public void delete(Category category) throws DAOException
    {
    }

    /*
     * Simple méthode utilitaire permettant de faire la correspondance (le
     * mapping) entre une ligne issue de la table advert (un
     * ResultSet) et un bean Advert.
     */
    private static Category map(ResultSet resultSet) throws SQLException
    {
        Category category = new Category();
        category.setId(resultSet.getLong("id"));
        category.setCategoryName(resultSet.getString("category_name"));
        category.setDescription(resultSet.getString("description"));

        return category;
    }
}
