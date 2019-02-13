
package fr.miage.firstapplijee.web;

import fr.miage.firstapplijee.forms.MagiqueForm;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse; 

@WebServlet(name = "PassageSecret", urlPatterns = {"/PassageSecret", "/ps"})
public class PassageSecret extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie : cookies) {
            if ("pseudo".equals(cookie.getName())) {
                request.setAttribute("pseudo", cookie.getValue());
                break;
            }
        }
        
        request.getRequestDispatcher("/WEB-INF/passagesecret.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        MagiqueForm.handle(request, response);
        request.getRequestDispatcher("/WEB-INF/passagesecret.jsp").forward(request, response);
    }
}
