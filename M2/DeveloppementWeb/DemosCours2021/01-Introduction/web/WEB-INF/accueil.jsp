<%-- 
    Document   : accueil
    Created on : 10 sept. 2021, 14:37:20
    Author     : Yvan Maillot <yvan.maillot@uha.fr>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%
    // Scriplet
    String nom = request.getParameter("nom");
    nom = nom==null?"":nom;
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil</title>
    </head>
    <body>
        <h1>Bienvenue <%= nom %> </h1>
    </body>
</html>
