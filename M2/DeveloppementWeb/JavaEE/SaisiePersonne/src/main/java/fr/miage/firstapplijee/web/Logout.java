
package fr.miage.firstapplijee.web;

import fr.miage.firstapplijee.modèle.MagiqueForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "Logout", urlPatterns = {"/logout"})
public class Logout extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MagiqueForm.setMagiqueFormFromCookie(request);
        request.getRequestDispatcher("/WEB-INF/logout_jslt.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MagiqueForm.logout(request, response);
        response.sendRedirect("/demo/ps");
    }
}
