/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.firstapplijee.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yvan
 */
@WebServlet(name = "InfosEtEntetes", urlPatterns = {"/infosetentetes"})
public class InfosEtEntetes extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Info</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Info at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            /*out.print("<!DOCTYPE html>");
            out.print("<html>");
            out.print("<head>");
            out.print("<title>Infos et en-têtes</title></head>");
            out.print("<body>");
            out.print("<h1>Quelques informations</h1>");
            out.print("<ul>");
            out.print("<li>Adresse : "
                    + request.getServerName() + ":"
                    + request.getLocalPort()
                    + request.getRequestURI()
                    + "</li>");
            out.print("<li>Protocole : " + request.getProtocol() + "</li>");
            out.print("<li>Méthode HTTP : " + request.getMethod() + "</li>");
            out.print("</ul>");
            out.print("<h1>Les en-têtes HTTP</h1>");
            Enumeration<String> hn = request.getHeaderNames();
            out.print("<ul>");
            while (hn.hasMoreElements()) {
                String entete = hn.nextElement();
                out.print("<li>" + entete + " : " + Stringify(request.getHeaders(entete)) + "</li>");
            }
            out.print("</ul>");
            out.print("</body>");
            out.print("</html>");*/
            
            request.getRequestDispatcher("WEB-INF/infosetentetesel.jsp").forward(request, response);
        }
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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

    private String Stringify(Enumeration<String> headers) {
        StringBuilder sb = new StringBuilder();
        if (headers.hasMoreElements()) {
            sb.append(headers.nextElement());
            while (headers.hasMoreElements()) {
                sb.append(", ").append(headers.nextElement());
            }
        }
        return sb.toString();
    }
}
