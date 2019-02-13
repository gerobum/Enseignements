<%-- 
    Document   : salut
    Created on : 4 janv. 2019, 17:02:36
    Author     : yvan
--%>
<%@page import="fr.miage.firstapplijee.metier.Personne"%>
<% Personne personne = (Personne) request.getAttribute("personne"); %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Un salut personnalisé</title>
    </head>
    <body>
        <h1>Bonjour 
            <%=personne.isMonsieur()?"M.":"Mme"%> 
            <%= personne.getPrenom() %> 
            <%= personne.getNom() %>, 
            <%= personne.getAge() %> ans</h1>
    </body>
</html>
