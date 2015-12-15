<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title><c:out value="${advert.title}"/></title>
        <link href="../../../inc/css/style.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/indexStyle.css" rel="stylesheet">
        <link href="../../../inc/css/buttonStyle.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="content">
        <div class="container" style="background-color: #ffffff">
            <h1>Mes annonces</h1>
            <div class="row">
                <c:forEach items="${adverts}" var="advert">
                    <h1><c:out value="${advert.title}"/></h1>
                    <h1><c:out value="${advert.user.lastname}"/></h1>
                    <h1><c:out value="${advert.id}"/></h1>
                    <p><c:out value="${advert.description}"/></p>
                    <p><c:out value="${advert.dateStart}"/></p>
                    <p><c:out value="${advert.dateEnd}"/></p>
                </c:forEach>
            </div>
        </div>
    </jsp:attribute>
</lat:baseLayout>
