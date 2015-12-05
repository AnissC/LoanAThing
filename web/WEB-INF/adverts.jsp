<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>Toutes les offres de prêt</title>
    <link type="text/css" rel="stylesheet" href="../form.css" />
</head>
<body>
    <h1>Toutes les offres de prêt :</h1>
    <c:forEach var="advert"  items="${requestScope['adverts']}" >
        <a href="<c:url value="${'/show-advert?id='}${advert.id}"/>">
            <div>
                <p><c:out value='${advert.title}'/></p>
            </div>
        </a>
    </c:forEach>
</body>
</html>
