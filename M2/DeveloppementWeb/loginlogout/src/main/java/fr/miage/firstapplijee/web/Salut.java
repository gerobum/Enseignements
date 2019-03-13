package fr.miage.firstapplijee.web;

import fr.miage.firstapplijee.metier.Personne;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Salut", urlPatterns = {"/salut"})
public class Salut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        boolean genre = "homme".equals(request.getParameter("genre"));
        String nom, prenom;
        nom = request.getParameter("nom");
        nom = nom == null ? "" : nom;
        prenom = request.getParameter("prenom");
        prenom = prenom == null ? "" : prenom;
        int age;
        try {
            age = Integer.parseInt(request.getParameter("age"));
        } catch (NumberFormatException ex) {
            age = 0;
        }
        Personne p = new Personne(
                genre,
                nom,
                prenom,
                age);
        request.setAttribute("personne", p);
        request.getRequestDispatcher("WEB-INF/salutel.jsp").forward(request, response);
    }
}
