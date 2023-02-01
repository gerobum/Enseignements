<%-- 
    Document   : enter
    Created on : 12 janv. 2023, 23:55:53
    Author     : yvan
--%>
<%@page import="edu.uha.miage.person.service.Person" %>
<%
    Person person = (Person) request.getAttribute("person");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Info</title>
    </head>
    <body>
        <h1>Bienvenue <%= person.toString() %></h1>
        
    </body>
</html>
