<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title>Statistiques</title>
        <link href="../../../inc/slideOnSideBar/css/component.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/backOfficeStyle.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/transformicons.css" rel="stylesheet">
        <link href="../../../inc/css/annonce.css" rel="stylesheet">
        <link href="../../../inc/css/buttonStyle.css" rel="stylesheet">
        <link href="../../../inc/css/modal.css" rel="stylesheet">
        <link href="../../../inc/css/statsStyle.css" rel="stylesheet">

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
                                            <h1 class="text-center">Statistiques</h1>
                                        </div>
                                        <div class="col-xs-3">
                                            <div class="stat">
                                                <p class="text-center stat-number">10</p>
                                                <p class="text-center stat-description">Pret au total</p>
                                            </div>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="stat">
                                                <canvas class="center-block" id="userWhoBorrowedChart" width="500" height="200"></canvas>
                                                <p class="text-center legend" id="userWhoBorrowedChart-legend"><i class="fa fa-square"></i> Utilisateurs ayant déjà emprunté</p>
                                            </div>
                                        </div>
                                        <div class="col-xs-3">
                                            <div class="stat">
                                                <p class="text-center stat-number">8</p>
                                                <p class="text-center stat-description">Annnonces en ligne</p>
                                            </div>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="stat">
                                                <canvas class="center-block" id="loanNumberLineChart" width="500" height="200"></canvas>
                                                <p class="legend text-center">Nombre de prêts par mois</p>
                                            </div>
                                        </div>
                                        <div class="col-xs-3">
                                            <div class="stat">
                                                <p class="text-center stat-number">15</p>
                                                <p class="text-center stat-description">Avis utilisateur</p>
                                            </div>
                                        </div>
                                        <div class="col-xs-3">
                                            <div class="stat">
                                                <p class="text-center">
                                                    <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star"></i> <i class="fa fa-star-half-o"></i> <i class="fa fa-star-o"></i></p>
                                                <p class="text-center stat-description">Note moyenne</p>
                                            </div>
                                        </div>
                                        <div class="col-xs-3">
                                            <div class="stat">
                                                <p class="text-center stat-number">3</p>
                                                <p class="text-center stat-description">Prets en cours</p>
                                            </div>
                                        </div>
                                        <div class="col-xs-3">
                                            <div class="stat">
                                                <p class="text-center stat-number">0,2</p>
                                                <p class="text-center">Pret par mois par utilisateur</p>
                                            </div>
                                        </div>
                                        <div class="col-xs-6">
                                            <div class="stat last-stat">
                                                <canvas class="center-block" id="advertByCategory" width="500" height="200"></canvas>
                                                <p class="legend text-center">Nombres d'annonce par catégories</p>
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
        <script src="../../../inc/js/Chart.min.js"></script>

        <script>
            transformicons.add('.tcon', {
                transform: "mouseover",
                revert: "mouseout"
            });
        </script>

        <script>


            var userWhoBorrowedCanvas = document.getElementById("userWhoBorrowedChart").getContext("2d");
            var userWhoBorrowedData = [
                {
                    value: 40,
                    color:"#F7464A",
                    highlight: "#FF5A5E",
                    label: "Utilisateur ayant déjà emprunté"
                },
                {
                    value: 60,
                    color: "#46BFBD",
                    highlight: "#5AD3D1",
                    label: "Utilisateur n'ayant jamais emprunté"
                }
            ]
            var userWhoBorrowedChart = new Chart(userWhoBorrowedCanvas).Doughnut(userWhoBorrowedData, {
                segmentStrokeColor : "transparent",
            });

            var loanNumberCanvas = document.getElementById("loanNumberLineChart").getContext("2d");
            var loanNumberData = {
                labels: ["Janvier", "Février", "Mars", "Avril", "Mai", "Juin", "Juillet"],
                datasets: [
                    {
                        label: "Nombre de prêts",
                        fillColor: "rgba(220,220,220,0.2)",
                        strokeColor: "rgba(220,220,220,1)",
                        pointColor: "rgba(220,220,220,1)",
                        pointStrokeColor: "#fff",
                        pointHighlightFill: "#fff",
                        pointHighlightStroke: "rgba(220,220,220,1)",
                        data: [65, 59, 80, 81, 56, 55, 40]
                    }
                ]
            };
            var myLineChart = new Chart(loanNumberCanvas).Line(loanNumberData,{
                scaleGridLineColor : "rgba(255,255,255,.05)",
                scaleLineColor: "rgba(255,255,255,.05)",
            });

            var advertByCategoryCanvas = document.getElementById("advertByCategory").getContext("2d");
            var advertByCategoryData = {
                labels: ["Info", "Maison", "Sport", "Bricolage", "Chaussures", "Livres", "Vétements"],
                datasets: [
                    {
                        label: "data",
                        fillColor: "rgba(220,220,220,0.5)",
                        strokeColor: "rgba(220,220,220,0.8)",
                        highlightFill: "rgba(220,220,220,0.75)",
                        highlightStroke: "rgba(220,220,220,1)",
                        data: [90, 30, 53, 81, 56, 20, 70]
                    }
                ]
            };
            var advertByCategoryChart = new Chart(advertByCategoryCanvas).Bar(advertByCategoryData);


            $(function(){
                $('.stat').matchHeight();
            });
        </script>
    </jsp:attribute>
</lat:baseLayout>
