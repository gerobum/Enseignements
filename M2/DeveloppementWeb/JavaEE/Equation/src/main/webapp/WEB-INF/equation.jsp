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
        <!-- (3) Pour ajouter du style-->
        <!-- <link rel="stylesheet" type="text/css" href="css/style.css"/> -->
        <!-- (4) Mais il est préférable d'utiliser un framework comme bootstrap -->
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    </head>
    <body>
        <div class="container">
            <div class="col-md-6 col-xs-12 col-md-offset-3">
                <div class="panel panel-primary">
                    <!-- (2) puis (3) --> 
                    <div class="panel-heading"><h4>Saisie d'une équation du second degré</h4></div>
                    <div class="panel-body">

                        <form method="post" action="/equation/2d">
                            <p>
                                <label for="a">a :</label><!-- < %=modele.getA()% > au lieu de 1 (2) -->
                                <input type="number" name="a" placeholder="un nombre différent de 0" step="any" value="<%=modele.getA()%>" />
                                <!-- (4) Pour tenir compte de cet impératif -->
                 
                                <!-- (4) Mais il faut faire attention, on ne fait plus comme ça -->
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


        <!-- (1)
        Dans un premier temps, le formulaire ne sert qu'à saisir les données.
        
        Les valeurs des inputs seront envoyés dans la requête dans les 
        paramètres a, b et c, leur nom. On reviendra donc ici par POST avec
        les paramètres a, b, c renseignés.
        On utilise une scriplet pour les récupérer.
        -->
        <!-- (1) 
        < %
            String a = request.getParameter("a");
            a = a == null?a="1":a;
            String b = request.getParameter("b");
            b = b == null?b="0":b;
            String c = request.getParameter("c");
            c = c == null?c="0":c;
        % >
        -->
        <!-- (2)
        Le modèle qui contient toutes les données à afficher dans la vue va être
        utilisé. On s'aperçoit qu'il contient a, b, c et que les valeurs par défaut
        des inputs sont remises à 1 0 0, même après les avoir saisies.
        
        Il faudra donc avoir le modèle dès le départ, avec ses valeurs par défaut.
        -->

        <!-- (1)
        Dans un premier temps, le formulaire ne sert qu'à saisir les données.
        
        Les valeurs des inputs seront envoyés dans la requête dans les 
        paramètres a, b et c, leur nom. On reviendra donc ici par POST avec
        les paramètres a, b, c renseignés.
        On utilise une scriplet pour les récupérer.
        -->
        <!-- (1) 
        < %
            String a = request.getParameter("a");
            a = a == null?a="1":a;
            String b = request.getParameter("b");
            b = b == null?b="0":b;
            String c = request.getParameter("c");
            c = c == null?c="0":c;
        % >
        -->
        <!-- (2)
        Le modèle qui contient toutes les données à afficher dans la vue va être
        utilisé. On s'aperçoit qu'il contient a, b, c et que les valeurs par défaut
        des inputs sont remises à 1 0 0, même après les avoir saisies.
        
        Il faudra donc avoir le modèle dès le départ, avec ses valeurs par défaut.
        -->

        <div class="container">
            <div class="col-md-6 col-xs-12 col-md-offset-3">
                <div class="panel panel-success">
                    <!-- (2) puis (3) --> 
                    <div class="panel-heading"><h4>Solution</h4></div>
                    <div class="panel-body">
                        <p> <!-- (1) -->
                            <!--< % out.print(String.format("%sx²+%sx + %s", a, b, c));% >-->
                            <!-- Ces deux lignes sont équivalentes -->
                            <!-- < %=String.format("%sx² + %sx + %s = 0", a, b, c)% > -->
                            <!-- Ci-dessus en (1) remplacé par ci-dessous en (2) -->
                            <%=String.format("%sx² + %sx + %s = 0", modele.getA(), modele.getB(), modele.getC())%>
                        </p>
                        <p> <!-- (2) -->
                            <%
                                for(String s : modele.getMessages()) {
                                    out.print(s+"<br>");
                                }
                            %>
                        </p>
                    </div>
                </div>
            </div>
    </body>
</html>
