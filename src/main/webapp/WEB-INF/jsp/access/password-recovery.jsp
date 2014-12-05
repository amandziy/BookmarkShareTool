<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Password recovery</title>
    <!--Connect styles from bootstrap-->
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/bootstrap.css"/>'>
    <style type="text/css">
        body {
            padding-top: 40px;
            padding-bottom: 40px;
            background-color: #f5f5f5;
        }
    </style>
    <link rel="stylesheet" type="text/css" media="screen" href="resources/css/style.css"/>
</head>
<body>
<jsp:include page="../parts/header.jsp" />
<div class="container">
    <p class="message">${message}</p>
    <form class="form-signin" action="/TravelAgency/password-recovery" method="post" >
        <fieldset>
            <h2 class="form-signin-heading">Password recovery</h2>
            <input id="email" name="email" size="20" type="text" class="input-block-level" placeholder="Type email">
            <button class="btn btn-large btn-primary" type="submit">Submit</button>
        </fieldset>
    </form>
</div>
</body>
</html>