package com.lat.forms;

import com.lat.beans.Adverts;
import com.lat.beans.Users;
import com.lat.dao.DAOException;
import com.lat.dao.AdvertDao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public final class AdvertAddForm
{
    private AdvertDao advertDao;

    private String results;
    private Map<String, String> errors = new HashMap<String, String>();

    public AdvertAddForm(AdvertDao advertDao)
    {
        this.advertDao = advertDao;
    }

    public String getResults()
    {
        return results;
    }

    public Map<String, String> getErrors()
    {
        return errors;
    }

    public Adverts processAdvert(String title, String description, String dateStart, String dateEnd, String categoryId, Users user)
    {
        Adverts advert = new Adverts();

        try {
            checkValues(advert, title, description, dateStart, dateEnd, categoryId);

            if (errors.isEmpty()) {
                advertDao.create(advert, user);
            } else {
                results = "Échec lors de la création de l'offre de prêt.";
            }
        } catch (DAOException e) {
            results = "Échec lors de la création de l'offre de prêt : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return advert;
    }

    private void checkValues(String title, String description, String dateStart, String categoryId) throws Exception
    {
        if (title == null || description == null || dateStart == null || categoryId == null) {
            throw new FormValidationException("Les champs marqués d'une * ne peuvent être nuls");
        }
    }

    private void checkValues(Adverts advert, String title, String description, String dateStart, String dateEnd, String categoryId) throws Exception {
        try {
            checkValues(title, description, dateStart, categoryId);
        } catch (FormValidationException e) {
            setError("", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.FRANCE);
        LocalDate startDate = LocalDate.parse(dateStart, formatter);

        advert.setTitle(title);
        advert.setDescription(description);
        advert.setDateStart(startDate.toString());
        advert.setCategoryId(categoryId);
        if (dateEnd != null) {
            LocalDate endDate = LocalDate.parse(dateEnd, formatter);
            advert.setDateEnd(endDate.toString());
        } else {
            advert.setDateEnd(null);
        }
    }

    private void setError(String field, String message)
    {
        errors.put(field, message);
    }
}
