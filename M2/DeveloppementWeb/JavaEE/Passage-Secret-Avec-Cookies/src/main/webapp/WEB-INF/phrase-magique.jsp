
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Phrase Magique</title>
    </head>
    <body>
        <!-- ##### Pas de sessionScope : possible -->
        <!-- ##### mais c'est préférable -->
    <c:if test="${!sessionScope.pm.connected}">
        <h1>Donne ton nom et dis la phrase magique pour entrer dans un monde merveilleux</h1>
        <form method="post">
            <p>
                <label for="name">Nom : </label>
                <input type="text" id="name" name="name" value="${sessionScope.pm.name}"/>
            </p>
            <p>
                <label for="password">Phrase magique : </label>
                <input type="password" id="password" name="password"/>
            </p>
            <button type="submit">Valide ta phrase magique</button>
        </form>   
    </c:if>
    <c:forEach items="${sessionScope.pm.msgs}" var="msg" begin="0" end="0">
        <h1>${msg}</h1>
    </c:forEach>
    <p>
    <c:forEach items="${sessionScope.pm.msgs}" var="msg" begin="1">
        ${msg}<br>
    </c:forEach>
</p> 
<c:if test="${!sessionScope.pm.connected}">
    <p><img src="images/java.jpg"/></p>
</c:if>
</body>
</html>
