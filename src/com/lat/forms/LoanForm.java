package com.lat.forms;

import com.lat.beans.Loan;
import com.lat.dao.DAOException;
import com.lat.dao.LoanDao;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class LoanForm
{
    private LoanDao loanDao;
    private String results;
    private Map<String, String> errors = new HashMap<String, String>();

    public LoanForm(LoanDao loanDao)
    {
        this.loanDao = loanDao;
    }

    public String getResults()
    {
        return results;
    }

    public Map<String, String> getErrors()
    {
        return errors;
    }

    public Loan processLoan(Integer applyId)
    {
        Loan loan = new Loan();

        try {
            checkValues(loan, applyId);
            generateCodes(loan);
            loan.setStateCode(false);
            loan.setStateReturnCode(false);

            if (errors.isEmpty()) {
                loanDao.create(loan);

                if (loan.getId() == null) {
                    setError("loanFail", "Erreur de génération des codes");
                }
            } else {
                setError("loanFail", "Échec lors de la validation d'emprunt.");
            }
        } catch (DAOException e) {
            results = "Échec lors de la création de la validation d'emprunt : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return loan;
    }

    private void checkValues(Integer applyId) throws Exception
    {
        if (applyId == null) {
            throw new FormValidationException("Il faut une demande de prêt au préalable");
        }
    }

    private void checkValues(Loan loan, Integer applyId) throws Exception
    {
        try {
            checkValues(applyId);
        } catch (FormValidationException e) {
            setError("loanFail", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void generateCodes(Loan loan)
    {
        loan.setCode(UUID.randomUUID().toString());
        loan.setReturnCode(UUID.randomUUID().toString());
    }

    private void setError(String field, String message)
    {
        errors.put(field, message);
    }

    public void resetError()
    {
        errors.clear();
    }
}
