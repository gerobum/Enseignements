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
        <h4>Donne la date dont tu veux le jour</h4>

        <form method="post">
            <p>
                <label for="day">Jour :</label>
                <input type="text" name="day" placeholder="un nombre entre 1 et 31" step="any" value="${dateModel.day}" />
                <c:if test = "${ ! dateModel.dayOk }"><span>Erreur</span></c:if>
            </p>
            <p>
                <label for="month">Mois :</label>
                <input type="text" name="month" placeholder="un nombre entre 1 et 12" value="${dateModel.month}" /> 
                <c:if test = "${ ! dateModel.monthOk}"><span>Erreur</span></c:if>             
            </p>
            <p>
                <label for="year">Année :</label>
                <input type="text" name="year" placeholder="un nombre entier" value="${dateModel.year}" />
                <c:if test = "${ ! dateModel.yearOk }"><span>Erreur</span></c:if>
            </p>        
            <input class="btn btn-danger" type="submit" value="Calculer" />
        </form>

    <c:forEach items="${ dateModel.messages }" var="h">
        <p>${ h }</p>
    </c:forEach>

 
</body>
</html>
