<%-- 
    Document   : SaisiePersonne
    Created on : 13 janv. 2019, 00:52:55
    Author     : yvan
--%>

<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informations d'une personne</title>
    </head>
    <body>
        <h1>Informations d'une personne EL</h1>

        <form method="post">
            <p>
                <label for="nom">Nom : </label>
                <!-- Récupération du nom dans le modèle -->
                <input type="text" name="nom" value="${modèle.nom}"/>
            </p>
            <p>
                <label for="prenom">Prénom : </label>
                <!-- Récupération du prénom dans le modèle -->
                <input type="text" name="prenom" value="${modèle.prénom}"/>
            </p>
            <p>
                <label for="age">Âge : </label>
                <!-- Récupération de l'âge dans le modèle -->
                <input type="text" name="age" value="${modèle.âge}"/> 
                <!-- Et affichage d'une alerte en cas d'erreur -->
                <c:if test="${!modèle.âgeOk}">                    
                    <label for="age">Erreur</label>
                </c:if>
            </p>
            <p>
                <label for="genre">Genre : </label>
                <select name="genre">
                    <!-- Récupération du genre dans le modèle -->
                    <option value="Homme" <c:if test='${"Homme".equals(modèle.genre)}'>selected</c:if> >Homme</option>
                    <option value="Femme" <c:if test="${'Femme'.equals(modèle.genre)}">selected</c:if> >Femme</option>                 
                    </select>
                </p>
                <button type="submit">Valider</button>
            </form>

            <!-- Affichage du message fabriqué de le modèle -->
            <h2>${modèle.message}</h2>
    </body>
</html>
