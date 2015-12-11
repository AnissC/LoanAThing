<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>Publier une offre de prêt</title>
    </head>
    <body>
        <form method="post" action="<c:url value="/advert/add" />">
            <fieldset>
                <legend>Publier une offre de prêt</legend>

                <label for="title">Titre <span class="requis">*</span></label>
                <input type="text" id="title" name="title" value="<c:out value="${advert.title}"/>" maxlength="60" />
                <span class="error">${form.errors['title']}</span>
                <br />

                <label for="description">Description <span class="requis">*</span></label>
                <input type="text" id="description" name="description" value="${advert.description}" maxlength="300" />
                <span class="error">${form.errors['description']}</span>
                <br />

                <label for="dateStart">Date de début <span class="requis">*</span></label>
                <input type="date" id="dateStart" name="dateStart" value="${advert.dateStart}" />
                <span class="error">${form.errors['dateStart']}</span>
                <br />

                <label for="dateEnd">Date de fin</label>
                <input type="date" id="dateEnd" name="dateEnd" value="${advert.dateEnd}" />
                <span class="error">${form.errors['dateEnd']}</span>
                <br />

                <label>Catégorie</label>
                <select name="categoryId" class="form-control">
                    <c:forEach var="category"  items="${requestScope['categories']}" >
                        <option value="<c:url value="${category.id}"/>"><c:url value="${category.name}"/></option>
                    </c:forEach>
                </select>
                <span class="error">${form.errors['category']}</span>
                <br />

                <input type="submit" value="AddAdvert" class="sansLabel" />
                <br />
                
                <p class="${empty form.errors ? 'success' : 'error'}">${form.results}</p>
            </fieldset>
        </form>
    </body>
</html>
