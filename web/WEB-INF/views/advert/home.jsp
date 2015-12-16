<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib tagdir="/WEB-INF/tags" prefix="lat" %>

<lat:baseLayout>
    <jsp:attribute name="header">
        <title>Toutes les offres de prêt</title>
        <link href="../../../inc/css/style.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/slideOnSideBar/css/component.css" rel='stylesheet' type='text/css'>
        <link href="../../../inc/css/multi-level-menu/component.css" rel="stylesheet">
        <link href="../../../inc/css/multi-level-menu/demo.css" rel="stylesheet">
        <link href="../../../inc/css/multi-level-menu/organicfoodicons.css" rel="stylesheet">
        <link href="../../../inc/css/transformicons.css" rel="stylesheet">
        <link href="../../../inc/css/annonce.css" rel="stylesheet">
        <link href="../../../inc/css/buttonStyle.css" rel="stylesheet">
        <link href="../../../inc/css/modal.css" rel="stylesheet">

      </jsp:attribute>
      <jsp:attribute name="content">
        <div id="st-container" class="st-container">

            <%@ include file="/WEB-INF/views/menu.jsp" %>

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
                                        <div class="col-xs-6 col-xs-offset-3">
                                            <h1 class="text-center">Nouvelles Offres</h1>
                                        </div>
                                        <div class="col-xs-3">
                                            <button data-toggle="modal" data-target="#addAdvert" type="submit" class="pull-right btn btn-block button button--naira button--round-s button--border-thin button--naira--custom">
                                                <span>Ajouter une annonce</span>
                                                <i class="fa fa-plus button__icon"></i>
                                            </button>
                                        </div>
                                    </div>

                                    <div class="row">
                                        <c:forEach items="${adverts}" var="advert">
                                            <div class="col-xs-3">
                                                <div class="advert text-center">
                                                    <img src="../../../inc/images/girafe.png" alt="" class="img-responsive">
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
          <!-- Modal -->
                <div class="modal fade" id="addAdvert" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                                <h4 class="modal-title" id="myModalLabel">Publier une offre de prêt :</h4>
                            </div>
                            <form method="post" class="form-horizontal" action="<c:url value="/advert/add" />">
                                <div class="modal-body">
                                    <div class="form-group">
                                        <label for="title" class="col-sm-2 control-label">Titre</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="title" id="title" value="${advert.title}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="description" class="col-sm-2 control-label">Description</label>
                                        <div class="col-sm-10">
                                            <input type="text" class="form-control" name="description" id="description" value="${advert.description}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="dateStart" class="col-sm-2 control-label">Date de début</label>
                                        <div class="col-sm-10">
                                            <input type="date" class="form-control" name="dateStart" id="dateStart" value="${advert.dateStart}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="dateEnd" class="col-sm-2 control-label">Date de fin</label>
                                        <div class="col-sm-10">
                                            <input type="date" class="form-control" name="dateEnd" id="dateEnd" value="${advert.dateEnd}">
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label class="col-sm-2 control-label">Catégorie</label>
                                        <div class="col-sm-10">
                                            <select name="categoryId" class="form-control">
                                                <c:forEach var="category"  items="${requestScope['categories']}" >
                                                    <option class="control-label" value="<c:url value="${category.id}"/>"><c:url value="${category.name}"/></option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                </div>
                                <div class="modal-footer">
                                    <div class="row">
                                        <div class="col-xs-6">
                                            <button data-dismiss="modal" type="button" class=" btn btn-block button button--naira button--round-s button--border-thin button--naira--danger">
                                                <span>Cancel </span>
                                                <i class="fa fa-undo button__icon"></i>
                                            </button>
                                        </div>
                                        <div class="col-xs-6">
                                            <button type="submit" class="pull-right btn btn-block button button--naira button--round-s button--border-thin button--naira--success">
                                                <span>Valider </span>
                                                <i class="fa fa-undo button__icon"></i>
                                            </button>
                                        </div>
                                    </div>
                                </div>
                            </form>
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

        <script>
            transformicons.add('.tcon', {
                transform: "mouseover",
                revert: "mouseout"
            });
            $(function() {
                $('.annonce-content').matchHeight();
            });
        </script>
    </jsp:attribute>
</lat:baseLayout>
