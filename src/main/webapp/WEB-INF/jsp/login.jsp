<%-- 
    Document   : register
    Created on : Jul 25, 2017, 1:55:38 PM
    Author     : tawfik
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>

<!DOCTYPE html>
<html >
    <head>
        <meta charset="UTF-8">
        <title>Login form</title>
        <link href='https://fonts.googleapis.com/css?family=Raleway:300,200' rel='stylesheet' type='text/css'>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">

        <!-- Latest compiled and minified CSS -->
        <link rel="stylesheet" href="<spring:url value="/url/css/login-style.css"/>">
        
        <c:if test="${sessionScope.user != null}" >
            <% response.sendRedirect("popular"); %>
        </c:if>
    </head>

    <body>
        <form:form commandName="user">


            <div class="form">

                <div class="forceColor"></div>

                <div class="topbar">

                    <div class="spanColor"></div>
                    <c:if test="${param.success eq 'ok' }">
                        <h2 class="alert alert-success" >Regestration success login now</h2>
                    </c:if>
                        
                    <c:if test="${param.success eq 'false' }">
                        <h2 class="alert alert-danger" >User name or password incorrect</h2>
                    </c:if>

                    <form:input path="username" cssClass="input" placeholder="User Name" />
                    <form:errors path="username" cssClass="errorBlock" />
                    <form:password path="password" cssClass="input" placeholder="Password" />
                    <form:errors path="password" cssClass="errorBlock" />

                </div>
                <button class="submit" id="submit" type="submit" >Login</button>

                <a href="register" class="registernow" >Register Now</a>

            </div>
        </form:form>

        <script src='http://cdnjs.cloudflare.com/ajax/libs/jquery/2.1.3/jquery.min.js'></script>

        <script src="<spring:url value="/js/login-js.js"/>"></script>

    </body>
</html>
