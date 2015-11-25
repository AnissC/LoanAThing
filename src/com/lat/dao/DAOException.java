package com.lat.dao;

public class DAOException extends RuntimeException
{
    public DAOException(String message)
    {
        super(message);
    }

    public DAOException(String message, Throwable cause)
    {
        super(message);
    }

    public DAOException(Throwable cause)
    {
        super(cause);
    }
}
