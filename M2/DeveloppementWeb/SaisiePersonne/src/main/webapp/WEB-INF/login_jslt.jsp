<%-- 
    Document   : saisie_jslt
    Created on : 5 janv. 2019, 22:55:03
    Author     : yvan
--%>
<%@page import="fr.miage.firstapplijee.forms.ConnexionForm"%>
<%--<c:if test="${ !empty form.pseudo }">
    <c:set var="sform" value="${ form }" scope="session"></c:set>
</c:if>--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Formulaire de connexion</title>
    </head>
    <body>
        <h2>Bonjour ${form.pseudo}
            <c:choose>
                <c:when test="${ form.age > 0 and form.age <= 1}">
                    ${form.age} an
                </c:when>
                <c:when test="${ form.age > 1}">
                    ${form.age} ans
                </c:when>
            </c:choose> 
            <p>${ form.message }</p>
        </h2>
        <c:choose>
            <c:when test="${ not form.connecte }">
                <form method="post">
                    <p>
                        <label for="pseudo">Pseudo : </label>
                        <input type="text" id="pseudo" name="pseudo" value="${form.pseudo}"/>
                    </p>
                    <p>
                        <label for="age">Age : </label>
                        <input type="number" id="age" name="age" value="${form.age}"/>
                        <label for="age">ans</label>
                    </p>
                    <p>
                        <label for="code">Code secret : </label>
                        <input type="password" id="code" name="code" value="${form.code}"/>
                    </p>

                    <input type="submit"/>
                </form>   
            </c:when> 
        </c:choose>
    </body>
</html>
