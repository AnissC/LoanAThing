<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title>Abus Annonces</title>
        <link href="../../../inc/slideOnSideBar/css/component.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/backOfficeStyle.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/transformicons.css" rel="stylesheet">
        <link href="../../../inc/css/buttonStyle.css" rel="stylesheet">
        <link href="../../../inc/css/modal.css" rel="stylesheet">
        <link href="../../../inc/css/annonce.css" rel="stylesheet" type="text/css">
        <link href="../../../inc/toastr-master/build/toastr.css" rel="stylesheet" type="text/css">


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
                                            <h1 class="text-center">Annonces Suspendues</h1>
                                        </div>
                                    </div>
                                    <div class="row">
                                        <c:forEach var="suspendedAdvert"  items="${suspendedAdverts}" >
                                            <div class="col-xs-3">
                                                <div class="advert text-center advert-suspended">
                                                    <p class="hidden id"><c:out value="${suspendedAdvert.id}"/></p>
                                                    <div class="annonce-image">
                                                        <img src="../../../inc/images/advert/<c:out value="${suspendedAdvert.image}"/>" alt="" class="img-responsive center-block">
                                                    </div>
                                                    <div class="annonce-title">
                                                        <h2 class=""><c:out value="${suspendedAdvert.title}" /></h2>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-xs-12">
                                                            <a href="<c:url value="/advert/view"><c:param name="id" value="${suspendedAdvert.id}" /></c:url>">
                                                                <button type="button" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--custom">
                                                                    <span>Details</span>
                                                                    <i class="fa fa-search button__icon"></i>
                                                                </button>
                                                            </a>
                                                        </div>
                                                        <div class="col-xs-12">
                                                            <button type="button" class="btn-reauthorize btn btn-block button button--naira button--round-s button--border-thin button--naira--success">
                                                                <span>Retablir</span>
                                                                <i class="fa fa-eye button__icon"></i>
                                                            </button>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
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
        <script src="../../../inc/toastr-master/build/toastr.min.js"></script>

        <script>
            $(function() {
                $('.annonce-title').matchHeight();
                $('.annonce-description').matchHeight();
                $('.annonce-image').matchHeight();
            });
            transformicons.add('.tcon', {
                transform: "mouseover",
                revert: "mouseout"
            });

            $(".btn-reauthorize").click(function(){
                var id = $(this).parents('.advert').find(".id").text();
                var $advert = $(this).parents('.advert').parent()
                $.ajax({
                    type: "GET",
                    url: "/backoffice/report/advert/unban",
                    dataType: "text",
                    data: {id: id},
                    success: function () {
                        toastr.success('Annonce remise en ligne');
                        $advert.remove();
                    }
                })
            })
        </script>
    </jsp:attribute>
</lat:baseLayout>

