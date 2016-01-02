package com.lat.dao;

import com.lat.beans.Advert;
import com.lat.beans.Apply;
import com.lat.beans.Loan;
import com.lat.beans.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static com.lat.dao.DAOUtilities.initialisationRequetePreparee;
import static com.lat.dao.DAOUtilities.silentClosures;

public class LoanDao
{
    private DAOFactory daoFactory;
    private static final String SQL_SELECT_WHITH_ID = "SELECT * FROM loan WHERE id = ?";
    private static final String SQL_SELECT_ALL = "SELECT * FROM loan ORDER BY id";
    private static final String SQL_INSERT = "INSERT INTO loan (code, state_code, return_code, state_return_code, apply_id) VALUES (?, ?, ?, ?, ?)";
    private static final String SQL_DELETE = "DELETE FROM loan WHERE id = ?";
    private static final String SQL_SELECT_WITH_APPLY_ID = "SELECT * FROM loan WHERE apply_id = ?";

    LoanDao(DAOFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    public void create(Loan loan) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet valeursAutoGenerees = null;

        try {
            /* Récupération d'une connexion depuis la Factory */
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_INSERT, true, loan.getCode(), loan.getStateCode(), loan.getReturnCode(), loan.getStateReturnCode(), loan.getApply().getId());

            int statut = preparedStatement.executeUpdate();
            /* Analyse du statut retourné par la requête d'insertion */
            if (statut == 0) {
                throw new DAOException("Échec de la création d'un prêt, aucune ligne ajoutée dans la table.");
            }
            /* Récupération de l'id auto-généré par la requête d'insertion */
            valeursAutoGenerees = preparedStatement.getGeneratedKeys();
            if (valeursAutoGenerees.next()) {
                /* Puis initialisation de la propriété id du bean Advert avec sa valeur */
                loan.setId(valeursAutoGenerees.getInt(1));
            } else {
                throw new DAOException("Échec de la création d'un prêt en base, aucun ID auto-généré retourné.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(valeursAutoGenerees, preparedStatement, connexion);
        }
    }

    public List<Loan> find() throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        List<Loan> loans = new ArrayList<Loan>();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_ALL, false);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                loans.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return loans;
    }

    public Loan findOneById(Integer id) throws DAOException
    {
        Connection connexion = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Loan loan = null;

        try {
            connexion = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connexion, SQL_SELECT_WHITH_ID, false, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                loan = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connexion);
        }

        return loan;
    }

    public void delete(Loan loan) throws DAOException
    {
    }

    private Loan map(ResultSet resultSet) throws SQLException
    {
        Loan loan = new Loan();
        ApplyDao applyDao = this.daoFactory.getApplyDao();

        loan.setId(resultSet.getInt("id"));
        loan.setCode(resultSet.getString("code"));
        loan.setStateCode(resultSet.getBoolean("state_code"));
        loan.setReturnCode(resultSet.getString("return_code"));
        loan.setStateReturnCode(resultSet.getBoolean("state_return_code"));
        loan.setApply(applyDao.findOneById(resultSet.getInt("apply_id")));

        return loan;
    }

    public Loan findOneByApplyId(Apply apply)
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Loan loan = new Loan();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_WITH_APPLY_ID, false, apply.getId());
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                loan = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return loan;
    }
}
