<%-- 
    Document   : SaisiePersonne
    Created on : 13 janv. 2019, 00:52:55
    Author     : yvan
--%>
<%@page import="edu.uha.miage.model.PersonFormModel"%>

<%
    // Récupération du modèle (vierge au départ ou rempli, à la longue)
    PersonFormModel model = (PersonFormModel) request.getAttribute("model");
%>    
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informations d'une personne</title>
    </head>
    <body>
        <h1>Informations d'une personne</h1>
        
        <form method="post">
            <p>
                <label for="nom">Nom : </label>
                <!-- Récupération du nom dans le modèle -->
                <input type="text" name="nom" value="<%=model.getSurname()%>"/>
            </p>
            <p>
                <label for="prenom">Prénom : </label>
                <!-- Récupération du prénom dans le modèle -->
                <input type="text" name="prenom" value="<%=model.getName()%>"/>
            </p>
            <p>
                <label for="age">Âge : </label>
                <!-- Récupération de l'âge dans le modèle -->
                <input type="text" name="age" value="<%=model.getAge()%>"/> 
                <!-- Et affichage d'une alerte en cas d'erreur -->
                <label for="age"><% if (!model.isAgeOk()) out.print("<strong>Erreur</strong>"); %></label>
            </p>
            <p>
                <label for="genre">Genre : </label>
                <select name="genre">
                <!-- Récupération du genre dans le modèle -->
                    <option value="Homme" <% if ("Homme".equals(model.getGender())) out.print("selected"); %>>Homme</option>
                    <option value="Femme" <% if (!"Homme".equals(model.getGender())) out.print("selected"); %>>Femme</option>
                </select>
            </p>
            <button type="submit">Valider</button>
        </form>
                
        <!-- Affichage du message fabriqué de le modèle -->
        <h2><%= model.getMessage() %></h2>
    </body>
</html>
