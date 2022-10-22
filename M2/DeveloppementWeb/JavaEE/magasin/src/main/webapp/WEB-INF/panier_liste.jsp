<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Panier</title>
    </head>
    <body>
        <h1>Choisir parmi 
            <c:forEach items="${sessionScope.modelList.names}" var="name" begin="0" end="0">
                ${name}
            </c:forEach>
            <c:forEach items="${sessionScope.modelList.names}" var="name" begin="1">
                , ${name}
            </c:forEach>

        </h1>

        <form method="post">
            <p>
                <label for="name">Choisir un article : </label>
                <select name="name">
                    <c:forEach items="${sessionScope.modelList.names}" var="name">
                        <option value="${name}">${name}</option>
                    </c:forEach>
                    
                </select>
                <%--
                <input type="text" name="name" value="${sessionScope.modelList.article.name}" style="${sessionScope.modelList.articleOk?'':'background-color:#FFDDDD;'}"/>
                --%>
                <c:if test="${!sessionScope.modelList.articleOk}"><span>"${sessionScope.modelList.article.name}" indisponible : choisir Coffee ou Chocolat ou Tea</span>
                </c:if>
            </p>
            <button type="submit">Valider</button>
        </form>



        <c:choose>
            <c:when test="${modelList.list.size() == 1}"><h2>
                    <c:forEach items="${modelList.list}" var="article" begin="0" end="0">
                        <li>${article}</li>
                    </c:forEach></h2>
                </c:when>
                <c:when test="${modelList.list.size() > 1}">
                <h2>Liste</h2>
                <ul>
                    <c:forEach items="${modelList.list}" var="article">
                        <li>${article}</li>
                        </c:forEach>
                </ul>
                <h3>Total : ${modelList.total}€</h3>
            </c:when>
        </c:choose>

        <p>
            <a href="/magasin">Retour à l'entrée</a>       
        </p>  
    </body>
</html>
