/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.controleurs;

import fr.miage.ModeleEquation;
import fr.miage.metier.CoefANul;
import fr.miage.metier.IEquation;
import fr.miage.metier.impl.EquationImpl;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yvan
 */
@WebServlet(name = "ControleurEquation", urlPatterns = {"/2d"})
public class ControleurEquation extends HttpServlet {
    
    
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        try { // #### (2)
            IEquation equation = new EquationImpl(1, 0, 0); // #### (2)
            ModeleEquation modele = new ModeleEquation(equation.getA(), equation.getB(), equation.getC(), equation.toString());
            request.setAttribute("modele", modele); // #### (2)
            request.getRequestDispatcher("/WEB-INF/equation.jsp").forward(request, response); // #### (1)
        } catch (CoefANul ex) {  // #### (2) 
            // Ici, on est sur qu'il n'y aura pas d'exception car a=1.0 #### (2) 
        } // #### (2)
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            // #### (2) Il faut faire aussi le DataBinding ici...
            // #### (2) POST signifie que des données ont été saisies.
            // #### (2) Il faut faire un nouveau calcul.
            // #### (2) Impossible d'utiliser getAttribute() ici, mais getParameter())
            IEquation equation = new EquationImpl(Double.parseDouble(request.getParameter("a")), Double.parseDouble(request.getParameter("b")), Double.parseDouble(request.getParameter("c"))); // #### (2)
            ModeleEquation modele = new ModeleEquation(equation.getA(), equation.getB(), equation.getC(), equation.toString()); // #### (2)
            request.setAttribute("modele", modele); // #### (2)
            request.getRequestDispatcher("/WEB-INF/equation.jsp").forward(request, response);
        } catch (CoefANul ex) {
            // #### (3) Il faut gérer l'exception.ModeleEquation modele = new ModeleEquation(equation.getA(), equation.getB(), equation.getC(), equation.toString()); // #### (2)
            ModeleEquation modele = new ModeleEquation(0.0, Double.parseDouble(request.getParameter("b")), Double.parseDouble(request.getParameter("c")), "Le coefficient a ne doit pas être nul"); // #### (2)
            request.setAttribute("modele", modele); // #### (2)
            request.getRequestDispatcher("/WEB-INF/equation.jsp").forward(request, response);            
        }
    }
}
