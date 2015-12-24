<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title><c:out value="${advert.title}"/></title>
        <link href="../../../inc/css/style.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/slideOnSideBar/css/component.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/transformicons.css" rel="stylesheet">
        <link href="../../../inc/css/annonce.css" rel="stylesheet">
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

                                <button type="button" class="tcon tcon-menu--arrow tcon-menu--arrowleft" aria-label="toggle menu" data-effect="st-effect-4">
                                    <span class="tcon-menu__lines" aria-hidden="true"></span>
                                    <span class="tcon-visuallyhidden">toggle menu</span>
                                </button>

                                <div class="container">
                                    <h1 class="text-center">Mes annonces</h1>
                                    <div class="row" id="annonces">
                                        <c:forEach items="${adverts}" var="advert">
                                            <div class="col-xs-3">
                                                <div class="advert text-center">
                                                    <div class="annonce-image">
                                                        <img src="../../../inc/images/advert/<c:out value="${advert.image}"/>" alt="" class="img-responsive center-block">
                                                    </div>
                                                    <div class="annonce-title" style="margin-bottom: 20px">
                                                        <h2 class=""><c:out value="${advert.title}" /></h2>
                                                    </div>
                                                    <div class="annonce-description">
                                                        <p><c:out value="${advert.description}" /></p>
                                                    </div>

                                                    <p><c:out value="${advert.dateStart}" /> <i class="fa fa-arrow-right"></i> <c:out value="${advert.dateEnd}" /></p>

                                                    <div class="row">
                                                        <div class="col-xs-12">
                                                            <a href="">
                                                                <button type="button" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--custom">
                                                                    <span>Modifier</span>
                                                                    <i class="fa fa-pencil button__icon"></i>
                                                                </button>
                                                            </a>
                                                        </div>
                                                        <div class="col-xs-12">
                                                            <a href="">
                                                                <button id="btn-delete" type="button" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--danger">
                                                                    <span>Supprimer</span>
                                                                    <i class="fa fa-trash-o button__icon"></i>
                                                                </button>
                                                            </a>
                                                        </div>
                                                    </div>
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
                $('.annonce-description').matchHeight();
                $('.annonce-image').matchHeight();
            });
        </script>
    </jsp:attribute>
</lat:baseLayout>
