<%-- 
    Document   : accueil
    Created on : 4 janv. 2019, 03:13:42
    Author     : yvan
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Accueil (JSLT)</title>
    </head>
    <body>
  <p>(JSP) Bonjour <%=request.getAttribute("nom")%></p> 
  <p>(JSTL) Bonjour ${nom}</p>  
  <%--<p>(JSTL) Bonjour <c:out value="${nom}">à tous</c:out></p> --%> 
  <%--<p>(JSTL) Bonjour <c:out value="${nom}"/></p>  --%>  
  <%--<p>(JSTL) Bonjour "${nom}"/></p>  --%>                  
    </body>
</html>
