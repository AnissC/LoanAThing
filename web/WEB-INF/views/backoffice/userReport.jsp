<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title>Abus Utilisateurs</title>
        <link href="../../../inc/slideOnSideBar/css/component.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/backOfficeStyle.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/transformicons.css" rel="stylesheet">
        <link href="../../../inc/css/annonce.css" rel="stylesheet">
        <link href="../../../inc/css/buttonStyle.css" rel="stylesheet">
        <link href="../../../inc/css/modal.css" rel="stylesheet">
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
                                            <h1 class="text-center">Utilisateurs signalés</h1>
                                        </div>
                                        <c:forEach var="user" items="${reportedUsers}">
                                            <div class="col-xs-3">
                                                <div id="<c:out value="${user.id}"/>" class="advert text-center advert-reported">
                                                    <div class="nbSignalement">1</div>
                                                    <div class="annonce-image">
                                                        <img src="../../../inc/images/user/<c:out value="${user.image}"/>" alt="" class="img-responsive center-block">
                                                    </div>
                                                    <div class="annonce-title">
                                                        <h2><c:out value="${user.nickname}" /></h2>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col-xs-12">
                                                            <a href="<c:url value="#"><c:param name="userId" value="${user.id}" /></c:url>">
                                                                <button type="button" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--custom">
                                                                    <span>Voir Profil</span>
                                                                    <i class="fa fa-search button__icon"></i>
                                                                </button>
                                                            </a>
                                                        </div>
                                                        <div class="col-xs-12">
                                                            <button type="button" class="btn-ignore btn btn-block button button--naira button--round-s button--border-thin button--naira--success">
                                                                <span>Ignorer</span>
                                                                <i class="fa fa-eye-slash button__icon"></i>
                                                            </button>
                                                        </div>
                                                        <div class="col-xs-12">
                                                            <button type="button" class="btn-ban btn btn-block button button--naira button--round-s button--border-thin button--naira--danger">
                                                                <span>Bannir</span>
                                                                <i class="fa fa-ban button__icon"></i>
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
            transformicons.add('.tcon', {
                transform: "mouseover",
                revert: "mouseout"
            });

            $(".advert").each(function(){
                var id = $(this).attr('id');
                var $nbSignalement = $(this).find(".nbSignalement");
                if($(this).parent().siblings().find('#' + id) != null){
                    $(this).parent().siblings().find('#' + id).each(function(){
                        $(this).parent().remove();
                        $nbSignalement.html(parseInt($nbSignalement.html(), 10)+1);
                    })
                }
            });

            $(".btn-ignore").click(function(){
                var id = $(this).parents('.advert').attr('id');
                var $advert = $(this).parents('.advert').parent();
                var url = "/backoffice/report/user/ignore";
                var toasterMessage = "Signalement ignoré";
                ajaxToasterCall(url, id, $advert, toasterMessage);
            });

            $(".btn-ban").click(function(){
                var id = $(this).parents('.advert').attr('id');
                var $advert = $(this).parents('.advert').parent();
                var url = "/backoffice/report/user/ban";
                var toasterMessage = "Utilisateur banni";
                ajaxToasterCall(url, id, $advert, toasterMessage);
            });

            function ajaxToasterCall(url, id, $advert, toasterMessage){
                $.ajax({
                    type: "GET",
                    url: url,
                    dataType: "text",
                    data: {userId: id},
                    success: function () {
                        toastr.success(toasterMessage);
                        $advert.remove();
                    }
                })
            };
        </script>
    </jsp:attribute>
</lat:baseLayout>
