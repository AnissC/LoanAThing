<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title>Prêts en cours</title>
        <link href="../../../inc/css/style.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/slideOnSideBar/css/component.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/transformicons.css" rel="stylesheet">
        <link href="../../../inc/css/buttonStyle.css" rel="stylesheet">
        <link href="../../../inc/css/modal.css" rel="stylesheet">
        <link href="../../../inc/css/loanStyle.css" rel="stylesheet">
        <link href="../../../inc/css/inputfield.css" rel="stylesheet">
        <link href="../../../inc/toastr-master/build/toastr.css" rel="stylesheet" type="text/css">
    </jsp:attribute>
    <jsp:attribute name="content">

        <div id="st-container" class="st-container">
            <%@ include file="/WEB-INF/views/menu.jsp" %>
            <div class="st-pusher">
                <div class="st-content">
                    <div class="st-content-inner">
                        <div class="main clearfix">
                            <div id="st-trigger-effects" class="column">
                                <button type="button" class="tcon tcon-menu--arrow tcon-menu--arrowleft" aria-label="toggle menu" data-effect="st-effect-4">
                                    <span class="tcon-menu__lines" aria-hidden="true"></span>
                                    <span class="tcon-visuallyhidden">toggle menu</span>
                                </button>
                                <div class="container">
                                    <div class="row">
                                        <c:choose>
                                            <c:when test="${fn:length(loansOffered) eq 0 && fn:length(loansAsked) eq 0}">
                                                <div class="col-xs-12">
                                                    <h1 class="text-center">Aucun pret ni emprunt en cours</h1>
                                                </div>
                                            </c:when>
                                            <c:when test="${fn:length(loansOffered) gt 0}">
                                                <div class="col-xs-12">
                                                    <h1 class="text-center">Vos Prets</h1>
                                                </div>
                                                <c:forEach var="loan"  items="${loansOffered}" >
                                                    <div class="col-xs-12">
                                                        <div class="loan">
                                                            <div class="row">
                                                                <div class="col-xs-3">
                                                                    <div class="loan-image">
                                                                        <img src="../../../inc/images/advert/<c:out value="${loan.apply.advert.image}"/>" alt="" class="img-responsive ">
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-9">
                                                                    <div class="row">
                                                                        <div class="col-xs-12">
                                                                            <div class="annonce-title" style="margin-bottom: 20px">
                                                                                <h2 class=""><c:out value="${loan.apply.advert.title}" /></h2>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-xs-12">
                                                                            <c:choose>
                                                                                <c:when test="${!loan.stateReturnCode}">
                                                                                    <p>Vous pouvez dès à présent prendre contact avec <c:out value="${loan.apply.user.nickname}"/> afin de convenir d'une date de rendez-vous.</p>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <p>Le pret avec <c:out value="${loan.apply.user.nickname}"/> est terminé</p>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                        <div class="col-xs-12">
                                                                            <p><i class="fa fa-envelope-o"></i> Email : <c:out value="${loan.apply.user.email}"/></p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12">
                                                                    <div class="row">
                                                                        <div class="col-xs-6 col-xs-offset-3">
                                                                            <div class="border-separator"></div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12">
                                                                    <div class="row">
                                                                        <c:choose>
                                                                            <c:when test="${loan.stateCode && !loan.stateReturnCode}">
                                                                                <div class="col-xs-12">
                                                                                    <p class="code-description text-center">Lors du rendez vous devrez échanger le code ci-dessous pour récuperer votre objet pour que <c:out value="${loan.apply.user.nickname}"/> puisse conclure la procédure de prêt.</p>
                                                                                </div>
                                                                                <div class="col-xs-4 col-xs-offset-4">
                                                                                    <div class="row">
                                                                                        <div class="col-xs-8 col-xs-offset-2">
                                                                                            <div class="code code-letter-spacing text-center">
                                                                                                <p><c:out value="${loan.returnCode}"/></p>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:when>
                                                                            <c:when test="${loan.stateReturnCode}">
                                                                                <div class="col-xs-12">
                                                                                    <p class="code-description text-center">Félicitation le prêt s'est déroulé avec succès. Vous pouvez dès à présent donner un avis sur <c:out value="${loan.apply.user.nickname}"/> ou le signaler si un problème a eu lieu pendant l'échange</p>
                                                                                </div>
                                                                                <div class="col-xs-4">
                                                                                    <div class="row">
                                                                                        <div class="col-xs-8 col-xs-offset-2">
                                                                                            <a href="/home">
                                                                                                <button class="btn btn-block button button--naira button--round-s button--border-thin button--naira--custom">
                                                                                                    <span>Accueil</span>
                                                                                                    <i class="fa fa-home button__icon"></i>
                                                                                                </button>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-4">
                                                                                    <div class="row">
                                                                                        <div class="col-xs-8 col-xs-offset-2">
                                                                                            <button class="btn btn-block button button--naira button--round-s button--border-thin button--naira--success">
                                                                                                <span>Evaluer</span>
                                                                                                <i class="fa fa-star-half-o button__icon"></i>
                                                                                            </button>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-4">
                                                                                    <div class="row">
                                                                                        <div class="col-xs-8 col-xs-offset-2">
                                                                                            <a href="<c:url value="/user/report"><c:param name="userId" value="${loan.apply.user.id}"/> </c:url>">
                                                                                                <p class="hidden id"><c:out value="${loan.apply.user.id}"/></p>
                                                                                                <button data-toggle="modal" data-target="#report" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--danger btn-report-modal">
                                                                                                    <span>Signaler</span>
                                                                                                    <i class="fa fa-exclamation-triangle button__icon"></i>
                                                                                                </button>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <div class="col-xs-12">
                                                                                     <p class="code-description text-center">Lors du rendez vous assurez vous que <c:out value="${loan.apply.user.nickname}"/> vous fournisse le code d'emprunt. Vous pourrez ensuite entrer le code dans le champs ci-dessous afin de valider le debut du pret.</p>
                                                                                </div>
                                                                                <div class="col-xs-4">
                                                                                    <div class="row">
                                                                                        <div class="col-xs-8 col-xs-offset-2">
                                                                                            <div class="code text-center">
                                                                                                <p>
                                                                                                    <span><c:out value="${fn:substring(loan.code, 0, 1)}"/></span>
                                                                                                    <span class="fake-to-blur"><img src="../../../inc/images/blurredfont.png" alt=""></span>
                                                                                                    <span><c:out value="${fn:substring(loan.code, 2, 3)}"/></span>
                                                                                                    <span class="fake-to-blur"><img src="../../../inc/images/blurredfont.png" alt=""></span>
                                                                                                    <span class="fake-to-blur"><img src="../../../inc/images/blurredfont.png" alt=""></span>
                                                                                                    <span class="fake-to-blur"><img src="../../../inc/images/blurredfont.png" alt=""></span>
                                                                                                    <span><c:out value="${fn:substring(loan.code, 6, 7)}"/></span>
                                                                                                    <span class="fake-to-blur"><img src="../../../inc/images/blurredfont.png" alt=""></span>
                                                                                                </p>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <form method="post" action="<c:url value="/loan/submit/code" />">
                                                                                    <div class="col-xs-4">
                                                                                        <div class="row">
                                                                                            <input class="hidden" type="text" id="loanOfferedId" name="loanId" value="<c:out value="${loan.id}"/>"/>
                                                                                            <div class="col-xs-8 col-xs-offset-2 input--shoko">
                                                                                                <input class="input__field input__field--shoko" type="text" id="code" name="code"
                                                                                                       size="20" maxlength="8"/>
                                                                                                <label class="input__label input__label--shoko" for="code">
                                                                                                    <span class="input__label-content input__label-content--shoko">Code</span>
                                                                                                </label>
                                                                                                <svg class="graphic graphic--shoko" width="300%" height="100%" viewBox="0 0 1200 60"
                                                                                                     preserveAspectRatio="none">
                                                                                                    <path d="M0,56.5c0,0,298.666,0,399.333,0C448.336,56.5,513.994,46,597,46c77.327,0,135,10.5,200.999,10.5c95.996,0,402.001,0,402.001,0"/>
                                                                                                    <path d="M0,2.5c0,0,298.666,0,399.333,0C448.336,2.5,513.994,13,597,13c77.327,0,135-10.5,200.999-10.5c95.996,0,402.001,0,402.001,0"/>
                                                                                                </svg>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-xs-4">
                                                                                        <div class="row">
                                                                                            <div class="col-xs-8 col-xs-offset-2">
                                                                                                <button type="submit" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--success">
                                                                                                    <span>Soumettre</span>
                                                                                                    <i class="fa fa-check button__icon"></i>
                                                                                                </button>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </form>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                            <c:when test="${fn:length(loansAsked) gt 0}">
                                                <div class="col-xs-12">
                                                    <h1 class="text-center">Vos Emprunts</h1>
                                                </div>
                                                <c:forEach var="loan"  items="${loansAsked}" >
                                                    <div class="col-xs-12">
                                                        <div class="loan">
                                                            <div class="row">
                                                                <div class="col-xs-3">
                                                                    <div class="loan-image">
                                                                        <img src="../../../inc/images/advert/<c:out value="${loan.apply.advert.image}"/>" alt="" class="img-responsive ">
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-9">
                                                                    <div class="row">
                                                                        <div class="col-xs-12">
                                                                            <div class="annonce-title" style="margin-bottom: 20px">
                                                                                <h2 class=""><c:out value="${loan.apply.advert.title}" /></h2>
                                                                            </div>
                                                                        </div>
                                                                        <div class="col-xs-12">
                                                                            <c:choose>
                                                                                <c:when test="${!loan.stateReturnCode}">
                                                                                    <p>Vous pouvez dès à présent prendre contact avec <c:out value="${loan.apply.advert.user.nickname}"/> afin de convenir d'une date de rendez-vous.</p>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <p>Le pret avec <c:out value="${loan.apply.advert.user.nickname}"/> est terminé</p>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                        <div class="col-xs-12">
                                                                            <p><i class="fa fa-envelope-o"></i> Email : <c:out value="${loan.apply.advert.user.email}"/></p>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12">
                                                                    <div class="row">
                                                                        <div class="col-xs-6 col-xs-offset-3">
                                                                            <div class="border-separator"></div>
                                                                        </div>
                                                                    </div>
                                                                </div>
                                                                <div class="col-xs-12">
                                                                    <div class="row">
                                                                        <c:choose>
                                                                            <c:when test="${loan.stateCode && !loan.stateReturnCode}">
                                                                                <div class="col-xs-12">
                                                                                    <p class="code-description text-center">Lors du rendez vous devrez échanger l'objet contre le code en possession de <c:out value="${loan.apply.user.nickname}"/> pour que vous puissiez ensuite conclure la procédure de prê.t</p>
                                                                                </div>
                                                                                <div class="col-xs-4">
                                                                                    <div class="row">
                                                                                        <div class="col-xs-8 col-xs-offset-2">
                                                                                            <div class="code text-center">
                                                                                                <p>
                                                                                                    <span class="fake-to-blur"><img src="../../../inc/images/blurredfont.png" alt=""></span>
                                                                                                    <span class="fake-to-blur"><img src="../../../inc/images/blurredfont.png" alt=""></span>
                                                                                                    <span><c:out value="${fn:substring(loan.returnCode, 2, 3)}"/></span>
                                                                                                    <span class="fake-to-blur"><img src="../../../inc/images/blurredfont.png" alt=""></span>
                                                                                                    <span><c:out value="${fn:substring(loan.returnCode, 4, 5)}"/></span>
                                                                                                    <span class="fake-to-blur"><img src="../../../inc/images/blurredfont.png" alt=""></span>
                                                                                                    <span class="fake-to-blur"><img src="../../../inc/images/blurredfont.png" alt=""></span>
                                                                                                    <span><c:out value="${fn:substring(loan.returnCode, 7, 8)}"/></span>
                                                                                                </p>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <form method="post" action="<c:url value="/loan/submit/return-code"/>">
                                                                                    <div class="col-xs-4">
                                                                                        <div class="row">
                                                                                            <input class="hidden" type="text" id="loanAskedId" name="loanId" value="<c:out value="${loan.id}"/>"/>
                                                                                            <div class="col-xs-8 col-xs-offset-2 input--shoko">
                                                                                                <input class="input__field input__field--shoko" type="text" id="returnCode" name="code"
                                                                                                       size="20" maxlength="8"/>
                                                                                                <label class="input__label input__label--shoko" for="code">
                                                                                                    <span class="input__label-content input__label-content--shoko">Code</span>
                                                                                                </label>
                                                                                                <svg class="graphic graphic--shoko" width="300%" height="100%" viewBox="0 0 1200 60"
                                                                                                     preserveAspectRatio="none">
                                                                                                    <path d="M0,56.5c0,0,298.666,0,399.333,0C448.336,56.5,513.994,46,597,46c77.327,0,135,10.5,200.999,10.5c95.996,0,402.001,0,402.001,0"/>
                                                                                                    <path d="M0,2.5c0,0,298.666,0,399.333,0C448.336,2.5,513.994,13,597,13c77.327,0,135-10.5,200.999-10.5c95.996,0,402.001,0,402.001,0"/>
                                                                                                </svg>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                    <div class="col-xs-4">
                                                                                        <div class="row">
                                                                                            <div class="col-xs-8 col-xs-offset-2">
                                                                                                <button type="submit" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--success">
                                                                                                    <span>Soumettre</span>
                                                                                                    <i class="fa fa-check button__icon"></i>
                                                                                                </button>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </form>
                                                                            </c:when>
                                                                            <c:when test="${loan.stateReturnCode}">
                                                                                <div class="col-xs-12">
                                                                                    <p class="code-description text-center">Félicitation le prêt s'est déroulé avec succès. Vous pouvez dès à présent donner un avis sur <c:out value="${loan.apply.advert.user.nickname}"/> ou le signaler si un problème a eu lieu pendant l'échange</p>
                                                                                </div>
                                                                                <div class="col-xs-4">
                                                                                    <div class="row">
                                                                                        <div class="col-xs-8 col-xs-offset-2">
                                                                                            <a href="/home">
                                                                                                <button class="btn btn-block button button--naira button--round-s button--border-thin button--naira--custom">
                                                                                                    <span>Accueil</span>
                                                                                                    <i class="fa fa-home button__icon"></i>
                                                                                                </button>
                                                                                            </a>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-4">
                                                                                    <div class="row">
                                                                                        <div class="col-xs-8 col-xs-offset-2">
                                                                                            <button class="btn btn-block button button--naira button--round-s button--border-thin button--naira--success">
                                                                                                <span>Evaluer</span>
                                                                                                <i class="fa fa-star-half-o button__icon"></i>
                                                                                            </button>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                                <div class="col-xs-4">
                                                                                    <div class="row">
                                                                                        <div class="col-xs-8 col-xs-offset-2">
                                                                                            <p class="hidden id"><c:out value="${loan.apply.advert.user.id}"/></p>
                                                                                            <button data-toggle="modal" data-target="#report" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--danger btn-report-modal">
                                                                                                <span>Signaler</span>
                                                                                                <i class="fa fa-exclamation-triangle button__icon"></i>
                                                                                            </button>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:when>
                                                                            <c:otherwise>
                                                                                <div class="col-xs-12">
                                                                                    <p class="code-description text-center">Lors du rendez vous devrez échanger le code ci-dessous pour récuperer l'objet  et que <c:out value="${loan.apply.advert.user.nickname}"/> puisse demarer la procédure de prêt.</p>
                                                                                </div>
                                                                                <div class="col-xs-4 col-xs-offset-4">
                                                                                    <div class="row">
                                                                                        <div class="col-xs-8 col-xs-offset-2">
                                                                                            <div class="code code-letter-spacing text-center">
                                                                                                <p><c:out value="${loan.code}"/></p>
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </div>
                                                                            </c:otherwise>
                                                                        </c:choose>
                                                                    </div>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </c:forEach>
                                            </c:when>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>


        <div class="modal fade" id="report" tabindex="-1" role="dialog" aria-labelledby="report">
            <div class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        <h4 class="modal-title"> Etes vous sur de vouloir signaler cet Utilisateur ?</h4>
                    </div>
                    <div class="modal-body">
                        <p>En cas d'abus d'utilisation de la fonction signalement votre compte pourra être suspendu.</p>
                        <p>Si vous avez un doute veuillez consulter la <a href="#" style="color: #0d3349">FAQ</a> pour savoir dans quels cas signaler une annonce.</p>
                    </div>
                    <div class="modal-footer">
                        <div class="row">
                            <div class="col-xs-6">
                                <button data-dismiss="modal" type="button" class="button--nico button--nico--danger">
                                    <span>Cancel </span>
                                    <i class="fa fa-undo button__icon"></i>
                                </button>
                            </div>
                            <p class="hidden" id="id"></p>
                            <div class="col-xs-6">
                                <button id="btn-report" type="submit" class="button--nico--success button--nico">
                                    <span>Signaler</span>
                                    <i class="fa fa-exclamation-triangle button__icon"></i>
                                </button>
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
        <script src="../../../inc/toastr-master/build/toastr.min.js"></script>
        <script>
            (function () {
                // trim polyfill : https://developer.mozilla.org/en-US/docs/Web/JavaScript/Reference/Global_Objects/String/Trim
                if (!String.prototype.trim) {
                    (function () {
                        // Make sure we trim BOM and NBSP
                        var rtrim = /^[\s\uFEFF\xA0]+|[\s\uFEFF\xA0]+$/g;
                        String.prototype.trim = function () {
                            return this.replace(rtrim, '');
                        };
                    })();
                }

                [].slice.call(document.querySelectorAll('input.input__field')).forEach(function (inputEl) {
                    // in case the input is already filled..
                    if (inputEl.value.trim() !== '') {
                        classie.add(inputEl.parentNode, 'input--filled');
                    }

                    // events:
                    inputEl.addEventListener('focus', onInputFocus);
                    inputEl.addEventListener('blur', onInputBlur);
                });

                function onInputFocus(ev) {
                    classie.add(ev.target.parentNode, 'input--filled');
                }

                function onInputBlur(ev) {
                    if (ev.target.value.trim() === '') {
                        classie.remove(ev.target.parentNode, 'input--filled');
                    }
                }


            })();

            $('.btn-report-modal').click(function(){
               $('#id').html($(this).siblings(".id").html());
            });

            $("#btn-report").click(function(){
                var id = $("#id").text();
                $.ajax({
                    type: "GET",
                    url: "/user/report",
                    dataType: "text",
                    data: {userId: id},
                    success: function () {
                        toastr.success('Utilisateur signalé');
                        $('#report').modal('toggle');
                    }
                })
            })

        </script>
    </jsp:attribute>
</lat:baseLayout>
