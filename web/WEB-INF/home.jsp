<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Toutes les offres de prêt</title>
    <link href="../inc/bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../inc/font-awesome-4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
    <link href="../inc/css/style.css" rel='stylesheet' type='text/css'>
    <link href="../inc/slideOnSideBar/css/component.css" rel='stylesheet' type='text/css'>
    <link href="../inc/css/multi-level-menu/component.css" rel="stylesheet">
    <link href="../inc/css/multi-level-menu/demo.css" rel="stylesheet">
    <link href="../inc/css/multi-level-menu/organicfoodicons.css" rel="stylesheet">
    <link href="../inc/css/transformicons.css" rel="stylesheet">
    <link href="../inc/css/annonce.css" rel="stylesheet">
    <link href="../inc/css/buttonStyle.css" rel="stylesheet">
</head>
<body>



<div id="st-container" class="st-container">

            <nav class="st-menu st-effect-4" id="menu-4">
                <a href="/home"><h2 class="icon icon-lab"><img src="../inc/images/LatLogoWhite.png" alt="" class="img-responsive center-block"></h2></a>
                <ul>
                    <li><a class="icon" href="/home"><i class="fa fa-shopping-basket"></i> Offres <span class="badge pull-right">${nbAdverts}</span></a></li>
                    <!--<li><a class="icon" href="/home"><i class="fa fa-comment-o"></i> Demandes<span class="badge pull-right">??</span></a></li>-->
                    <li><a class="icon" href="/user"><i class="fa fa-user"></i> Profil</a></li>
                    <li><a class="icon" href="/home"><i class="fa fa-question-circle"></i> Contact</a></li>
                    <li><a class="icon" href="/logout"><i class="fa fa-power-off"></i> Deconnexion</a></li>
                </ul>
            </nav>

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
                                    <h1 class="text-center">Nouvelles Offres</h1>
                                </div>
                            </div>
                            <div class="row">
                                <c:forEach items="${adverts}" var="advert">
                                <div class="col-xs-3">
                                    <div class="advert text-center">
                                        <img src="../inc/images/girafe.png" alt="" class="img-responsive">
                                        <div class="annonce-content" style="margin-bottom: 20px">
                                            <h2 class=""><c:out value="${advert.title}" /></h2>
                                            <p><c:out value="${advert.description}" /></p>
                                            <p><c:out value="${advert.dateStart}" /> <i class="fa fa-arrow-right"></i> <c:out value="${advert.dateEnd}" /></p>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <button type="submit" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--custom">
                                                    <span>Details</span>
                                                    <i class="fa fa-search button__icon"></i>
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
<!-- /view -->


<script src="../inc/js/jquery-1.11.3.min.js"></script>
<script src="../inc/slideOnSideBar/js/classie.js"></script>
<script src="../inc/slideOnSideBar/js/sidebarEffects.js"></script>
<script src="../inc/js/transformicon.js"></script>
<script src="../inc/jquery-match-height-master/jquery.matchHeight-min.js"></script>

<script>
    transformicons.add('.tcon', {
    transform: "mouseover",
    revert: "mouseout"
});
    $(function() {
        $('.annonce-content').matchHeight();
    });
</script>
</body>
</html>
