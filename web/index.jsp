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
        <ul>
            <li><a href="<c:url value="/register" />">S'inscrire</a></li>
            <li><a href="<c:url value="/login" />">Se connecter</a></li>
        </ul>
    </body>
</html>