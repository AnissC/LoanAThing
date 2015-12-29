<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                                            <h1 class="text-center">Demandes en attente</h1>
                                        </div>
                                        <c:forEach var="pendingRequest"  items="${requestScope['pendingRequests']}" >
                                            <div class="col-xs-6 col-xs-offset-3">
                                                <div class="request-table">
                                                    <h4 class="text-center"><c:out value="${pendingRequest[0].advert.title}"/></h4></td>
                                                    <table class="table">
                                                        <tr>
                                                            <td><p>Utilisateur</p></td>
                                                            <td><p>Date de début</p></td>
                                                            <td><p>Date de fin</p></td>
                                                            <td><p class="text-center">Accepter</p></td>
                                                            <td><p class="text-center">Refuser</p></td>
                                                        </tr>
                                                        <c:forEach var="apply"  items="${pendingRequest}" >
                                                        <tr>
                                                            <td><p><c:out value="${apply.user.lastname}"/></p></td>
                                                            <td><p><c:out value="${apply.dateStart}"/></p></td>
                                                            <td><p><c:out value="${apply.dateEnd}"/></p></td>
                                                            <td><div class="btn btn-block btn-success"><i class="fa fa-check"></i></div></td>
                                                            <td><div class="btn btn-block btn-danger"><i class="fa fa-ban"></i></div></td>
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
    </jsp:attribute>
    <jsp:attribute name="js">
        <script src="../../../inc/slideOnSideBar/js/classie.js"></script>
        <script src="../../../inc/slideOnSideBar/js/sidebarEffects.js"></script>
        <script src="../../../inc/js/transformicon.js"></script>
        <script src="../../../inc/jquery-match-height-master/jquery.matchHeight-min.js"></script>
        <script src="../../../inc/js/konami.js"></script>
    </jsp:attribute>
</lat:baseLayout>
