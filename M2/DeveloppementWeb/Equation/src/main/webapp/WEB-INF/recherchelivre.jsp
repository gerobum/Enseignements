<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <title>Recherche d'un livre</title>
        <link rel="stylesheet" type="text/css" href="css/bootstrap.min.css">
    </head>
    <body>
        <!--<div class="container">-->
            <div class="col-md-6 col-xs-12 col-md-offset-3">
                <!--<div class="panel panel-primary">-->
                    <div class="panel-heading"><h4>Recherche d'un livre</h4></div>
                    <!--<div class="panel-body">-->

                        <form method="post" action="/equation/recherche">
                            <p>
                                <label for="a">Titre : </label>
                                <input type="number" name="a" placeholder="titre du livre cherché" step="any" value="" />
                                <c:if test="${!modele.aOk}"><label for="a">Erreur</label></c:if>
                            </p>
                            <p>
                                <label for="b">Auteur : </label>
                                <input type="number" name="b" placeholder="nom d'un auteur" step="any" value="" />  
                                <c:if test="${!modele.bOk}"><label for="b">Erreur</label></c:if>            
                            </p>
                            <p>
                                <label for="c">ISBN : </label>
                                <input type="number" name="c" placeholder="le numéro d'isbn" step="any" value="" />
                                <c:if test="${!modele.cOk}"><label for="c">Erreur</label></c:if> 
                            </p>
                            
                            <input type="submit" value="Lancer la recherche" />
                        </form>
                    </div>
                </div>
            </div>
        <!--</div>-->

        <!--<div class="container">
            <div class="col-md-6 col-xs-12 col-md-offset-3">
                <div class="panel panel-success">
                    <div class="panel-heading"><h4>Solution</h4></div>
                    <div class="panel-body">
                    <c:forEach var="message" items="${modele.messages}">
                        <p>${message}</p>
                    </c:forEach>
                        
                    </div>
                </div>
            </div> 
        </div>-->
    </body>
</html>
