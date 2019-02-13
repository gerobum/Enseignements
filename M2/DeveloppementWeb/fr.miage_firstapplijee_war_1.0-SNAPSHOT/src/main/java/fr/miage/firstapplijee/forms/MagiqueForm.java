
package fr.miage.firstapplijee.forms;

import java.io.Serializable;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author yvan
 */
public class MagiqueForm implements Serializable {
    private String pseudo;
    private boolean connecte;
    private String message;

    public MagiqueForm(String pseudo, boolean connecte, String message) {
        this.pseudo = pseudo;
        this.connecte = connecte;
        this.message = message;
    }

    public MagiqueForm() {
        connecte = false;
    }
    
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public boolean isConnecte() {
        return connecte;
    }

    public void setConnecte(boolean connecte) {
        this.connecte = connecte;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    
    public static void handle(HttpServletRequest request, HttpServletResponse response) {
        String pseudo = request.getParameter("pseudo");
        String phrase = request.getParameter("magique");
        boolean connecte;
        String message;
        Cookie cookie = null;
        
        if (pseudo == null || pseudo.isEmpty()) {
            connecte = false;
            message = "T'as bien un pseudo !";
        } else if ("Vive Java".equals(phrase)) {            
            connecte = true;
            message = "Bienvenue "+ pseudo +" !";
            cookie = new Cookie("pseudo", pseudo);
        } else {
            connecte = false;
            message = "Ce n'est pas la phrase magique. Reformule !";
        }
        // #### request.setAttribute("form", new MagiqueForm(pseudo, connecte, message));
        // #### Si mémoriser la connection pour la session.
        
        if (cookie != null) {
            // Expire dans une heure
            cookie.setMaxAge(60*60);
            response.addCookie(cookie);
        }
        
        request.getSession().setAttribute("form", new MagiqueForm(pseudo, connecte, message));
    }
}
