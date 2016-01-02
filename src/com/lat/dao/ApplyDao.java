package com.lat.dao;

import com.lat.beans.Advert;
import com.lat.beans.Apply;
import com.lat.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.lat.dao.DAOUtilities.initialisationRequetePreparee;
import static com.lat.dao.DAOUtilities.silentClosures;

public class ApplyDao
{
    private DAOFactory daoFactory;
    private static final String SQL_SELECT_WITH_ID = "SELECT * FROM apply WHERE id = ?";
    private static final String SQL_SELECT_COUNT = "SELECT COUNT(*) as nbApplies FROM apply";
    private static final String SQL_SELECT_PENDING_REQUESTS = "SELECT * FROM apply WHERE user_id = ?";
    private static final String SQL_SELECT_INBOX_REQUESTS = "SELECT * FROM apply WHERE advert_id = ? AND accepted = false";
    private static final String SQL_INSERT = "INSERT INTO apply (date_start, date_end, accepted, user_id, advert_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM apply WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM apply ORDER BY id";
    private static final String SQL_SELECT_ALL_BY_USER_ID = "SELECT * FROM apply WHERE user_id = ?";
    private static final String SQL_SELECT_ALL_BY_ADVERT_ID = "SELECT * FROM apply WHERE advert_id = ?";
    private static final String SQL_UPDATE = "UPDATE apply SET date_start = ?, date_end = ?, accepted = ?, user_id = ?, advert_id = ? WHERE id = ?";

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

    public List<Apply> findPendingRequests(Integer advertId) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Apply> applyRequests = new ArrayList<Apply>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_INBOX_REQUESTS, false, advertId);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                applyRequests.add(map(resultSet));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return applyRequests;
    }

    public List<Apply> findInboxRequests(User user) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Apply> applyRequests = new ArrayList<Apply>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_INBOX_REQUESTS, false, user.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                applyRequests.add(map(resultSet));
            }
        } catch ( SQLException e ) {
            throw new DAOException( e );
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return applyRequests;
    }

    public void delete(Apply apply) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_DELETE, false, apply.getId());
            preparedStatement.executeUpdate();
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
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, apply.getDateStart(), apply.getDateEnd(), apply.getAccepted(), apply.getUser().getId(), apply.getAdvert().getId());
            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if (statut == 0) {
                throw new DAOException("Échec de la création d'une demande de prêt, aucune ligne ajoutée dans la table.");
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                /* Puis initialisation de la propriété id du bean Advert avec sa valeur */
                apply.setId(valeursAutoGenerees.getInt(1));
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

    private Apply map(ResultSet resultSet) throws SQLException
    {
        Apply apply = new Apply();
        UserDao userDao = this.daoFactory.getUserDao();
        AdvertDao advertDao = this.daoFactory.getAdvertDao();

        apply.setId(resultSet.getInt("id"));
        apply.setAccepted(resultSet.getBoolean("accepted"));
        apply.setDateStart(resultSet.getString("date_start"));
        apply.setDateEnd(resultSet.getString("date_end"));
        apply.setUser(userDao.findOneById(resultSet.getInt("user_id")));
        apply.setAdvert(advertDao.findOneById(resultSet.getInt("advert_id")));

        return apply;
    }

    public List<Apply> find() throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Apply> apply = new ArrayList<Apply>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                apply.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return apply;
    }

    public Apply findOneById(long id) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Apply apply = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WITH_ID, false, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                apply = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connexion);
        }

        return apply;
    }

    public List<Apply> findAllByUserId(User user) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Apply> applies = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_ALL_BY_USER_ID, false, user.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                applies.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return applies;
    }

    public List<Apply> findAllByAdvertId(Advert advert)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Apply> applies = new ArrayList<>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_ALL_BY_ADVERT_ID, false, advert.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                applies.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return applies;
    }

    public void update(Apply apply) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_UPDATE, false, apply.getDateStart(), apply.getDateEnd(), apply.getAccepted(), apply.getUser().getId(), apply.getAdvert().getId(), apply.getId());

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
}
