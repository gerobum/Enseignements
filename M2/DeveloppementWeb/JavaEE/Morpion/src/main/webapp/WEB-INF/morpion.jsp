<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Morpion</title>
    </head>
    <body>

        <h2>Morpion</h2>
        <c:if test="${!sessionScope.modele.firstPlayerIsChosen}">
            <form method="post" action="/jeux/morpion">
                <label for="whoFirst">Qui joue en premier ?</label>
                <select  id="whoFirst" name="firstPlayer">
                    <option value="ordinateur">L'ordinateur</option>
                    <option value="moi">Moi</option>
                </select>
                <input type="submit" name="first" value="Choisir" />
            </form>
        </c:if>
        <c:if test="${modele.firstPlayerIsChosen}">
            <form method="post" action="/jeux/morpion">
                <h3>Tu joues avec les ${sessionScope.modele.playerToken}</h3>
                <table>
                    <tbody>
                        <c:forEach begin="0" end="2" var="l">  
                            <tr>
                                <c:forEach begin="0" end="2" var="c">                            
                                    <td>
                                        <input style="font-family:'Courier New'" type="submit" name="${'b'.concat(l).concat(c)}" value="${sessionScope.modele.get(l,c)}">      
                                    </td>
                                </c:forEach>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>      
                <c:if test="${sessionScope.modele.finished}"><input type="submit" name="rejouer" value="Rejouer"></c:if>                  
                </form>
                <p>${sessionScope.modele.message}</p>
        </c:if>
    </body>
</html>
