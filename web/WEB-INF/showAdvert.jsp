<%@ page pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title><c:out value="${advert.title}"/></title>
    <link type="text/css" rel="stylesheet" href="../form.css" />
</head>
<body>
    <h1><c:out value="${advert.title}"/></h1>
    <p><c:out value="${advert.description}"/></p>
    <p><c:out value="${advert.dateStart}"/></p>
    <p><c:out value="${advert.dateEnd}"/></p>
</body>
</html>
