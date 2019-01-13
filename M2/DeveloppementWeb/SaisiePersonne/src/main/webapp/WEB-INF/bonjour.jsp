<%-- 
    Document   : bonjour
    Created on : 4 janv. 2019, 02:36:53
    Author     : yvan
--%>
<%@page import="fr.miage.firstapplijee.metier.Personne"%>
<%
  Personne personne = (Personne) request.getAttribute("personne");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="jspf/menu.jspf" %>
        <h1>Bonjour <%=(personne.isMonsieur()?"M.":"Mme")%> <%=personne.getPrenom() %> <%=personne.getNom() %>, <%=personne.getAge() %> ans</h1>
        <ul>
            <%
                for(int i = 1; i < personne.getAge(); ++i) {
                    out.print(String.format("<li>%d ans </li>", i));
                }
            %>
        </ul>
    </body>
</html>
