<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title>Prêts en cours</title>
        <link href="../../../inc/css/style.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/slideOnSideBar/css/component.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/transformicons.css" rel="stylesheet">
        <link href="../../../inc/css/annonce.css" rel="stylesheet">
        <link href="../../../inc/css/buttonStyle.css" rel="stylesheet">
        <link href="../../../inc/css/modal.css" rel="stylesheet">
        <link href="../../../inc/css/loanStyle.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="content">

        <div id="st-container" class="st-container">
            <%@ include file="/WEB-INF/views/menu.jsp" %>
            <div class="st-pusher">
                <div class="st-content">
                    <div class="st-content-inner">
                        <div class="main clearfix">
                            <div id="st-trigger-effects" class="column">
                                <button type="button" class="tcon tcon-menu--arrow tcon-menu--arrowleft" aria-label="toggle menu" data-effect="st-effect-4">
                                    <span class="tcon-menu__lines" aria-hidden="true"></span>
                                    <span class="tcon-visuallyhidden">toggle menu</span>
                                </button>
                                <div class="container">
                                    <div class="row">
                                        <c:choose>
                                            <c:when test="${fn:length(loansOffered) eq 0 && fn:length(loansAsked) eq 0}">
                                                <div class="col-xs-12">
                                                    <h1 class="text-center">Aucun pret ni emprunt en cours</h1>
                                                </div>
                                            </c:when>
                                            <c:when test="${fn:length(loansOffered) gt 0}">
                                                <div class="col-xs-12">
                                                    <h1 class="text-center">Vos Prets</h1>
                                                </div>
                                                <c:forEach var="loan"  items="${loansOffered}" >
                                                    <div class="col-xs-3">
                                                        <div class="advert text-center">
                                                            <div class="annonce-image">
                                                                <img src="../../../inc/images/advert/<c:out value="${loan.apply.advert.image}"/>" alt="" class="img-responsive center-block">
                                                            </div>
                                                            <div class="annonce-title" style="margin-bottom: 20px">
                                                                <h2 class=""><c:out value="${loan.apply.advert.title}" /></h2>
                                                            </div>
                                                            <div class="row">
                                                                <div class="col-xs-12">
                                                                    <a href="<c:url value="/advert/view"><c:param name="id" value="${loan.id}" /></c:url>">
                                                                        <button type="button" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--custom">
                                                                            <span>VOODOO</span>
                                                                            <i class="fa fa-search button__icon"></i>
                                                                        </button>
                                                                    </a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                            <c:when test="${fn:length(loansAsked) gt 0}">
                                                <div class="col-xs-12">
                                                    <h1 class="text-center">Vos Emprunts</h1>
                                                </div>
                                                <c:forEach var="loan"  items="${loansAsked}" >
                                                    <div class="col-xs-3">
                                                        <div class="advert text-center">
                                                            <div class="annonce-image">
                                                                <img src="../../../inc/images/advert/<c:out value="${loan.apply.advert.image}"/>" alt="" class="img-responsive center-block">
                                                            </div>
                                                            <div class="annonce-title" style="margin-bottom: 20px">
                                                                <h2 class=""><c:out value="${loan.apply.advert.title}" /></h2>
                                                            </div>

                                                            <div class="row">
                                                                <div class="col-xs-12">
                                                                    <div class="contact text-center">
                                                                        <p>Vous pouvez dès à présent prendre contact avec <c:out value="${loan.apply.advert.user.firstname}"/> afin de convenir d'une date de rendez-vous.</p>
                                                                        <p><i class="fa fa-envelope-o"></i> Email : <c:out value="${loan.apply.advert.user.email}"/></p>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12">
                                                                    <div class="code text-center">
                                                                        <p><c:out value="${loan.code}"/></p>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12">
                                                                    <p class="code-description">Vous devrez fournir le code ci dessus à <c:out value="${loan.apply.advert.user.firstname}"/> en échange de l'objet emprunté afin de démarrer la procédure de prêt.</p>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                        </c:choose>
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

            $(function() {
                $('.annonce-title').matchHeight();
                $('.annonce-image').matchHeight();
            });


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
