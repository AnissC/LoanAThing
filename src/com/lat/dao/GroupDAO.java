package com.lat.dao;

import com.lat.beans.Group;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static com.lat.dao.DAOUtilities.initialisationRequetePreparee;
import static com.lat.dao.DAOUtilities.silentClosures;

public class GroupDAO {

    private DAOFactory daoFactory;
    private static final String SQL_SELECT_WITH_ID = "SELECT * FROM `group` WHERE id = ?";

    GroupDAO(DAOFactory daoFactory)
    {
        this.daoFactory = daoFactory;
    }

    public Group findOneById(long id) throws DAOException
    {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        Group group = new Group();

        try {
            connection = daoFactory.getConnection();
            preparedStatement = initialisationRequetePreparee(connection, SQL_SELECT_WITH_ID, false, id);
            resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                group = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        } finally {
            silentClosures(resultSet, preparedStatement, connection);
        }

        return group;
    }

    private static Group map(ResultSet resultSet) throws SQLException
    {
        Group group = new Group();

        group.setId(resultSet.getInt("id"));
        group.setName(resultSet.getString("name"));

        return group;
    }
}
