<%@ attribute name="header" fragment="true" %>
<%@ attribute name="content" fragment="true" %>
<%@ attribute name="js" fragment="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <link href="../../inc/bootstrap-3.3.6-dist/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../inc/font-awesome-4.5.0/css/font-awesome.min.css" rel="stylesheet">
    <link href='https://fonts.googleapis.com/css?family=Roboto:400,300,100' rel='stylesheet' type='text/css'>
    <jsp:invoke fragment="header"/>
</head>

<body>
    <jsp:invoke fragment="content"/>

    <script src="../../inc/js/jquery-1.11.3.min.js"></script>
    <script src="../../inc/bootstrap-3.3.6-dist/js/bootstrap.min.js"></script>
    <jsp:invoke fragment="js"/>
</body>
</html>
