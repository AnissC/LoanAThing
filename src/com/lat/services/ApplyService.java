package com.lat.services;

import com.lat.beans.Apply;
import com.lat.dao.ApplyDao;
import com.lat.dao.DAOFactory;

public class ApplyService
{
    private static ApplyService APPLY_SERVICE = null;
    private ApplyDao applyDao;

    private ApplyService()
    {
        this.applyDao = DAOFactory.getInstance().getApplyDao();
    }

    public static ApplyService getInstance()
    {
        if (APPLY_SERVICE == null)
        {
            APPLY_SERVICE = new ApplyService();
        }
        return APPLY_SERVICE;
    }

    public Apply getApplyById(long id)
    {
        return applyDao.findOneById(id);
    }

    public void delete(Apply apply)
    {
        applyDao.delete(apply);
    }
}
