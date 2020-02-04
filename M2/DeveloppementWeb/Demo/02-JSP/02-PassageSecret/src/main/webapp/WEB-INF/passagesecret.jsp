<%-- 
    Document   : portesecrete
    Created on : 6 janv. 2019, 21:09:25
    Author     : yvan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Passage secret</title>
    </head>
    <body>
        <c:choose>  
            <c:when test="${form.connecte}">
                <h3>Bienvenue ${form.pseudo}</h3>
            </c:when>
            <c:otherwise>
                <h3>
                <c:choose>
                    <c:when test="${empty form}">Donne ton pseudo et dis la phrase magique</c:when>
                    <c:otherwise>${form.message}</c:otherwise>
                </c:choose>
                </h3>
                
                <form method="post">
                    <p>
                        <label for="pseudo">Pseudo : </label>
                        <input type="text" name="pseudo" value="${ form.pseudo }" />
                    </p>
                    <p>
                        <label for="magique">Phrase magique : </label>
                        <input type="password" name="magique"  />
                    </p>
                    <button type="submit">Valide ta phrase magique</button>
                </form>
            </c:otherwise>
        </c:choose>
    </body>
</html>
