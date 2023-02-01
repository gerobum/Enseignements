<%-- 
    Document   : enter
    Created on : 12 janv. 2023, 23:55:53
    Author     : yvan
--%>
<%@page import="edu.uha.miage.person.models.ModelPersonEntry" %>
<%
    ModelPersonEntry mpe = (ModelPersonEntry) request.getAttribute("mpe");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Saisie</title>
    </head>
    <body>
        <h1>Entrez vos données</h1>
        <form method="post">
            <p>
                <label for="prenom">Prénom : </label>
                <input id="prenom" type="text" name="prenom" value="<%= mpe.getFirstname()%>" />
            </p>
            <p>
                <label for="nom">Nom : </label>
                <input id="nom" type="text" name="nom" value="<%= mpe.getLastname()%>" />
            </p>
            <p>
                <label for="age">Âge : </label>
                <input  <% if (!mpe.isAgeOk()) out.print("style='border: solid red'"); %> id="age" type="text" name="age" value="<%= mpe.getAge() %>"/>
                <span><%= mpe.isAgeOk()?"":"<strong>Entre 0 et 150</strong>" %></span>
            </p>
            <p>
                <label for="genre">Genre : </label>
                <select id="genre" name="genre">
                    <option value="Homme" <% if ("Homme".equals(mpe.getGender())) out.print("selected"); %> >Homme</option>
                    <option value="Femme" <% if ("Femme".equals(mpe.getGender())) out.print("selected"); %> >Femme</option>
                </select>
            </p>
            <p>
                <button type="submit">Valider</button>
                <button type="reset">Annuler</button>
            </p>
        </form>
                <h2><%= mpe.getMessage() %></h2>
    </body>
</html>
