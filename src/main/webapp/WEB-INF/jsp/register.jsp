<%-- 
    Document   : register
    Created on : Jul 25, 2017, 1:55:38 PM
    Author     : tawfik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<!DOCTYPE html>
<html >
    <head>
        <meta charset="UTF-8">
        <title>Login form</title>
        <script src="https://s.codepen.io/assets/libs/modernizr.js" type="text/javascript"></script>

        <link href='https://fonts.googleapis.com/css?family=Raleway:300,200' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/meyer-reset/2.0/reset.min.css">
        <link rel="stylesheet" href="<spring:url value="/url/css/login-style.css"/>">

    </head>

    <body>

        <form:form commandName="user" action="register" method="POST">

            <div class="form">
                <div class="forceColor"></div>
                <div class="topbar">
                    <div class="spanColor"></div>
                    <form:input path="username" cssClass="input" placeholder="User Name" />
                    <form:errors path="username" cssClass="errorBlock" />

                    <form:input path="full_name" cssClass="input" placeholder="Full Name" />
                    <form:errors path="full_name" cssClass="errorBlock" />

                    <form:input path="email" cssClass="input" placeholder="Email" />
                    <form:errors path="email" cssClass="errorBlock" />

                    <form:password path="password" cssClass="input" placeholder="Password" />
                    <form:errors path="password" cssClass="errorBlock" />

                </div>

                <button class="submit" id="submit" type="submit" >Sign Up</button>

                <a href="login" class="registernow" >Login Now</a>

            </div>

        </form:form>>

        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

        <script src="js/login-js.js"></script>

    </body>
</html>
