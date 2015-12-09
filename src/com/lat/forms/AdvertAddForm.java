package com.lat.forms;

import com.lat.beans.Advert;
import com.lat.dao.DAOException;
import com.lat.dao.AdvertDao;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.text.SimpleDateFormat;

public final class AdvertAddForm
{
    private static final String TITLE_FIELD = "title";
    private static final String DESCRIPTION_FIELD = "description";
    private static final String DATE_START_FIELD = "dateStart";
    private static final String DATE_END_FIELD = "dateEnd";

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

    public Advert processAdvert(HttpServletRequest request)
    {
        String title = getFieldValue(request, TITLE_FIELD);
        String description = getFieldValue(request, DESCRIPTION_FIELD);
        String dateStart = getFieldValue(request, DATE_START_FIELD);
        String dateEnd = getFieldValue(request, DATE_END_FIELD);

        Advert advert = new Advert();

        try {
            checkValues(advert, title, description, dateStart, dateEnd);

            if (errors.isEmpty()) {
                advertDao.create(advert);
                results = "Offre de prêt publiée.";
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

    private void checkValues(String title, String description, String dateStart) throws Exception
    {
        if (title == null || description == null || dateStart == null) {
            throw new FormValidationException("Les champs marqués d'une * ne peuvent être nuls");
        }
    }

    private void checkValues(Advert advert, String title, String description, String dateStart, String dateEnd) throws Exception {
        try {
            checkValues(title, description, dateStart);
        } catch (FormValidationException e) {
            setError("", e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = formatter.parse(dateStart);

        advert.setTitle(title);
        advert.setDescription(description);
        advert.setDateStart(startDate);
        if (dateEnd != null) {
            Date endDate = formatter.parse(dateEnd);
            advert.setDateEnd(endDate);
        } else {
            advert.setDateEnd(null);
        }
        advert.getState().setStateName("available");
    }

    /*
     * Ajoute un message correspondant au champ spécifié à la map des erreurs.
     */
    private void setError(String field, String message)
    {
        errors.put(field, message);
    }

    /*
     * Méthode utilitaire qui retourne null si un champ est vide, et son contenu
     * sinon.
     */
    private static String getFieldValue(HttpServletRequest request, String fieldName)
    {
        String value = request.getParameter(fieldName);
        if (value == null || value.trim().length() == 0) {
            return null;
        } else {
            return value.trim();
        }
    }
}
