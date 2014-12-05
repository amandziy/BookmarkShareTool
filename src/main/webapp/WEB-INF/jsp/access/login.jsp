<!DOCTYPE html>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <title>Travel Agency. Login</title>
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
            <form class="form-signin" action="j_spring_security_check" method="post" >
                <fieldset>
                    <h2 class="form-signin-heading">Login</h2>
                    <input id="j_username" name="j_username" size="20" type="text" class="input-block-level" placeholder="Username">
                    <input id="j_password" name="j_password" size="20" type="password" class="input-block-level" placeholder="Password">
                    <button class="btn btn-large btn-primary" type="submit">Login</button>
                    <p><a href="/TravelAgency/password-recovery">Forget password?</a></p>
                </fieldset>
            </form>
        </div>
    </body>
</html>