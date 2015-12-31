<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title>Back Office</title>
        <link href="../../../inc/slideOnSideBar/css/component.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/backOfficeStyle.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/transformicons.css" rel="stylesheet">
        <link href="../../../inc/css/annonce.css" rel="stylesheet">
        <link href="../../../inc/css/buttonStyle.css" rel="stylesheet">
        <link href="../../../inc/css/modal.css" rel="stylesheet">

      </jsp:attribute>
      <jsp:attribute name="content">
        <div id="st-container" class="st-container">

            <%@ include file="/WEB-INF/views/menuBackOffice.jsp" %>

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
                                            <h1 class="text-center">Back Office</h1>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <div class="col-xs-4">
                                            <div class="row">
                                                <div class="col-xs-8 col-xs-offset-2">
                                                    <a href="/backoffice/report/users">
                                                        <div class="btn-bo">
                                                            <h1 class="text-center"><i class="fa fa-user-secret"></i></h1>
                                                            <p class="text-center">Utilisateurs Signales</p>
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4">
                                            <div class="row">
                                                <div class="col-xs-8 col-xs-offset-2">
                                                    <a href="/backoffice/report/adverts">
                                                        <div class="btn-bo">
                                                            <h1 class="text-center"><i class="fa fa-comment-o"></i></h1>
                                                            <p class="text-center">Annonces Signalees</p>
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4">
                                            <div class="row">
                                                <div class="col-xs-8 col-xs-offset-2">
                                                    <a href="/backoffice/stats">
                                                        <div class="btn-bo">
                                                            <h1 class="text-center"><i class="fa fa-bar-chart"></i></h1>
                                                            <p class="text-center">Statistiques</p>
                                                        </div>
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4">
                                            <div class="row">
                                                <div class="col-xs-8 col-xs-offset-2">

                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-xs-4">
                                            <div class="row">
                                                <div class="col-xs-8 col-xs-offset-2">
                                                    <a href="/backoffice/suspended/adverts">
                                                        <div class="btn-bo">
                                                            <h1 class="text-center"><i class="fa fa-ban"></i></h1>
                                                            <p class="text-center">Annonces suspendues</p>
                                                        </div>
                                                    </a>
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

    </jsp:attribute>
    <jsp:attribute name="js">
        <script src="../../../inc/slideOnSideBar/js/classie.js"></script>
        <script src="../../../inc/slideOnSideBar/js/sidebarEffects.js"></script>
        <script src="../../../inc/js/transformicon.js"></script>
        <script src="../../../inc/jquery-match-height-master/jquery.matchHeight-min.js"></script>
        <script src="../../../inc/js/konami.js"></script>

        <script>
            transformicons.add('.tcon', {
                transform: "mouseover",
                revert: "mouseout"
            });
        </script>
    </jsp:attribute>
</lat:baseLayout>
