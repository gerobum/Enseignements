
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Phrase Magique</title>
    </head>
    <body>
    <c:if test="${!pm.connected}">
        <h1>Donne ton nom et dis la phrase magique pour entrer dans un monde merveilleux</h1>
        <form method="post">
            <p>
                <label for="name">Nom : </label>
                <input type="text" id="name" name="name" value="${pm.name}"/>
                <c:if test="${!pm.nameOk}">
                    <span><b>Au moins une lettre</b></span>
                </c:if>
            </p>
            <p>
                <label for="password">Phrase magique : </label>
                <input type="password" id="password" name="password"/>
                <c:if test="${!pm.passwordOk}">
                    <span><b>Mot de passe incorrect</b></span>
                </c:if>
            </p>
            <button type="submit">Valide ta phrase magique</button>
        </form>   
    </c:if>
    <c:forEach items="${pm.msgs}" var="msg" begin="0" end="0">
        <h1>${msg}</h1>
    </c:forEach>
    
    <c:if test="${pm.connected}">
        <p><img src="images/java.png" width="15%" height="15%"/></p>
    </c:if>
    <p>
        <c:forEach items="${pm.msgs}" var="msg" begin="1">
            ${msg}<br>
        </c:forEach>
    </p> 
</body>
</html>
