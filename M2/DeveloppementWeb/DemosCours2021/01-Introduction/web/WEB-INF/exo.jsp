<%-- 
    Document   : exo
    Created on : 10 sept. 2021, 15:16:39
    Author     : Yvan Maillot <yvan.maillot@uha.fr>
--%>
<%
    String nom = request.getParameter("nom");
    String prenom = request.getParameter("prenom");
    String s_age = request.getParameter("age");
    String genre = request.getParameter("genre");
    String civil;
    String phrase;
    int age;

    if ("H".equals(genre)) {
        civil = "Monsieur ";
    } else if ("F".equals(genre)) {
        civil = "Madame ";
    } else {
        civil = "";
    }
    try {
        age = Integer.parseInt(s_age);
    } catch (Exception ex) {
        age = -1;
    }
    
    if (age > 1) {
        s_age = "(" + age + " ans)";
    } else if (age > 0) {
        s_age = "(1 an)";
    } else {
        s_age = "";
    }
    
    nom = (nom == null) ? "" : nom + " ";
    
    prenom = (prenom == null) ? "" : prenom + " ";
        
    phrase = civil+prenom+nom+s_age;

%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Bonjour <%=phrase%></h1>
    </body>
</html>
