package com.lat.dao;

public class DAOConfigurationException extends RuntimeException
{
    public DAOConfigurationException(String message)
    {
        super(message);
    }

    public DAOConfigurationException(String message, Throwable cause)
    {
        super(message);
    }

    public DAOConfigurationException(Throwable cause)
    {
        super(cause);
    }
}
