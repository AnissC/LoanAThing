<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title><c:out value="${advert.title}"/></title>
        <link href="../../../inc/css/style.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/indexStyle.css" rel="stylesheet">
        <link href="../../../inc/css/buttonStyle.css" rel="stylesheet">
        <link href="../../../inc/css/advertView.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="content">
        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <h2 class="text-center">Details de l'annonce</h2>
                </div>
                <div class="col-xs-12">
                    <div class="annonce">
                        <div class="row">
                            <div class="col-xs-4">
                                <img src="../../../inc/images/girafe.png" alt="" class="img-responsive">
                            </div>
                            <div class="col-xs-8">
                                <h2><c:out value="${advert.title}"/> <span><c:out value="${advert.category.name}"/></span></h2>
                                <p>Proposé par : <c:out value="${advert.user.lastname}"/> <c:out value="${advert.user.firstname}"/></p>
                                <p><c:out value="${advert.description}"/></p>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <h3 class="text-center">Date de disponibilite</h3>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <h4 class="text-center"><span><i class="fa fa-calendar-o"></i></span> <span><c:out value="${advert.dateStart}"/></span> <span><i class="fa fa-arrow-right"></i></span> <span><i class="fa fa-calendar-o"></i></span> <span><c:out value="${advert.dateEnd}"/></span></h4>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-xs-12">
                                <c:choose>
                                    <c:when test="${advert.user.id != sessionScope.userSession.id}">
                                        <!-- Button trigger modal -->
                                        <button data-toggle="modal" data-target="#loan" type="button" class="center-block btn btn-block button button--naira button--round-s button--border-thin button--naira--success pull-right">
                                            <span>Emprunter</span>
                                            <i class="fa fa-check button__icon"></i>
                                        </button>
                                    </c:when>
                                    <c:otherwise>
                                        <button data-toggle="modal" data-target="#update" type="button" class="center-block btn btn-block button button--naira button--round-s button--border-thin button--naira--success pull-right">
                                            <span>Modifier</span>
                                            <i class="fa fa-exchange button__icon"></i>
                                        </button>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <c:choose>
                <c:when test="${advert.user.id != sessionScope.userSession.id}">
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
                </c:when>
                <c:otherwise>
                    <!-- Modal -->
                    <div class="modal fade" id="update" tabindex="-1" role="dialog" aria-labelledby="update">
                        <div class="modal-dialog" role="document">
                            <div class="modal-content">
                                <div class="modal-header">
                                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                    <h4 class="modal-title">Modifier l'annonce : <c:out value="${advert.title}"/></h4>
                                </div>
                                <form method="post" class="form-horizontal" action="<c:url value="/advert/view" />">
                                    <div class="modal-body">
                                        <div class="form-group">
                                            <label for="title" class="col-sm-2 control-label">Titre</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="title" id="title" value="${advert.title}">
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="description" class="col-sm-2 control-label">Description</label>
                                            <div class="col-sm-10">
                                                <input type="text" class="form-control" name="description" id="description" value="${advert.description}">
                                            </div>
                                        </div>
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
                                        <div class="form-group">
                                            <label class="col-sm-2 control-label">Catégorie</label>
                                            <div class="col-sm-10">
                                                <select name="categoryId" class="form-control">
                                                    <c:forEach var="category"  items="${requestScope['categories']}" >
                                                        <option class="control-label" value="<c:url value="${category.id}"/>"><c:url value="${category.name}"/></option>
                                                    </c:forEach>
                                                </select>
                                            </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="isPublish" class="col-sm-2 control-label">Disponible en ligne</label>
                                            <div class="col-sm-10">
                                                <input type="checkbox" class="form-control" name="isPublish" id="isPublish" value="${advert.publish}" checked="checked">
                                            </div>
                                        </div>
                                        <input type="hidden" name="id" value="${advert.id}">
                                        <input type="hidden" name="isSuspend" value="${advert.suspend}">
                                    </div>
                                    <div class="modal-footer">
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <button data-dismiss="modal" type="button" class=" btn btn-block button button--naira button--round-s button--border-thin button--naira--danger">
                                                    <span>Cancel </span>
                                                    <i class="fa fa-undo button__icon"></i>
                                                </button>
                                            </div>
                                            <div class="col-xs-6">
                                                <button type="submit" class="pull-right btn btn-block button button--naira button--round-s button--border-thin button--naira--success">
                                                    <span>Mettre à jour </span>
                                                    <i class="fa fa-check button__icon"></i>
                                                </button>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </jsp:attribute>
</lat:baseLayout>