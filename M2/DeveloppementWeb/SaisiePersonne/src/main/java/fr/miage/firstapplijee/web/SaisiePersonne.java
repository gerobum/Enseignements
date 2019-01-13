/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.firstapplijee.web;

import fr.miage.firstapplijee.forms.PersonForm;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yvan
 */
@WebServlet(name = "SaisiePersonne", urlPatterns = {"/SaisiePersonne", "/sp"})
public class SaisiePersonne extends HttpServlet {

    private PersonForm pf;

    @Override
    public void init() throws ServletException {
        super.init();
        read();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("pf", pf);
        request.getRequestDispatcher("/WEB-INF/SaisiePersonne.jsp").forward(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        /*String nom = (String) request.getParameter("nom");
        String prenom = (String) request.getParameter("prenom");
        int age;
        try {
            age = Integer.parseInt((String) request.getParameter("age"));
        } catch (NumberFormatException ex) {
            age = 0;
        }
        boolean homme = request.getParameter("genre").equals("Homme");
        personne = new Personne(homme, nom, prenom, age);
        request.setAttribute("personne", personne);*/
        pf = PersonForm.handle(request);

        request.getRequestDispatcher("/WEB-INF/SaisiePersonne.jsp").forward(request, response);
    }

    @Override
    public void destroy() {
        super.destroy();
        write();
    }

    private void read() {
        
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream("pf.bin"))) {
            pf = (PersonForm) in.readObject();
            pf.setMessage("Chargement OK");
        } catch (FileNotFoundException ex) {
            pf = new PersonForm("---" + ex + "---");
        } catch (IOException | ClassNotFoundException | ClassCastException ex) {
            pf = new PersonForm("---" + ex + "---");

        }
    }

    private void write() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("pf.bin"))) {
            out.writeObject(pf);
        } catch (FileNotFoundException ex) {

        } catch (IOException ex) {

        }
    }
}
