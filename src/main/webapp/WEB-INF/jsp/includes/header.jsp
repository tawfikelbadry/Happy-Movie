<%-- 
    Document   : header
    Created on : Aug 2, 2017, 1:19:58 AM
    Author     : tawfik
--%>

<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Movies App</title>
        <link rel="stylesheet" href="<spring:url value="/url/css/bootstrap.min.css"/>" >
        <link rel="stylesheet" href="<spring:url value="/url/css/movies-style.css" />" >
        <script src="http://code.jquery.com/jquery-3.2.1.js"></script>
        
        <c:if test="${sessionScope.user == null}" >
            <% response.sendRedirect("login"); %>
        </c:if>


    </head>

    <body>
        <nav class="menu" tabindex="0">
            <div class="smartphone-menu-trigger"></div>
            <header class="avatar">
                <img src="http://www.freeiconspng.com/uploads/account-icon-7.png" />
                <h2>${user.username}</h2>
            </header>
            <ul>
                <li tabindex="0" class="icon-dashboard"><a href="/Happy-Movie/popular"><span>Popular</span></a></li>
                <li tabindex="0" class="icon-customers"><a href="/Happy-Movie/top_rated" ><span>Top Rated</span></a></li>
                <li tabindex="0" class="icon-customers"><a href="/Happy-Movie/favourites" ><span> Favourites </span></a></li>
                <li tabindex="0" class="icon-customers"><a href="/Happy-Movie/logout" ><span>Log out</span></a></li>
            </ul>
        </nav>