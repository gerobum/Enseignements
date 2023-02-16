<%-- 
    Document   : magic
    Created on : 27 sept. 2022, 19:07:46
    Author     : yvan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css"> 
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Passage Secret</title>
        
        <style>
            body {
                margin: 8%; /* ajoute une marge de 20px autour de tous les côtés */
            }
        </style>
    </head>
    <body>
        <c:if test="${!model.connected}">

            <h1>${model.title}</h1>
            <form method="post">
                <p>
                    <label for="nom">Nom : </label>
                    <input id="name" type="text" name="name" value="${model.name}" style="${model.nameOk?'':'background-color:#FFDDDD;'}"/>
                </p>
                <p>
                    <label for="magic">Phrase magique : </label>
                    <input type="password" id="magic" name="magic" value="${model.magic}" style="${model.magicOk?'':'background-color:#FFDDDD;'}"/>
                </p>
                <button type="submit">Valider</button>
            </form>
            <h2>${model.footer}</h2>
        </c:if>
        <c:if test="${model.connected}">
            <h1>Bienvenue <strong><span style="color:#0074bd;">${model.name}</span></strong> dans le monde merveilleux de Java</h1>
            <p>
                <img src="images/java.png" width="15%" />
            </p>
            <c:forEach items="${model.finalMsgs}" var="msg" begin="0" end="0">
                <h2>${msg}</h2>
            </c:forEach>
            <c:forEach items="${model.finalMsgs}" var="msg" begin="1">
                ${msg}<br>
            </c:forEach>
        </c:if>
    </body>
</html>
