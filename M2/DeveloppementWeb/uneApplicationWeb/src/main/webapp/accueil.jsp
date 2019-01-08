<%-- 
    Document   : accueil
    Created on : 2 janv. 2019, 19:39:19
    Author     : yvan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    String nom = request.getParameter("nom");
    nom = nom==null?"":nom;
    
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil (JSP)</title>
    </head>
    <body>
        <h3>Bienvenue <%= nom %></h3>
    </body>
</html>
