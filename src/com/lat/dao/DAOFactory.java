package com.lat.dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DAOFactory
{
    private static final String PROPERTIES_FILE = "/com/lat/dao/dao.properties";
    private static final String PROPERTY_URL = "url";
    private static final String PROPERTY_DRIVER = "driver";
    private static final String PROPERTY_USERNAME = "db_username";
    private static final String PROPERTY_PASSWORD = "db_password";

    private String url;
    private String username;
    private String password;

    DAOFactory(String url, String username, String password)
    {
        this.url = url;
        this.username = username;
        this.password = password;
    }

    public static DAOFactory getInstance() throws DAOConfigurationException
    {
        Properties properties = new Properties();
        String url;
        String driver;
        String username;
        String password;

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null) {
            throw new DAOConfigurationException("Le fichier properties " + PROPERTIES_FILE + " est introuvable.");
        }

        try {
            properties.load(propertiesFile);
            url = properties.getProperty(PROPERTY_URL);
            driver = properties.getProperty(PROPERTY_DRIVER);
            username = properties.getProperty(PROPERTY_USERNAME);
            password = properties.getProperty(PROPERTY_PASSWORD);
        } catch (IOException e) {
            throw new DAOConfigurationException("Impossible de charger le fichier properties " + PROPERTIES_FILE, e);
        }

        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            throw new DAOConfigurationException("Le driver est introuvable dans le classpath.", e);
        }

        DAOFactory instance = new DAOFactory(url, username, password);

        return instance;
    }

    Connection getConnection() throws SQLException
    {
        return DriverManager.getConnection(url, username, password);
    }

    public UserDao getUserDao()
    {
        return new UserDao(this);
    }

    public AdvertDao getAdvertDao()
    {
        return new AdvertDao(this);
    }

    public CategoryDao getCategoryDao()
    {
        return new CategoryDao(this);
    }

    public ApplyDao getApplyDao() { return new ApplyDao(this);}

    public LoanDao getLoanDao() { return new LoanDao(this);}

    public GroupDAO getGroupDAO() { return new GroupDAO(this);}

    public ReportingAdvertDao getReportingAdvertDao(){ return new ReportingAdvertDao(this);}
}
