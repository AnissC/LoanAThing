package com.lat.forms;

import com.lat.beans.Advert;
import com.lat.beans.Category;
import com.lat.beans.User;
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

    public Advert processAdvert(String title, String description, String dateStart, String dateEnd, Integer categoryId, Integer userId)
    {
        Advert advert = new Advert();

        try {
            checkValues(advert, title, description, dateStart, dateEnd, categoryId, userId);
            advert.setPublish(true);
            advert.setSuspend(false);

            if (errors.isEmpty()) {
                advertDao.create(advert);

                if (advert.getId() == null) {
                    setError("advertFail", "Tous les champs doivent être remplis");
                }
            } else {
                setError("advertFail", "Échec lors de la création de l'offre de prêt.");
            }
        } catch (DAOException e) {
            results = "Échec lors de la création de l'offre de prêt : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return advert;
    }

    public void updateAdvert(Advert advert, String title, String description, String dateStart, String dateEnd, Integer categoryId, Boolean isPublish, Boolean isSuspend)
    {
        try {
            checkUpdatedValues(advert, title, description, dateStart, dateEnd, categoryId, isPublish, isSuspend);

            if (errors.isEmpty()) {
                advertDao.update(advert);

                if (advert.getId() == null) {
                    setError("advertFail", "Tous les champs doivent être remplis");
                }
            } else {
                setError("advertFail", "Échec lors de la modification de l'offre de prêt.");
            }
        } catch (DAOException e) {
            results = "Échec lors de la modification de l'offre de prêt : une erreur imprévue est survenue, merci de réessayer dans quelques instants.";
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void checkValues(String title, String description, String dateStart, Integer categoryId) throws Exception
    {
        if (title == null || description == null || dateStart == null || categoryId == null) {
            throw new FormValidationException("Les champs marqués d'une * ne peuvent être nuls");
        }
    }

    private void checkValues(Advert advert, String title, String description, String dateStart, String dateEnd, Integer categoryId, Integer userId) throws Exception
    {
        try {
            checkValues(title, description, dateStart, categoryId);
        } catch (FormValidationException e) {
            setError("", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        Category category = new Category();
        category.setId(categoryId);

        User user = new User();
        user.setId(userId);

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.FRANCE);
        LocalDate startDate = LocalDate.parse(dateStart, formatter);

        advert.setTitle(title);
        advert.setDescription(description);
        advert.setDateStart(startDate.toString());
        advert.setCategory(category);
        advert.setUser(user);

        if (dateEnd != null) {
            LocalDate endDate = LocalDate.parse(dateEnd, formatter);
            advert.setDateEnd(endDate.toString());
        } else {
            advert.setDateEnd(null);
        }
    }

    private void checkUpdatedValues(Advert advert, String title, String description, String dateStart, String dateEnd, Integer categoryId, Boolean isPublish, Boolean isSuspend) throws Exception
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd", Locale.FRANCE);

        if (title != null) {
            advert.setTitle(title);
        }

        if (description != null) {
            advert.setDescription(description);
        }

        if (dateStart != null) {
            LocalDate startDate = LocalDate.parse(dateStart, formatter);
            advert.setDateStart(startDate.toString());
        }

        if (dateEnd != null) {
            LocalDate endDate = LocalDate.parse(dateEnd, formatter);
            advert.setDateEnd(endDate.toString());
        }

        if (categoryId != null) {
            Category category = new Category();
            category.setId(categoryId);
            advert.setCategory(category);
        }

        if (isPublish != null) {
            advert.setPublish(isPublish);
        }

        if (isSuspend != null) {
            advert.setSuspend(isSuspend);
        }
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
