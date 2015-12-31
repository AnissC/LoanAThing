package com.lat.dao;

import com.lat.beans.Advert;
import com.lat.beans.Reporting;
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
}
