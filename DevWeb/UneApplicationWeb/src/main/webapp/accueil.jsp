<%-- 
    Document   : bienvenue
    Created on : 7 nov. 2019, 00:02:52
    Author     : Yvan Maillot <yvan.maillot@uha.fr>
--%>
<%
    String nom = (String) request.getParameter("nom");
    nom = nom == null ? "depuis ma page JSP" : nom;
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html;
              charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bienvenue <%= nom%>  depuis ma page JSP</h1>
    </body>
</html>
