/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.controleurs;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yvan
 */
  
//@WebServlet(urlPatterns = {"/equation"})
public class Accueil extends HttpServlet {    
  @Override
  protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    // Récupération de l'éventuel paramètre       
    request.getRequestDispatcher("/WEB-INF/index.html").forward(request, response); 
    
    //response.sendRedirect("accueil.jsp?"+request.getQueryString());
    //String nom = request.getParameter("nom");
    //nom = nom==null?"":nom;
    // -----------------------------------
    /*response.setContentType("text/html;charset=UTF-8");
    try (PrintWriter out = response.getWriter()) {
        out.print("<!DOCTYPE html><html><head>");
        out.print("<title>Accueil</title></head><body>");
        // Utilisation de sa valeur
        out.print("<h1>Bienvenue "+nom+"</h1>");
        // -------------------------------
        out.print("</body></html>");
    }*/
  }
}
