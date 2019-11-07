
package fr.miage.controleurs;

import fr.miage.modele.ModeleEquation;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet(name = "ControleurEquation", urlPatterns = {"/2d"})
public class ControleurEquation extends HttpServlet {
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {   
            request.setAttribute("modele", new ModeleEquation());
            //request.getRequestDispatcher("/WEB-INF/equation.jsp").forward(request, response); // #### (0)
            request.getRequestDispatcher("/WEB-INF/equation_jstl_sans_bootstrap.jsp").forward(request, response); // #### (1)
            //request.getRequestDispatcher("/WEB-INF/equation_jstl.jsp").forward(request, response); // #### (2)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.setAttribute("modele", ModeleEquation.handle(request));
            //request.getRequestDispatcher("/WEB-INF/equation.jsp").forward(request, response);
            request.getRequestDispatcher("/WEB-INF/equation_jstl_sans_bootstrap.jsp").forward(request, response);
            //request.getRequestDispatcher("/WEB-INF/equation_jstl.jsp").forward(request, response);
    }
}
