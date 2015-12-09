<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <title>LAT</title>
        <link href="/bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="/font-awesome-4.5.0/css/font-awesome.min.css" rel="stylesheet">
        <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
        <link href="css/style.css" rel='stylesheet' type='text/css'>

        <c:choose>
            <c:when test="${!empty sessionScope.userSession}">
                <script src="/js/modernizr-custom.js"></script>
                <script src="/js/classie.js"></script>
                <script src="/js/dummydata.js"></script>
                <script src="/js/main.js"></script>
                <link href="/css/multi-level-menu/component.css" rel="stylesheet">
                <link href="/css/multi-level-menu/demo.css" rel="stylesheet">
                <link href="/css/multi-level-menu/organicfoodicons.css" rel="stylesheet">
            </c:when>

            <c:otherwise>
                <link href="/css/indexStyle.css" rel="stylesheet">
                <link href="/css/buttonStyle.css" rel="stylesheet">
            </c:otherwise>
        </c:choose>
    </head>
    <body>

    <c:choose>
        <c:when test="${!empty sessionScope.userSession}"> <%-- On check si il y a un utilisateur courant --%>
            <div class="container">
                <!-- Blueprint header -->
                <header class="bp-header cf">
                    <div class="dummy-logo">
                        <div class="header-logo">
                            <img src="/images/LatLogoHeader.png" alt="" class="img-responsive center-block" style="max-width: 33%;">
                        </div>
                        <h2 class="dummy-heading">Loan a Thing</h2>
                    </div>
                    <li><a href="<c:url value="/logout" />">Se déconnecter</a></li>
                </header>
                <button class="action action--open" aria-label="Open Menu"><span class="icon icon--menu"></span></button>
                <nav id="ml-menu" class="menu">
                    <button class="action action--close" aria-label="Close Menu"><span class="icon icon--cross"></span></button>
                    <div class="menu__wrap">
                        <ul data-menu="main" class="menu__level">
                            <li class="menu__item"><a class="menu__link" data-submenu="submenu-1" href="#">Offres</a></li>
                            <li class="menu__item"><a class="menu__link" data-submenu="submenu-2" href="#">Demandes</a></li>
                            <li class="menu__item"><a class="menu__link" data-submenu="submenu-3" href="#">Profil</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Contact</a></li>
                        </ul>
                        <!-- Submenu 1 -->
                        <ul data-menu="submenu-1" class="menu__level">
                            <li class="menu__item"><a class="menu__link" href="#" data-submenu="submenu-1-1">Informatique</a></li>
                            <li class="menu__item"><a class="menu__link" href="#" data-submenu="submenu-1-2">Electroménager</a></li>
                            <li class="menu__item"><a class="menu__link" href="#" data-submenu="submenu-1-3">Sport</a></li>
                            <li class="menu__item"><a class="menu__link" href="#" >Bricolage</a></li>
                            <li class="menu__item"><a class="menu__link" href="#" data-submenu="submenu-1-4">Chaussures</a></li>
                            <li class="menu__item"><a class="menu__link" href="#" data-submenu="submenu-1-5">Livres</a></li>
                        </ul>
                        <!-- Submenu 1-1 -->
                        <ul data-menu="submenu-1-1" class="menu__level">
                            <li class="menu__item"><a class="menu__link" href="#">Ordinateur</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Souris</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Clavier</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Écran</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Raspberry Pi</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Cables</a></li>
                        </ul>

                        <!-- Submenu 1-2 -->
                        <ul data-menu="submenu-1-2" class="menu__level">
                            <li class="menu__item"><a class="menu__link" href="#">Ustensiles de cuisine</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Fours</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Micro-Onde</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Grilles Pain</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Machines à café</a></li>
                        </ul>

                        <!-- Submenu 1-3 -->
                        <ul data-menu="submenu-1-3" class="menu__level">
                            <li class="menu__item"><a class="menu__link" href="#">Sports d'hiver</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Sports sur pelouse</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Sports sur glace</a></li>
                        </ul>

                        <!-- Submenu 1-4 -->
                        <ul data-menu="submenu-1-4" class="menu__level">
                            <li class="menu__item"><a class="menu__link" href="#">Chaussures hommme</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Chaussures femme</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Chaussures à talon</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Chaussures de sport</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Chaussures d'hiver</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Chaussures d'été</a></li>
                        </ul>

                        <!-- Submenu 1-5 -->
                        <ul data-menu="submenu-1-5" class="menu__level">
                            <li class="menu__item"><a class="menu__link" href="#">Livres scolaires</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Littérature</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Poésie</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Théâtre</a></li>
                        </ul>

                        <!-- Submenu 2 -->
                        <ul data-menu="submenu-2" class="menu__level">
                            <li class="menu__item"><a class="menu__link" href="#">Citrus Fruits</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Berries</a></li>
                            <li class="menu__item"><a class="menu__link" data-submenu="submenu-2-1" href="#">Special Selection</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Tropical Fruits</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Melons</a></li>
                        </ul>
                        <!-- Submenu 2-1 -->
                        <ul data-menu="submenu-2-1" class="menu__level">
                            <li class="menu__item"><a class="menu__link" href="#">Exotic Mixes</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Wild Pick</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Vitamin Boosters</a></li>
                        </ul>
                        <!-- Submenu 3 -->
                        <ul data-menu="submenu-3" class="menu__level">
                            <li class="menu__item"><a class="menu__link" href="#">Voir son profil</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Modifier son profil</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Supprimer son profil</a></li>
                            <li class="menu__item"><a class="menu__link" href="#">Consulter ses offres</a></li>
                        </ul>
                      
                    </div>
                </nav>
                <div class="content">
                    <p class="info">Please choose a category</p>
                    <!-- Ajax loaded content here -->
                </div>
            </div>
            <!-- /view -->
            <script src="js/classie.js"></script>
            <script src="js/dummydata.js"></script>
            <script src="js/main.js"></script>
            <script>
                (function() {
                    var menuEl = document.getElementById('ml-menu'),
                            mlmenu = new MLMenu(menuEl, {
                                // breadcrumbsCtrl : true, // show breadcrumbs
                                // initialBreadcrumb : 'all', // initial breadcrumb text
                                backCtrl : false, // show back button
                                // itemsDelayInterval : 60, // delay between each menu item sliding animation
                                onItemClick: loadDummyData // callback: item that doesn´t have a submenu gets clicked - onItemClick([event], [inner HTML of the clicked item])
                            });

                    // mobile menu toggle
                    var openMenuCtrl = document.querySelector('.action--open'),
                            closeMenuCtrl = document.querySelector('.action--close');

                    openMenuCtrl.addEventListener('click', openMenu);
                    closeMenuCtrl.addEventListener('click', closeMenu);

                    function openMenu() {
                        classie.add(menuEl, 'menu--open');
                    }

                    function closeMenu() {
                        classie.remove(menuEl, 'menu--open');
                    }

                    // simulate grid content loading
                    var gridWrapper = document.querySelector('.content');

                    function loadDummyData(ev, itemName) {
                        ev.preventDefault();

                        closeMenu();
                        gridWrapper.innerHTML = '';
                        classie.add(gridWrapper, 'content--loading');
                        setTimeout(function() {
                            classie.remove(gridWrapper, 'content--loading');
                            gridWrapper.innerHTML = '<ul class="products">' + dummyData[itemName] + '<ul>';
                        }, 700);
                    }
                })();
            </script>
        </c:when>

        <c:otherwise> <%-- Sinon on demande une authentification  --%>
            <jsp:include page="authentification.jsp"></jsp:include>
        </c:otherwise>
    </c:choose>
        <%-- <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.userSession.email}</p> --%>

    </body>
</html>