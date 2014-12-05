<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="org.springframework.security.core.context.SecurityContextHolder" %>
<html>
<head>
    <title>Access denied</title>
    <link rel="stylesheet" type="text/css" href="/resources/css/bootstrap.css"/>
	<link rel="stylesheet" type="text/css" media="screen" href="resources/css/style.css"/>
</head>
<body>
    <div class="container">
	    <jsp:include page="../parts/body.jsp" />
	    <h1 id="banner">Unauthorized</h1>
	    <hr/>
        <h2>Access denied! You haven't access to this resource!</h2>
    </div>
</body>
</html>