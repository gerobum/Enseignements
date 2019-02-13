
package fr.miage.firstapplijee.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "Accueil", urlPatterns = {"/"})
public class Accueil extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /* #### (JSLT)  */ String nom = request.getParameter("nom");
        /* #### (JSLT)  */ request.setAttribute("nom", nom);
        request.getRequestDispatcher("WEB-INF/accueil_jslt.jsp").forward(request, response);
    }
}
