<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Infos</title>
    </head>
    <body>
        <h1>Quelques informations (JSP et EL)</h1>
        <ul>
            <li>Adresse : ${ pageContext.request.serverName }:${ pageContext.request.localPort }<%=request.getRequestURI()%></li>
            <li>Protocole : ${ pageContext.request.protocol }</li>
            <li>Méthode HTTP : <%=request.getMethod()%></li>
        </ul>
        <h1>Les en-têtes HTTP (JSP et EL)</h1>
        <ul>
            <c:forEach items="${ header }" var="h">
                <li>${ h.key }<%-- : ${ h.value }--%>
                    <ul>                        
                        <c:forEach items="${ h.value }" var="v">
                            <li>${v}</li>
                        </c:forEach> 
                    </ul> 
                </li>
            </c:forEach>
        </ul>
    </body>
</html>
