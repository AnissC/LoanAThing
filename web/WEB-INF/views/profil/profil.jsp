<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title>Mon compte</title>
        <link href="../../../inc/css/style.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/indexStyle.css" rel="stylesheet">
        <link href="../../../inc/css/buttonStyle.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="content">
        <div class="container">
            <div class="row" style="background-color: #ffffff">
                <h1><c:out value="${user.lastname}"/></h1>
                <h1><c:out value="${user.email}"/></h1>
            </div>

            <a class="btn btn-primary" href="/advert/requests">Mes annonces</a>
            <a class="btn btn-primary" href="/advert/requests">Demandes de prêt</a>
        </div>
    </jsp:attribute>
</lat:baseLayout>
