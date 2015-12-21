<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title>Demandes de prêt</title>
        <link href="../../../inc/css/style.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/slideOnSideBar/css/component.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/multi-level-menu/component.css" rel="stylesheet">
        <link href="../../../inc/css/multi-level-menu/demo.css" rel="stylesheet">
        <link href="../../../inc/css/multi-level-menu/organicfoodicons.css" rel="stylesheet">
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
                                    <div class="row">
                                        <c:forEach var="pendingRequest"  items="${requestScope['pendingRequests']}" >
                                            <c:forEach var="apply"  items="${pendingRequest}" >
                                                <h1><c:out value="${apply.dateStart}"/></h1>
                                                <h1><c:out value="${apply.dateEnd}"/></h1>
                                                <h1><c:out value="${apply.user.lastname}"/></h1>
                                                <h1><c:out value="${apply.advert.title}"/></h1>
                                                <h1><c:out value="${apply.advert.user.lastname}"/></h1>
                                                <p><c:out value="${apply.advert.description}"/></p>
                                                <p><c:out value="${apply.advert.dateStart}"/></p>
                                                <p><c:out value="${apply.advert.dateEnd}"/></p>
                                            </c:forEach>
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
