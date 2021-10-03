<%-- 
    Document   : headers
    Created on : 11 sept. 2021, 01:11:26
    Author     : Yvan Maillot <yvan.maillot@uha.fr>
--%>
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
      <li>Adresse : ${pageContext.request.serverName}:${pageContext.request.localPort}${pageContext.request.requestURI}</li>
      <li>Protocole : ${pageContext.request.protocol}</li>
      <li>Méthode HTTP : ${pageContext.request.method}</li>
    </ul>
    <h1>Les en-têtes HTTP (JSP)</h1>
    <ul>
      <%! // Définition d'une méthode (possible dans JSP)
        private String Stringify(Enumeration<String> headers) {
            StringBuilder sb = new StringBuilder();
            if (headers.hasMoreElements()) {
                sb.append(headers.nextElement());
                while (headers.hasMoreElements()) {
                    sb.append(", ").append(headers.nextElement());
                }
            }
            return sb.toString();
        }%>
      <%
        Enumeration<String> hn = request.getHeaderNames();
        while (hn.hasMoreElements()) {
            String entete = hn.nextElement();
            out.print("<li>" + entete + " : " + Stringify(request.getHeaders(entete)) + "</li>");
        }
      %>
    </ul>
  </body>
</html>
