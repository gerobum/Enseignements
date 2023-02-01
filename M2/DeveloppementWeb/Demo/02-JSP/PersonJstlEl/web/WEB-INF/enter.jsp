<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Saisie</title>
    </head>
    <body>
        <h1>Entrez vos données</h1>
        <form method="post">
            <p>
                <label for="prenom">Prénom : </label>
                <input ${mpe.firstnameOk?"":"style='border: solid red'}"} 
                    id="prenom" 
                    type="text" 
                    name="prenom" 
                    value="${mpe.firstname}" 
                    />
            </p>
            <p>
                <label for="nom">Nom : </label>
                <input ${mpe.lastnameOk?"":"style='border: solid red'}"} 
                    id="nom" 
                    type="text" 
                    name="nom" 
                    value="${mpe.lastname}" 
                    />
            <c:if test="${!mpe.lastnameOk}"><span>En majuscule</span></c:if>
            </p>
            <p>
                <label for="age">Âge : </label>
                <input ${mpe.ageOk?"":"style='border: solid red'}"}
                    id="age" 
                    type="text" 
                    name="age" 
                    value="${age}"
                    />
            </p>
            <c:if test="${mpe.ageOk}">
            </c:if>
            <p>
                <label for="genre">Genre : </label>
                <select id="genre" name="genre">
                    <option value="Homme" ${"Homme".equals(mpe.gender)?"selected":""} >Homme</option>
                    <option value="Femme" ${"Femme".equals(mpe.gender)?"selected":""} >Femme</option>
                </select>
            </p>
            <p>
                <button type="submit">Valider</button>
                <button type="reset">Annuler</button>
            </p>
        </form>
    <c:if test="${!mpe.ok}">
  
        <h2>Erreur${mpe.messages.size()>1?"s":""}</h2>
    </c:if>
    <ul>

        <c:forEach items="${mpe.messages}" var="message">
            <li>${message}</li>
        </c:forEach>
    </ul>

</body>
</html>
