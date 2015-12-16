<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title>Demandes de prêt</title>
        <link href="../../../inc/css/style.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/indexStyle.css" rel="stylesheet">
        <link href="../../../inc/css/buttonStyle.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="content">
        <div class="container">
            <div class="row" style="background-color: #ffffff">
                <c:forEach var="pendingRequest"  items="${requestScope['pendingRequests']}" >
                    <h1><c:out value="${pendingRequest.dateStart}"/></h1>
                    <h1><c:out value="${pendingRequest.dateEnd}"/></h1>
                    <h1><c:out value="${pendingRequest.user.lastname}"/></h1>
                    <h1><c:out value="${pendingRequest.advert.title}"/></h1>
                    <h1><c:out value="${pendingRequest.advert.user.lastname}"/></h1>
                    <p><c:out value="${pendingRequest.advert.description}"/></p>
                    <p><c:out value="${pendingRequest.advert.dateStart}"/></p>
                    <p><c:out value="${pendingRequest.advert.dateEnd}"/></p>
                </c:forEach>
            </div>

            <a class="btn btn-primary" href="/advert/requests">Voir les demandes de prêt</a>
        </div>
    </jsp:attribute>
</lat:baseLayout>
