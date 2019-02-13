<%@page import="java.util.Enumeration"%>
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

        <h1>Les en-têtes HTTP (JSP)</h1>
        <ul>
            <%! // Définition d'une méthode (c'est possible dans JSP)
                private String Stringify(Enumeration<String> headers) {
                    StringBuilder sb = new StringBuilder();
                    if (headers.hasMoreElements()) {
                        sb.append(headers.nextElement());
                        while (headers.hasMoreElements()) {
                            sb.append(", ").append(headers.nextElement());
                        }
                    }
                    return sb.toString();
                }
            %>
            <%
                Enumeration<String> hn = request.getHeaderNames();

                while (hn.hasMoreElements () 
                    ) {
                    String entete = hn.nextElement();
                    out.print("<li>" + entete + " : " + Stringify(request.getHeaders(entete)) + "</li>");
                }
            %>
        </ul>
        
        
        <h1>Les objets accessibles dans une page JSP</h1>
        <ul>
            <li><%= application.getClass()  %></li>
        </ul>
        
    </body>
</html>
