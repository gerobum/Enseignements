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
        <h4>Anniversaires à souhaiter</h4>
        <ul>
            <c:forEach items="${ map }" var="h">
                <li>${ h.key } : ${ h.value }</li>
            </c:forEach>
        </ul>


        <!--<form method="post"> 
            <p>
                <select>
                    <c:forEach items="${ map }" var="h">
                        <option value = "${ h.key }">${ h.key }</option>
                    </c:forEach>
                </select> 
            </p> 

            <input class="btn btn-danger" type="submit" value="Choisir" />
        </form>
        <p>
            xx/xx/xxxx
        </p>-->
    </body>
</html>
