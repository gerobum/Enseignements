
package fr.miage.controleurs;

import fr.miage.modele.ModeleEquation;
import fr.miage.metier.CoefANul;
import fr.miage.metier.IEquation;
import fr.miage.metier.impl.EquationImpl;
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
            request.getRequestDispatcher("/WEB-INF/eq.jsp").forward(request, response); // #### (1)
    }
    
        
    /*@Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {   
        try { // #### (2)
            IEquation equation = new EquationImpl(1, 0, 0); // #### (2)
            ModeleEquation modele = new ModeleEquation(equation.getA(), equation.getB(), equation.getC(), equation.toString());
            request.getRequestDispatcher("/WEB-INF/equation.jsp").forward(request, response); // #### (1)
        } catch (CoefANul ex) {  // #### (2) 
            // Ici, on est sur qu'il n'y aura pas d'exception car a=1.0 #### (2) 
        } // #### (2)
    }*/

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
            request.setAttribute("modele", ModeleEquation.handle(request));
            request.getRequestDispatcher("/WEB-INF/eq.jsp").forward(request, response);
    }
    
    /*@Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            request.setAttribute("modele", ModeleEquation.handle(request));
            request.getRequestDispatcher("/WEB-INF/equation.jsp").forward(request, response);
        } catch (CoefANul ex) {
            // #### (3) Il faut gérer l'exception.ModeleEquation modele = new ModeleEquation(equation.getA(), equation.getB(), equation.getC(), equation.toString()); // #### (2)
            ModeleEquation modele = new ModeleEquation(0.0, Double.parseDouble(request.getParameter("b")), Double.parseDouble(request.getParameter("c")), "Le coefficient a ne doit pas être nul"); // #### (2)
            request.setAttribute("modele", modele); // #### (2)
            request.getRequestDispatcher("/WEB-INF/equation.jsp").forward(request, response);            
        }
    }*/
}
