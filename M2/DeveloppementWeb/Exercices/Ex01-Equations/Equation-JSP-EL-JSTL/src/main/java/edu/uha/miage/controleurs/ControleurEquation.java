package edu.uha.miage.controleurs;

import edu.uha.miage.modele.ModeleEquation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ControleurEquation", urlPatterns = {"/2d"})
public class ControleurEquation extends HttpServlet {

    private static final String vueFileName = "equation.jsp";
    //private static String vueFileName = "equation.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("modele", new ModeleEquation());
        request.getRequestDispatcher("/WEB-INF/" + vueFileName).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("modele", ModeleEquation.handle(request));
        request.getRequestDispatcher("/WEB-INF/" + vueFileName).forward(request, response);
    }
}
