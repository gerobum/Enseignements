<%-- 
    Document   : affichagePersonne
    Created on : 10 sept. 2021, 23:06:38
    Author     : Yvan Maillot <yvan.maillot@uha.fr>
--%>
<%--  CECI N'EST PLUS NÉCESSAIRE AVEC EL :

    <%@page import="edu.uha.miage.person.service.Personne"%>
    <% Personne personne = (Personne) request.getAttribute("personne");%>
--%>

<!--Inutile d'importer Personne avec EL -->

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Un salut personnalisé (EL)</title>
    </head>
    <body>
        <h1>Bonjour 
        <%-- 
            ${personne.monsieur ? "M." : "Mme "}
            ${personne.prenom}            
            ${personne.nom}
            (${personne.age} ${personne.age > 1 ? "ans" : "an"})

        De plus, si toString() est redéfinie, on peut l'utiliser : 
        --%>

        ${personne}
        
   
        </h1>
    </body>
</html>
