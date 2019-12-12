<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Quel jour était-on ?</title>
    </head>
    <body>
        <h4>Anniversaire à souhaiter</h4>

        <form method="post"> 
            <p>
                <label for="who">Qui : </label>
                <input type="text" name="who" placeholder="un nom" value="${annivModel.nom}" />
                <c:if test = "${ ! annivModel.nomOk }"><span>Erreur</span></c:if>
            </p>     
            <p>
                <label for="day">Jour :</label>
                <input type="text" name="day" placeholder="un nombre entre 1 et 31" step="any" value="${annivModel.day}" />
                <c:if test = "${ ! annivModel.dayOk }"><span>Erreur</span></c:if>
            </p>
            <p>
                <label for="month">Mois :</label>
                <input type="text" name="month" placeholder="un nombre entre 1 et 12" value="${annivModel.month}" /> 
                <c:if test = "${ ! annivModel.monthOk}"><span>Erreur</span></c:if>             
            </p>
            <p>
                <label for="year">Année :</label>
                <input type="text" name="year" placeholder="un nombre entier" value="${annivModel.year}" />
                <c:if test = "${ ! annivModel.yearOk }"><span>Erreur</span></c:if>
            </p>     
            <input class="btn btn-danger" type="submit" value="Calculer" />
        </form>

    <c:forEach items="${ annivModel.messages }" var="h">
        <p>${ h }</p>
    </c:forEach>

 
</body>
</html>
