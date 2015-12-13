<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Demandes de prêt</title>
    <link href="../inc/bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../inc/font-awesome-4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
    <link href="../inc/css/style.css" rel='stylesheet' type='text/css'>
    <link href="../inc/css/indexStyle.css" rel="stylesheet">
    <link href="../inc/css/buttonStyle.css" rel="stylesheet">
</head>
<body>
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
<script src="../inc/js/jquery-1.11.3.min.js"></script>
<script src="../inc/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
</body>
</html>