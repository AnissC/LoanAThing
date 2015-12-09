<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>LAT</title>
    </head>
    <body>
        <p>Bienvenue sur la plateforme LAT.</p>
        <%-- Vérification de la présence d'un objet utilisateur en session --%>
        <c:if test="${!empty sessionScope.userSession}">
            <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
            <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.userSession.email}</p>
        </c:if>
        <ul>
            <li><a href="<c:url value="/register" />">S'inscrire</a></li>
            <li><a href="<c:url value="/login" />">Se connecter</a></li>
            <li><a href="<c:url value="/logout" />">Se déconnecter</a></li>
            <li><a href="<c:url value="/advert/add" />">Publier une offre de prêt</a></li>
        </ul>
    </body>
</html>