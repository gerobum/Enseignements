<%-- 
    Document   : form
    Created on : 2 janv. 2019, 23:34:49
    Author     : yvan
--%>
<%@page import="fr.miage.modele.ModeleEquation"%>
<!-- (2) Récupération du modèle, même la première fois, dans une scriplet.-->
<%
    ModeleEquation modele = (ModeleEquation) request.getAttribute("modele");
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Résolution d'une équation du second degré</title>
    </head>
    <body>

        <h4>Saisie d'une équation du second degré</h4>
        <form method="post" action="/equation/2d">
            <p>
                <label for="a">a :</label><!-- < %=modele.getA()% > au lieu de 1 (2) -->
                <input type="number" name="a" placeholder="un nombre différent de 0" step="any" value="<%=modele.getA()%>" />
            </p>
            <p>
                <label for="b">b :</label>
                <input type="number" name="b" placeholder="un nombre" step="any" value="<%=modele.getB()%>" />              
            </p>
            <p>
                <label for="c">c :</label>
                <input type="number" name="c" placeholder="un nombre" step="any" value="<%=modele.getC()%>" />
            </p>        
            <input type="submit" value="Calculer" />
        </form>

        <h4>Solution</h4>
        <p> 
            <%
                for (String s : modele.getMessages()) {
                    out.print("<p>" + s + "</p>");
                }
            %>
        </p>



    </body>
</html>
