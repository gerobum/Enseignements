<%-- 
    Document   : login
    Created on : 6 janv. 2019, 11:55:51
    Author     : yvan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Connexion</title>
    </head>
    <body>
        <form method="post" action="connexion">
            <p>
                <label for="nom">Nom : </label>
                <input type="text" id="nom" name="nom" value="${form.nom}"/>                       
            </p>
            <button type="submit">Se connecter</button>
        </form>
    </body>
</html>
