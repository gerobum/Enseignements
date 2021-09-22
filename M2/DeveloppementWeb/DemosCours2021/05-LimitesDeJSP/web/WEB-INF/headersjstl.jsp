<%-- 
    Document   : headers
    Created on : 11 sept. 2021, 01:11:26
    Author     : Yvan Maillot <yvan.maillot@uha.fr>
--%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Infos</title>
    </head>
    <body>
        <h1>Quelques informations (JSTL et EL)</h1>
        <ul>
                <li>Adresse : ${pageContext.request.serverName}:${pageContext.request.localPort}<%=request.getRequestURI()%></li>
            <li>Protocole : ${pageContext.request.protocol}</li>
            <li>Méthode HTTP : <%=request.getMethod()%></li>
        </ul>
        <h1>Les en-têtes HTTP (JSP et EL)</h1>
        <ul>
            <c:forEach items="${ header }" var="h">
                <li>${ h.key } : ${ h.value }</li>
                </c:forEach>
        </ul>
    </body>
</html>