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
            <c:forEach items="${model.names}" var="name" begin="0" end="0">
                ${name}
            </c:forEach>
            <c:forEach items="${model.names}" var="name" begin="1">
                , ${name}
            </c:forEach>
                       
        </h1>

        <form method="post">
            <p>
                <label for="name">Choisir un article : </label>
                <input type="text" name="name" value="${model.article.name}" style="${model.articleOk?'':'background-color:#FFDDDD;'}"/>
                <c:if test="${!model.articleOk}"><span>"${model.article.name}" indisponible : choisir Coffee ou Chocolat ou Tea</span></c:if>
                </p>
                <button type="submit">Valider</button>
            </form>


            <h2>${model.chosenArticle}</h2>

        <p>
            <a href="/magasin">Retour à l'entrée</a>       
        </p>

    </body>
</html>
