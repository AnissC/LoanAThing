<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title>Mon compte</title>
        <link href="../../../inc/css/style.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/slideOnSideBar/css/component.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/transformicons.css" rel="stylesheet">
        <link href="../../../inc/css/userProfil.css" rel="stylesheet">
        <link href="../../../inc/css/buttonStyle.css" rel="stylesheet">
        <link href="../../../inc/css/modal.css" rel="stylesheet">
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

                                <button type="button" class="tcon tcon-menu--arrow tcon-menu--arrowleft" aria-label="toggle menu"  data-effect="st-effect-4">
                                    <span class="tcon-menu__lines" aria-hidden="true"></span>
                                    <span class="tcon-visuallyhidden">toggle menu</span>
                                </button>

                                <div class="container">
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <h1 class="text-center">Profil</h1>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-12">
                                            <div class="profil">
                                                <div class="row">
                                                    <div class="col-xs-3">
                                                        <img src="../../../inc/images/user/<c:out value="${user.image}"/>" alt="" class="img-responsive">
                                                    </div>
                                                    <div class="col-xs-9">
                                                        <div class="row">
                                                            <div class="col-xs-6">
                                                                <p><span>Prenom : </span><c:out value="${user.firstname}" /><p>
                                                                <p><span>Nom : </span><c:out value="${user.lastname}" /><p>
                                                                <p><span>Nom d'utilisateur : </span><c:out value="${user.nickname}" /><p>
                                                                <p><span>Adresse email : </span><c:out value="${user.email}" /><p>
                                                            </div>
                                                            <div class="col-xs-6">
                                                                <p><span>Adresse : </span><c:out value="${user.address}" /><p>
                                                                <p><span>Ville : </span><c:out value="${user.city}" /><p>
                                                                <p><span>Code postal: </span><c:out value="${user.zipCode}" /><p>
                                                                <p><span>Date de naissance : </span><c:out value="${user.birthday}" /><p>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>

                                                <div class="row">
                                                    <div class="col-xs-3">
                                                        <button data-toggle="modal" data-target="#modificationModal" type="submit" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--custom">
                                                            <span>Modifier le profil</span>
                                                            <i class="fa fa-pencil button__icon"></i>
                                                        </button>
                                                    </div>
                                                    <div class="col-xs-3">
                                                        <a href="<c:url value="/profil/adverts" />" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--custom">
                                                            <span>Gérer les annonces</span>
                                                            <i class="fa fa-shopping-basket button__icon"></i>
                                                        </a>
                                                    </div>
                                                    <div class="col-xs-3">
                                                        <a href="<c:url value="/profil/requests" />" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--custom">
                                                            <span>Gérer les Demandes</span>
                                                            <i class="fa fa-comment-o button__icon"></i>
                                                        </a>
                                                    </div>
                                                    <div class="col-xs-3">
                                                        <button data-toggle="modal" data-target="#modal-delete" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--danger">
                                                            <span>Supprimer le compte</span>
                                                            <i class="fa fa-trash-o button__icon"></i>
                                                        </button>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div><!-- /main -->
                    </div><!-- /st-content-inner -->
                </div><!-- /st-content -->
            </div><!-- /st-pusher -->
        </div><!-- /st-container -->
        <!-- /view -->

        <!-- Modal -->
        <div class="modal fade" id="modificationModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title">Modification du profil</h4>
                    </div>
                    <form method="post" class="form-horizontal" action="<c:url value="/profil" />">
                        <div class="modal-body">
                            <div class="row">
                                <div class="col-xs-6">
                                    <input type="text" class="form-control" placeholder="Prénom" name="firstname" value="<c:out value="${user.firstname}"/>"/>
                                </div>
                                <div class="col-xs-6">
                                    <input type="text" class="form-control" placeholder="Nom" name="lastname" value="<c:out value="${user.lastname}"/>"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6">
                                    <input type="text" class="form-control" placeholder="Nom d'utilisateur" name="nickname" value="<c:out value="${user.nickname}"/>"/>
                                </div>
                                <div class="col-xs-6">
                                    <input type="text" class="form-control" placeholder="Email" name="email" value="<c:out value="${user.email}"/>"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6">
                                    <input type="date" class="form-control" placeholder="Date de naissance" name="birthday" value="<c:out value="${user.birthday}"/>"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-12">
                                    <input type="text" class="form-control" placeholder="Adresse" name="address" value="<c:out value="${user.address}"/>"/>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6">
                                    <input type="text" class="form-control" placeholder="Ville" name="city" value="<c:out value="${user.city}"/>"/>
                                </div>
                                <div class="col-xs-6">
                                    <input type="text" class="form-control" placeholder="Code Postal" name="zipcode" value="<c:out value="${user.zipCode}"/>"/>
                                </div>
                            </div>
                            <input type="hidden" name="id" value="${user.id}">
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
                                    <button type="submit" class="button--nico button--nico--success">
                                        <span>Valider</span>
                                        <i class="fa fa-check button__icon"></i>
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>
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
                        <p class="text-center">Etes-vous sur de vouloir supprimer votre profil ?</p>
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
        <script>
            transformicons.add('.tcon', {
                transform: "mouseover",
                revert: "mouseout"
            });
            $(function() {
                $('.annonce-content').matchHeight();
            });
            $("img").click(function(){
                $("img").addClass("reda-pattern")
            });
        </script>
    </jsp:attribute>
</lat:baseLayout>
