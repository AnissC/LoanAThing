<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title>Connexion</title>
        <link href="../../../inc/css/indexStyle.css" rel="stylesheet">
        <link href="../../../inc/css/inputfield.css" rel="stylesheet">
        <link href="../../../inc/css/buttonStyle.css" rel="stylesheet">
    </jsp:attribute>
    <jsp:attribute name="content">
        <div class="container">
            <div class="row">
                <div class="col-xs-6 col-xs-offset-3">
                    <form method="post" action="<c:url value="/login" />">
                        <div class="panel" id="panel-register">
                            <div class="panel-heading text-center">
                                <h2>Connexion</h2>
                            </div>
                            <div class="panel-body">
                                <div class="row">
                                    <div class="col-xs-6 ">
                                        <div class="col-lg-12 input--shoko">
                                            <input class="input__field input__field--shoko" type="email" id="email" name="email"
                                                   value="<c:out value="${user.email}"/>" size="20" maxlength="60"/>
                                            <label class="input__label input__label--shoko" for="email">
                                                <span class="input__label-content input__label-content--shoko">Email</span>
                                            </label>
                                            <svg class="graphic graphic--shoko" width="300%" height="100%" viewBox="0 0 1200 60"
                                                 preserveAspectRatio="none">
                                                <path d="M0,56.5c0,0,298.666,0,399.333,0C448.336,56.5,513.994,46,597,46c77.327,0,135,10.5,200.999,10.5c95.996,0,402.001,0,402.001,0"/>
                                                <path d="M0,2.5c0,0,298.666,0,399.333,0C448.336,2.5,513.994,13,597,13c77.327,0,135-10.5,200.999-10.5c95.996,0,402.001,0,402.001,0"/>
                                            </svg>
                                            <span class="error">${form.errors['email']}</span>
                                        </div>
                                    </div>
                                    <div class="col-lg-6">
                                        <div class="col-xs-12 input--shoko">
                                            <input class="input__field input__field--shoko" type="password" id="password"
                                                   name="password" value="" size="20" maxlength="20"/>
                                            <label class="input__label input__label--shoko" for="password">
                                                <span class="input__label-content input__label-content--shoko">Mot de passe</span>
                                            </label>
                                            <svg class="graphic graphic--shoko" width="300%" height="100%" viewBox="0 0 1200 60"
                                                 preserveAspectRatio="none">
                                                <path d="M0,56.5c0,0,298.666,0,399.333,0C448.336,56.5,513.994,46,597,46c77.327,0,135,10.5,200.999,10.5c95.996,0,402.001,0,402.001,0"/>
                                                <path d="M0,2.5c0,0,298.666,0,399.333,0C448.336,2.5,513.994,13,597,13c77.327,0,135-10.5,200.999-10.5c95.996,0,402.001,0,402.001,0"/>
                                            </svg>
                                            <span class="error">${form.errors['password']}</span>
                                        </div>
                                    </div>
                                </div>
                                <div class="row form-margin-top">
                                    <div class="col-xs-6">
                                        <div class="back btn btn-block button button--naira button--round-s button--border-thin button--naira-up button--naira--danger">
                                            <i class="fa fa-undo button__icon"></i>
                                            <span>Annuler</span>
                                        </div>
                                    </div>
                                    <div class="col-xs-6">
                                        <button type="submit" class="btn btn-block button button--naira button--round-s button--border-thin button--naira--success">
                                            <span>Se connecter</span>
                                            <i class="fa fa-check button__icon"></i>
                                        </button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </jsp:attribute>
    <jsp:attribute name="js">
        <script src="../../../inc/js/classie.js"></script>
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

            /**
             * Quickfix pour les inputfields quand les credentials sont enregistrés dans le browser
             */
            $(".input__field").each(function () {
                $(this).focus();
                $(this).blur();
            });

            $('.back').click(function(){
                parent.history.back();
                return false;
            });
        </script>
    </jsp:attribute>
</lat:baseLayout>
