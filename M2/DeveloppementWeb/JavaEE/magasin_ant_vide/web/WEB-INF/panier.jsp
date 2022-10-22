<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Panier</title>
    </head>
    <body>
        <h1>Choisir parmi Coffee, Choco, Tea</h1>

        <form method="post">
            <p>
                <label for="name">Choisir un article : </label>
                <input type="text" name="name" value=""/>
                </p>
                <button type="submit">Valider</button>
            </form>
        <p>
            <a href="/magasin">Retour à l'entrée</a>       
        </p>

    </body>
</html>
