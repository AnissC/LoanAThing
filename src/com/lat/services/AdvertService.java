package com.lat.services;

public class AdvertService
{
    private static AdvertService SESSION = null;

    private AdvertService()
    {
    }

    public static AdvertService getInstance()
    {
        if (SESSION == null)
        {
            SESSION = new AdvertService();
        }
        return SESSION;
    }
}
