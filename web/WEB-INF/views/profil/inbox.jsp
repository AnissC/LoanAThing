<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Contact</title>
    <link href="../../../inc/bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../../inc/font-awesome-4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
    <link href="../../../inc/css/style.css" rel='stylesheet' type='text/css'>
    <link href="../../../inc/slideOnSideBar/css/component.css" rel='stylesheet' type='text/css'>
    <link href="../../../inc/css/multi-level-menu/component.css" rel="stylesheet">
    <link href="../../../inc/css/multi-level-menu/demo.css" rel="stylesheet">
    <link href="../../../inc/css/multi-level-menu/organicfoodicons.css" rel="stylesheet">
    <link href="../../../inc/css/transformicons.css" rel="stylesheet">
    <link href="../../../inc/css/buttonStyle.css" rel="stylesheet">
    <link href="../../../inc/css/inbox.css" rel="stylesheet">
</head>
<body>
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
                                    <h1 class="text-center">Contacter un administrateur</h1>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-xs-6 col-xs-offset-3">
                                    <div class="inbox">
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <input type="text" placeholder="Objet du message" class="form-control">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-12">
                                                <textarea class="form-control" name="inbox-content" id="inbox-content" placeholder="Veuillez saisir votre message" cols="30" rows="10"></textarea>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-6">
                                                <button type="button" class=" btn btn-block button button--naira button--round-s button--border-thin button--naira--danger">
                                                    <span>Annuler</span>
                                                    <i class="fa fa-trash-o button__icon"></i>
                                                </button>
                                            </div>
                                            <div class="col-xs-6">
                                                <button type="submit" class="pull-right btn btn-block button button--naira button--round-s button--border-thin button--naira--success">
                                                    <span>Valider</span>
                                                    <i class="fa fa-check button__icon"></i>
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
<script src="../../../inc/js/jquery-1.11.3.min.js"></script>
<script src="../../../inc/slideOnSideBar/js/classie.js"></script>
<script src="../../../inc/slideOnSideBar/js/sidebarEffects.js"></script>
<script src="../../../inc/js/transformicon.js"></script>
<script src="../../../inc/jquery-match-height-master/jquery.matchHeight-min.js"></script>
<script src="../../../inc/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>

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
