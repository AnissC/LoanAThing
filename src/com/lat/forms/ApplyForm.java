package com.lat.forms;

import com.lat.beans.Advert;
import com.lat.beans.Apply;
import com.lat.beans.User;
import com.lat.dao.ApplyDao;
import com.lat.dao.DAOException;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class ApplyForm
{
    private ApplyDao applyDao;
    private String results;
    private Map<String, String> errors = new HashMap<String, String>();

    public ApplyForm(ApplyDao applyDao)
    {
        this.applyDao = applyDao;
    }

    public String getResults()
    {
        return results;
    }

    public Map<String, String> getErrors()
    {
        return errors;
    }

    public Apply processApply(String dateStart, String dateEnd, Integer advertId, Integer userId)
    {
        Apply apply = new Apply();

        try {
            checkValues(apply, dateStart, dateEnd, advertId, userId);
            apply.setAccepted(false);

            if (errors.isEmpty()) {
                applyDao.create(apply);

                if (apply.getId() == null) {
                    setError("applyFail", "Les dates sont obligatoires.");
                }
            } else {
                setError("applyFail", "Échec lors de la demande d'emprunt.");
            }
        } catch (DAOException e) {
            results = "Échec lors de la création de la demande d'emprunt : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return apply;
    }

    private void checkValues(String dateStart, String dateEnd, Integer advertId, Integer userId) throws Exception
    {
        if (dateStart == null || dateEnd == null || advertId == null || userId == null) {
            throw new FormValidationException("Les dates ne peuvent être nulles");
        }
    }

    private void checkValues(Apply apply, String dateStart, String dateEnd, Integer advertId, Integer userId) throws Exception
    {
        try {
            checkValues(dateStart, dateEnd, advertId, userId);
        } catch (FormValidationException e) {
            setError("applyFail", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Advert advert = new Advert();
        advert.setId(advertId);

        User user = new User();
        user.setId(userId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.FRANCE);
        LocalDate dateStartFormatted = LocalDate.parse(dateStart, formatter);
        LocalDate dateEndFormatted = LocalDate.parse(dateEnd, formatter);

        apply.setDateStart(dateStartFormatted.toString());
        apply.setDateEnd(dateEndFormatted.toString());
        apply.setUser(user);
        apply.setAdvert(advert);
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
