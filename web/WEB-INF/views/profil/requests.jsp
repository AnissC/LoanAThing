<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title>Demandes de prêt</title>
        <link href="../../../inc/css/style.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/slideOnSideBar/css/component.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/transformicons.css" rel="stylesheet">
        <link href="../../../inc/css/annonce.css" rel="stylesheet">
        <link href="../../../inc/css/buttonStyle.css" rel="stylesheet">
        <link href="../../../inc/css/modal.css" rel="stylesheet">
        <link href="../../../inc/css/pendingRequestStyle.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="content">
        <div id="st-container" class="st-container">

            <%@ include file="/WEB-INF/views/menu.jsp" %>

            <!-- content push wrapper -->
            <div class="st-pusher">
                <div class="st-content"><!-- this is the wrapper for the content -->
                    <div class="st-content-inner"><!-- extra div for emulating position:fixed of the menu -->
                        <div class="main clearfix">
                            <div id="st-trigger-effects" class="column">
                                <!-- <button class="sideBarButton" data-effect="st-effect-4"> <i class="fa fa-bars"></i></button>-->

                                <button type="button" class="tcon tcon-menu--arrow tcon-menu--arrowleft" aria-label="toggle menu" data-effect="st-effect-4">
                                    <span class="tcon-menu__lines" aria-hidden="true"></span>
                                    <span class="tcon-visuallyhidden">toggle menu</span>
                                </button>

                                <div class="container">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <c:choose>
                                                <c:when test="${fn:length(requestScope['pendingRequests']) gt 0}">
                                                    <h1 class="text-center">Demandes en attente</h1>
                                                </c:when>
                                                <c:otherwise>
                                                    <h1 class="text-center">Aucune demande en attente</h1>
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <c:forEach var="pendingRequest"  items="${requestScope['pendingRequests']}" >
                                            <div class="col-xs-6 col-xs-offset-3">
                                                <div class="request-table">
                                                    <h4 class="text-center"><c:out value="${pendingRequest[0].advert.title}"/></h4></td>
                                                    <table class="table">
                                                        <tr>
                                                            <td class="hidden">id</td>
                                                            <td><p>Utilisateur</p></td>
                                                            <td><p>Date de début</p></td>
                                                            <td><p>Date de fin</p></td>
                                                            <td><p class="text-center">Accepter</p></td>
                                                            <td><p class="text-center">Refuser</p></td>
                                                        </tr>
                                                        <c:forEach var="apply"  items="${pendingRequest}" >
                                                        <tr>
                                                            <td class="hidden id"><c:out value="${apply.id}"/></td>
                                                            <td><p><c:out value="${apply.user.lastname}"/></p></td>
                                                            <td><p><c:out value="${apply.dateStart}"/></p></td>
                                                            <td><p><c:out value="${apply.dateEnd}"/></p></td>
                                                            <td><div class="btn btn-block btn-success"><i class="fa fa-check"></i></div></td>
                                                            <td><div data-toggle="modal" data-target="#modal-delete" class="btn btn-block btn-danger delete"><i class="fa fa-ban"></i></div></td>
                                                        </tr>
                                                        </c:forEach>
                                                    </table>
                                                </div>
                                            </div>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="modal-delete" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title"><i class="fa fa-exclamation-circle"></i> Suppression du profil</h4>
                    </div>
                    <div class="modal-body">
                        <p class="text-center">Etes-vous sur de vouloir supprimer cette demande ?</p>
                    </div>
                    <div class="modal-footer">
                        <div class="row">
                            <div class="col-xs-6">
                                <button data-dismiss="modal" type="button" class="button--nico--danger button--nico">
                                    <span>Annuler</span>
                                    <i class="fa fa-trash-o button__icon"></i>
                                </button>
                            </div>
                            <div class="col-xs-6">
                                <button id="delete-profil" class="button--nico button--nico--success">
                                    <span>Valider</span>
                                    <i class="fa fa-check button__icon"></i>
                                </button>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </jsp:attribute>
    <jsp:attribute name="js">
        <script src="../../../inc/slideOnSideBar/js/classie.js"></script>
        <script src="../../../inc/slideOnSideBar/js/sidebarEffects.js"></script>
        <script src="../../../inc/js/transformicon.js"></script>
        <script src="../../../inc/jquery-match-height-master/jquery.matchHeight-min.js"></script>
        <script src="../../../inc/js/konami.js"></script>

        <script>
            $('.delete').click(function(){
                $(this).parent().siblings('.id').addClass('id-to-delete');
            });

            $('#modal-delete').on('hidden.bs.modal', function () {
                $('.id-to-delete').removeClass('id-to-delete');
            });

            $('#delete-profil').click(function(){
                var id = $('.id-to-delete').text();
                $.ajax({
                    type:"GET",
                    url: "/request/delete",
                    dataType: "text",
                    data: {id:id},
                    success: function(){
                        if($('.id-to-delete').parent().siblings().size() == 1){
                            $('.id-to-delete').parent().after("<tr><td colspan='5'><p class='text-center'>Aucune demande disponible pour cette offre</p></td></tr>");
                        }
                        $('.id-to-delete').parent().remove();
                        $("#modal-delete").modal('hide');
                    }
                })
            });
        </script>
    </jsp:attribute>
</lat:baseLayout>
