<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<div class="container">
    <div class="row">
        <div class="col-lg-6 col-md-8 col-sm-10 col-xs-12 col-lg-offset-3 col-md-offset-2 col-sm-offset-1 col-xs-offset-0">
            <div class="panel" id="panel-welcome">
                <div class="panel-heading">
                    <div class="row">
                        <div class="col-xs-12">
                            <img src="/images/LatLogoWhite.png" alt="" class="center-block img-responsive">
                        </div>
                    </div>
                </div>
                <div class="panel-body">
                    <div class="row">
                        <div class="col-xs-6">
                            <a href="<c:url value="/register" />" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--custom">
                                <i class="fa fa-pencil-square-o button__icon"></i>
                                <span>S'inscrire</span>
                            </a>
                        </div>
                        <div class="col-xs-6">
                            <a href="<c:url value="/login" />" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--custom">
                                <span>Se connecter</span>
                                <i class="fa fa-sign-in button__icon"></i>
                            </a>
                        </div>
                    </div>
                </div>
                <%-- Vérification de la présence d'un objet utilisateur en session --%>
                <c:if test="${!empty sessionScope.userSession}">
                    <%-- Si l'utilisateur existe en session, alors on affiche son adresse email. --%>
                    <p class="succes">Vous êtes connecté(e) avec l'adresse : ${sessionScope.userSession.email}</p>
                </c:if>
            </div>
        </div>
    </div>
</div>