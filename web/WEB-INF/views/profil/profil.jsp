<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Mon compte</title>
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
        <h1><c:out value="${user.lastname}"/></h1>
        <h1><c:out value="${user.email}"/></h1>
    </div>

    <a class="btn btn-primary" href="/advert/requests">Mes annonces</a>
    <a class="btn btn-primary" href="/advert/requests">Demandes de prêt</a>
</div>
<script src="../inc/js/jquery-1.11.3.min.js"></script>
<script src="../inc/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
</body>
</html>