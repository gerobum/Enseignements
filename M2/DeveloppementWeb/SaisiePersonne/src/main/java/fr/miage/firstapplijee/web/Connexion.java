
package fr.miage.firstapplijee.web;

import fr.miage.firstapplijee.forms.Nom;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yvan
 */
@WebServlet(name = "Connexion", urlPatterns = {"/connexion"})
public class Connexion extends HttpServlet {
    private Nom nom = new Nom();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("form", nom);
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Nom form = (Nom) request.getAttribute("form");
        String nom = request.getParameter("nom");
        request.setAttribute("nom", nom);
        request.getRequestDispatcher("/WEB-INF/login.jsp").forward(request, response);
    }


}
