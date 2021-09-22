<%-- 
    Document   : salutel
    Created on : 4 janv. 2019, 19:37:13
    Author     : yvan
--%>

<%-- 
    Document   : salut
    Created on : 4 janv. 2019, 17:02:36
    Author     : yvan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Un salut personnalisé avec Expression Language</title>
    </head>
    <body>
        <%--<h1>Bonjour 
            ${personne.monsieur?"M.":"Mme"}
            ${personne.prenom} 
            ${personne.nom}, 
            ${personne.age} ans</h1>
        <h1>Bonjour 
        <c:if test="${personne.age > 14}">
            ${personne.monsieur?"M.":"Mme"}
        </c:if>
            ${personne.prenom} 
            ${personne.nom}, 
            ${personne.age} ans
        </h1>--%>
        <h1>Bonjour 
            <c:if test="${personne.age > 14}">
                ${personne.monsieur?"M.":"Mme"}
            </c:if>            
            ${personne.prenom} 
            ${personne.nom}, 
            ${personne.age} ans
        </h1>
        <c:choose>
            <c:when test="${personne.age < 10 && personne.monsieur }"><p>Petit garçon</p></c:when>
            <c:when test="${personne.age < 10 && ! personne.monsieur }"><p>Petite fille</p></c:when>
            <c:when test="${personne.age >= 18 }"><p>En âge de voter</p></c:when>
            <c:otherwise><p>-------------------</p></c:otherwise>
        </c:choose>

        <c:forEach begin="1" end="1">
            <p>Je ne copierai plus sur mon voisin.</p>
        </c:forEach>  

        <ul>
            <c:forEach begin="1" 
                       end="10" 
                       step="2" 
                       var="i" 
                       varStatus="status">
                <li>Element N°${i} - status.count = ${ status.count }</li>
                </c:forEach>   
        </ul>
        <ul>
            <%-- Déclaration d'un tableau des jours de la semaine --%>
            <c:set var="jours" value="${['lundi', 'mardi', 'mercredi', 'jeudi', 'vendredi', 'samedi', 'dimanche']}"/>
            <li>Les jours de la semaine</li>
            <ul>
                <c:forEach items="${jours}" var="j">
                    <li>${j}</li>
                    </c:forEach>   
            </ul> 
            <li>Les jours travaillés</li>
            <ul>
                <c:forEach items="${jours}" var="j" begin="0" end="4">
                    <li>${j}</li>
                    </c:forEach>    
            </ul> 
            <li>Le weekend</li>
            <ul>
                <c:forEach items="${jours}" var="j" begin="5" end="6">
                    <li>${j}</li>
                    </c:forEach>   
            </ul>    
        </ul>
    </body>
</html>
