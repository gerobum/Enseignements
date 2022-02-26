<%-- 
    Document   : saisie
    Created on : 10 sept. 2021, 23:23:36
    Author     : Yvan Maillot <yvan.maillot@uha.fr>
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Informations d’une personne</title>
    </head>
    <body>
        <h1>Informations d’une personne</h1>
        <form method="post">
            <p>
                <label for="nom">Nom : </label>
                <!-- Récupération du nom dans le modèle -->
                <input type="text" name="nom" value="${modele.nom}"/>
            </p>
            <p>
                <label for="prenom">Prénom : </label>
                <!-- Récupération du prénom dans le modèle -->
                <input type="text" name="prenom" value="${modele.prenom}"/>
            </p>
            <p>
                <label for="age">Âge : </label>
                <!-- Récupération de l’âge dans le modèle -->
                <input type="text" name="age" value="${modele.age}"/>
                <!-- Et affichage d’une alerte en cas d’erreur -->          
                <label for="age">${modele.ageOk ? "" : "<strong>Erreur</strong>"}</label>
            </p>
            <p>
                <label for="genre">Genre : </label>
                <select name="genre">
                    <!-- Récupération du genre dans le modèle -->
                    <option value="Homme" ${modele.genre == "Homme" ? "selected" : ""}>Homme</option>
                    <option value="Femme" ${modele.genre == "Femme" ? "selected" : ""}>Femme</option>
                </select>
            </p>
            <button type="submit">Valider</button>
        </form>
        <!-- Affichage du message fabriqué par le modèle -->
        <h2>${modele.message}</h2>
    </body>
</html>