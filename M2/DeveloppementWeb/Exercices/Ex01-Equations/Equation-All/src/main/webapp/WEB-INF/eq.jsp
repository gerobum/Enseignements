<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Résolution d'une équation du second degré</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    </head>
    <body>

        <h4>Saisie d'une équation du second degré</h4>


        <form method="post" action="/equation/2d">
            <p>
                <label for="a">a :</label>
                <input type="number" name="a" placeholder="un nombre différent de 0" step="any" value="${modele.a}" />
                <c:if test="${!modele.aOk}"><label for="a">Erreur</label></c:if>
                </p>
                <p>
                    <label for="b">b :</label>
                    <input type="number" name="b" placeholder="un nombre" step="any" value="${modele.b}" />  
                <c:if test="${!modele.bOk}"><label for="b">Erreur</label></c:if>            
                </p>
                <p>
                    <label for="c">c :</label>
                    <input type="number" name="c" placeholder="un nombre" step="any" value="${modele.c}" />
                <c:if test="${!modele.cOk}"><label for="c">Erreur</label></c:if> 
                </p>

                <input type="submit" value="Calculer" />
            </form>


            <h4>Solution</h4>
            <c:forEach var="message" items="${modele.messages}">
                <p>${message}</p>
            </c:forEach>

    </body>
</html>
