<nav class="st-menu st-effect-4" id="menu-4">
    <a href="/home"><h2 class="icon icon-lab"><img src="../../../inc/images/LatLogoWhite.png" alt="" class="img-responsive center-block"></h2></a>
    <ul>
        <li><a class="" href="/home"><i class="fa fa-shopping-basket"></i> Offres </a></li>
        <li><a class="" href="/profil/requests"><i class="fa fa-commenting-o"></i> Demandes <span class="badge pull-right">?</span></a></li>
        <li><a class="" href="/profil/loans"><i class="fa fa-random"></i> Emprunts en cours <span class="badge pull-right">?</span></a></li>
        <li><a class="" href="/profil"><i class="fa fa-user"></i> Profil</a></li>
        <li><a class="" href="/contact"><i class="fa fa-question-circle"></i> Contact</a></li>
        <c:if test="${sessionScope.userSession.group.name == 'ADMIN' }">
            <li><a class="" href="/backoffice"><i class="fa fa-rocket"></i> Back Office</a></li>
        </c:if>
        <li><a class="" href="/logout"><i class="fa fa-power-off"></i> Deconnexion</a></li>
    </ul>
</nav>
