<%-- 
    Document   : saisie_jslt
    Created on : 5 janv. 2019, 22:55:03
    Author     : yvan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulaire de saisie</title>
    </head>
    <body>
        <c:choose>
            <c:when test="${form.connecte}">
                <h1>Bonjour</h1>
                <form method="post">
                    <input type="submit" value="Se déconnecter">
                </form>
            </c:when>
            <c:otherwise>
                <h1>Vous êtes bien déconnecté</h1>
            </c:otherwise>
        </c:choose>
    </body>
</html>
