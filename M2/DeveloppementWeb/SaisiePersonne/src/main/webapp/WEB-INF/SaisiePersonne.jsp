<%-- 
    Document   : SaisiePersonne
    Created on : 13 janv. 2019, 00:52:55
    Author     : yvan
--%>
<%@page import="fr.miage.firstapplijee.forms.PersonForm"%>
<%@page import="fr.miage.firstapplijee.metier.Personne"%>
<%
    PersonForm pf = (PersonForm) request.getAttribute("pf");
%>    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Information d'une personne</title>
    </head>
    <body>
        <h1>Information d'une personne</h1>
        
        <form method="post">
            <p>
                <label for="nom">Nom : </label>
                <input type="text" name="nom" value="<%=pf.getPersonne().getNom()%>"/>
            </p>
            <p>
                <label for="prenom">Prénom : </label>
                <input type="text" name="prenom" value="<%=pf.getPersonne().getPrenom()%>"/>
            </p>
            <p>
                <label for="age">Âge : </label>
                <input type="text" name="age" value="<%=pf.getPersonne().getAge()%>"/> 
                <label for="age"><% if (!pf.isAgeOk()) out.print("<strong>Erreur</strong>"); %></label>
            </p>
            <p>
                <label for="genre">Genre : </label>
                <select name="genre">
                    <option value="Homme" <% if (pf.getPersonne().isMonsieur()) out.print("selected"); %>>Homme</option>
                    <option value="Femme" <% if (!pf.getPersonne().isMonsieur()) out.print("selected"); %>>Femme</option>
                </select>
            </p>
            <button type="submit">Valider</button>
        </form>
                
        <h2><%= pf.getMessage() %></h2>
    </body>
</html>
