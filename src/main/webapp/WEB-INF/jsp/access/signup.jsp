<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<html>
<head>
    <title>Registration - Travel Agency</title>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
    <!--Connect styles from bootstrap-->
    <link rel="stylesheet" type="text/css" href='<c:url value="/resources/css/bootstrap.css"/>'>
    <link rel="stylesheet" type="text/css" media="screen" href='<c:url value="/resources/css/style.css"/>'/>
    <!--Connect scripts from bootstrap-->
    <script src='<c:url value="/resources/js/bootstrap.js"/>' type="text/javascript"></script>

    <style type="text/css">
        body {
            padding-top: 60px;
            /* padding-bottom: 40px;*/
        }
    </style>
</head>
<body>
<jsp:include page="../parts/header.jsp" />
<div class="container">
    <div class="row">
        <div class="span3">
            <jsp:include page="../parts/sidebar.jsp" />
        </div>
        <div class="span9">
            <div class="page-header">
                <h1>Реєстрація</h1>
            </div>

            <form action="/TravelAgency/signup" method="post" class="form-horizontal" >
                <fieldset>
                    <label for="firstName">First Name</label>
                    <input id="firstName" name="firstName" size="47" maxlength="50" type="text" required="required"
                           pattern="[ A-Za-z]{2,50}" title="First name have to be between 2 and 50 symbols."/>
                    <label for="lastName">Last Name</label>
                    <input id="lastName" name="lastName" size="47" maxlength="50" type="text" required="required"
                           pattern="[ A-Za-z]{2,50}" title="Last name have to be between 2 and 50 symbols."/>
                    <label for="username">Username</label>
                    <input id="username" name="username" size="47" maxlength="50" type="text" required="required"
                           pattern="[ A-Za-z0-9_-]{2,50}" title="Last name have to be between 2 and 50 symbols."/>
                    <label for="email">E-mail</label>
                    <input id="email" name="email" size="47" maxlength="50" type="text" required="required"
                           pattern="[A-Za-z0-9._%-]+@[A-Za-z0-9.-]+\.[A-Za-z]{2,6}" title="Please enter valid e-mail address"/>
                    <label for="password">Password</label>
                    <input id="password" name="password" size="47" maxlength="50" type="password" required="required"
                           pattern="[ A-Za-z0-9.,;:-_!@#$%^&+=]{4,50}" title="Password have to be between 4 and 50 symbols."/>
                    <label for="repassword">Retype password</label>
                    <input id="repassword" name="repassword" size="47" maxlength="50" type="password" required="required"
                           pattern="[ A-Za-z0-9.,;:-_!@#$%^&+=]{4,50}" title="Password must be between 4 and 50 symbols."/>
                    <br>
                    <input type="submit" class="btn btn-inverse" value="Sign up">
                </fieldset>
            </form>

        </div>
    </div>
</div>
<jsp:include page="../parts/footer.jsp" />
</body>
</html>