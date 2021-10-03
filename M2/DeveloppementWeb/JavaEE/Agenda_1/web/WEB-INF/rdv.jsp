
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Rendez-vous</title>
    </head>
    <body>
    <c:if test="${rendezVous != null}">
        <h1>Rendez-vous pris</h1> 
        <p>
            ${rendezVous}
        </p>
    </c:if> 

        <h1>Prise de rendez-vous</h1>
        <form method="post">
            <p>
                <label for="title">Titre : </label>
                <input type="text" id="title" name="title" value="${model.getTitle()}"/>
                <c:if test="${!model.isTitleOk()}"><label for="title">Le titre ne doit pas être vide</label></c:if>
            </p>
            <p>
                <label for="date">Date : </label>
                <input type="text" id="date" name="date" value="${model.date}"/>
                <c:if test="${!model.dateOk}"><label for="date">Date incorrecte</label></c:if>
            </p>
            <p>
                <label for="time">Heure : </label>
                <input type="text" id="time" name="time" value="${model.time}"/>
                <c:if test="${!model.timeOk}"><label for="time">Heure incorrecte</label></c:if>
            </p>
            <c:if test="${!model.notPassed}">
                <p>
                    Impossible de prendre un rendez-vous dans le passé.
                </p>
            </c:if>
            <button type="submit">Valider</button>

            <!--<a href="/Agenda" class="button">Annuler</a>-->

        </form>   

</body>
</html>
