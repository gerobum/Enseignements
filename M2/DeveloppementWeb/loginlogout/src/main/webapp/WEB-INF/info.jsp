<%-- 
    Document   : info
    Created on : 4 janv. 2019, 13:38:02
    Author     : yvan
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Infos</title>
    </head>
    <body>
        <h1>Quelques informations (JSP)</h1>
        <ul>
            <li>Adresse : <%=request.getServerName()%>:<%=request.getLocalPort()%><%=request.getRequestURI()%></li>
            <li>Protocole : <%=request.getProtocol()%></li>
            <li>Méthode HTTP : <%=request.getMethod()%></li>
        </ul>
    </body>
</html>
