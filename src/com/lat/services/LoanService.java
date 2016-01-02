package com.lat.services;

import com.lat.beans.Advert;
import com.lat.beans.Apply;
import com.lat.beans.Loan;
import com.lat.beans.User;
import com.lat.dao.*;

import java.util.ArrayList;
import java.util.List;

public class LoanService
{
    private static LoanService LOAN_SERVICE = null;
    private LoanDao loanDao;
    private ApplyDao applyDao;
    private AdvertDao advertDao;

    private LoanService()
    {
        this.loanDao = DAOFactory.getInstance().getLoanDao();
        this.applyDao = DAOFactory.getInstance().getApplyDao();
        this.advertDao = DAOFactory.getInstance().getAdvertDao();
    }

    public static LoanService getInstance()
    {
        if (LOAN_SERVICE == null) {
            LOAN_SERVICE = new LoanService();
        }

        return LOAN_SERVICE;
    }

    public List<Loan> getAllLoansOfferedByUser(User user)
    {
        List<Advert> adverts = advertDao.findAllByUserId(user.getId());
        List<Apply> applies = new ArrayList<>();
        List<Loan> loans = new ArrayList<>();

        for (Advert advert : adverts)
        {
            for(Apply apply : applyDao.findAllByAdvertId(advert))
            {
                applies.add(apply);
            }
        }

        for (Apply apply : applies)
        {
            loans.add(loanDao.findOneByApplyId(apply));
        }

        return loans;
    }

    public List<Loan> getAllLoansAskedByUser(User user)
    {
        List<Apply> applies = applyDao.findAllByUserId(user);

        List<Loan> loans = new ArrayList<>();

        for (Apply apply : applies)
        {
            loans.add(loanDao.findOneByApplyId(apply));
        }

        return loans;
    }
}
