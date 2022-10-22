<%-- 
    Document   : affichagePersonne
    Created on : 10 sept. 2021, 23:06:38
    Author     : Yvan Maillot <yvan.maillot@uha.fr>
--%>

<%@page import="edu.uha.miage.person.service.Personne"%>
<% Personne personne = (Personne) request.getAttribute("personne");%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Un salut personnalisé</title>
    </head>
    <body>
        <h1>Bonjour
   <%-- p
            <%=personne.monsieur ? "M." : "Mme"%>
            ersonne.prenom est accessible parce que public (final) --%><%--
            <%=personne.prenom%>
              tout comme personne.nom). Mais s'il ne l'était pas, on aurait du écrire ceci : --%><%--
            <%=personne.getNom()%>,
            <%=personne.age%> ans
            
             De plus, si toString() est redéfinie, on peut l'utiliser : 

            --%>
            <%=personne.toString()%> 
        </h1>
    </body>
</html>
