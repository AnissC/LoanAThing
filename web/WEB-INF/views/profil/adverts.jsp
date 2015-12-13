<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title><c:out value="${advert.title}"/></title>
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
        <h1><c:out value="${advert.title}"/></h1>
        <h1><c:out value="${advert.user.lastname}"/></h1>
        <h1><c:out value="${advert.id}"/></h1>
        <p><c:out value="${advert.description}"/></p>
        <p><c:out value="${advert.dateStart}"/></p>
        <p><c:out value="${advert.dateEnd}"/></p>
    </div>
    <!-- Button trigger modal -->
    <button type="button" class="btn btn-primary btn-lg" data-toggle="modal" data-target="#loan">
        Emprunter
    </button>

    <!-- Modal -->
    <div class="modal fade" id="loan" tabindex="-1" role="dialog" aria-labelledby="loan">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel">Faire une demande d'emprunt pour : <c:out value="${advert.title}"/></h4>
                </div>
                <form class="form-horizontal" method="post" action="advert">
                    <h4 class="text-center">Veuillez sélectionner les dates souhaitées pour l'emprunt.</h4>
                    <div class="modal-body">
                        <div class="form-group">
                            <label for="dateStart" class="col-sm-2 control-label">Date de début</label>
                            <div class="col-sm-10">
                                <input type="date" class="form-control" name="dateStart" id="dateStart" value="${advert.dateStart}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="dateEnd" class="col-sm-2 control-label">Date de fin</label>
                            <div class="col-sm-10">
                                <input type="date" class="form-control" name="dateEnd" id="dateEnd" value="${advert.dateEnd}">
                            </div>
                        </div>
                    </div>
                    <input type="hidden" name="advertId" value="${advert.id}">
                    <div class="modal-footer">
                        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                        <button type="submit" class="btn btn-success">Envoyer</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<script src="../inc/js/jquery-1.11.3.min.js"></script>
<script src="../inc/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
</body>
</html>
