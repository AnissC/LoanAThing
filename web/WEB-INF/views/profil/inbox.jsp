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
                <c:forEach var="inboxRequest"  items="${requestScope['inboxRequests']}" >
                    <h1><c:out value="${inboxRequest.dateStart}"/></h1>
                    <h1><c:out value="${inboxRequest.dateEnd}"/></h1>
                    <h1><c:out value="${inboxRequest.user.lastname}"/></h1>
                    <h1><c:out value="${inboxRequest.advert.title}"/></h1>
                    <h1><c:out value="${inboxRequest.advert.user.lastname}"/></h1>
                    <p><c:out value="${inboxRequest.advert.description}"/></p>
                    <p><c:out value="${inboxRequest.advert.dateStart}"/></p>
                    <p><c:out value="${inboxRequest.advert.dateEnd}"/></p>
                </c:forEach>
            </div>
            <a class="btn btn-primary" href="/advert/requests">Voir les demandes de prêt</a>
        </div>
    </jsp:attribute>
</lat:baseLayout>
