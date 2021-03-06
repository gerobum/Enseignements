/*
 * Copyright (C) 2019 Yvan Maillot <yvan.maillot@uha.fr>
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.uha.miage.Controllers;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
@WebServlet(name = "Accueil9", urlPatterns = {"/accueil9"})
public class Accueil09 extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Pour montrer l'exploitation d'un éventuel paramètre dans l'URL
        // (Cf. page 9 de base-jee-fascicule.pdf)
        String nom = request.getParameter("nom");
        nom = nom == null ? "tout le monde" : nom;
        // -----------------------------------
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            out.print("<!DOCTYPE html><html><head>");
            out.print("<title>Accueil</title></head><body>");
            // Utilisation de sa valeur
            out.print("<h1>Bienvenue " + nom + "</h1>");
            // -------------------------------
            out.print("</body></html>");
        }
    }
}
