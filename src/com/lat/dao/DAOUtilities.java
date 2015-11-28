package com.lat.dao;

import java.sql.*;

public final class DAOUtilities
{
    /*
     * Constructeur cachÃ© par dÃ©faut (car c'est une classe finale utilitaire,
     * contenant uniquement des mÃ©thode appelÃ©es de maniÃ¨re statique)
     */
    private DAOUtilities() {
    }

    /*
     * Initialise la requête préparée basée sur la connexion passée en argument,
     * avec la requête SQL et les objets donnés.
     */
    public static PreparedStatement initialisationRequetePreparee(Connection connexion, String sql, boolean returnGeneratedKeys, Object... objets ) throws SQLException
    {
        PreparedStatement preparedStatement = connexion.prepareStatement(sql, returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);

        for (int i = 0; i < objets.length; i++) {
            preparedStatement.setObject(i + 1, objets[i]);
        }

        return preparedStatement;
    }

    /* Fermeture silencieuse du resultset */
    public static void silentClosure(ResultSet resultSet)
    {
        if (resultSet != null) {
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println("Échec de la fermeture du ResultSet : " + e.getMessage());
            }
        }
    }

    /* Fermeture silencieuse du statement */
    public static void silentClosure(Statement statement)
    {
        if (statement != null) {
            try {
                statement.close();
            } catch (SQLException e) {
                System.out.println("Échec de la fermeture du Statement : " + e.getMessage());
            }
        }
    }

    /* Fermeture silencieuse de la connexion */
    public static void silentClosure(Connection connexion)
    {
        if (connexion != null) {
            try {
                connexion.close();
            } catch (SQLException e) {
                System.out.println("Échec de la fermeture de la connexion : " + e.getMessage());
            }
        }
    }

    /* Fermetures silencieuses du statement et de la connexion */
    public static void silentClosures(Statement statement, Connection connexion)
    {
        silentClosure(statement);
        silentClosure(connexion);
    }

    /* Fermetures silencieuses du resultset, du statement et de la connexion */
    public static void silentClosures(ResultSet resultSet, Statement statement, Connection connexion)
    {
        silentClosure(resultSet);
        silentClosure(statement);
        silentClosure(connexion);
    }
}
