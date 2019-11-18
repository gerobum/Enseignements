<%-- 
    Document   : form
    Created on : 2 janv. 2019, 23:34:49
    Author     : yvan
--%>
<%@page import="edu.uha.miage.modele.ModeleEquation"%>
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
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <div class="col-md-6 col-xs-12 col-md-offset-3">
                <div class="panel panel-primary">
                    <div class="panel-heading"><h4>Saisie d'une équation du second degré</h4></div>
                    <div class="panel-body">

                        <form method="post" action="/equation/2d">
                            <p>
                                <label for="a">a :</label>
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
                            <input class="btn btn-danger" type="submit" value="Calculer" />
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="container">
            <div class="col-md-6 col-xs-12 col-md-offset-3">
                <div class="panel panel-success">
                    <div class="panel-heading"><h4>Solution</h4></div>
                    <div class="panel-body">
                            <% 
                                for(String msg : modele.getMessages())
                                   out.print("<p>"+msg+"</p>");
                            %>
                    </div>
                </div>
            </div>
    </body>
</html>
