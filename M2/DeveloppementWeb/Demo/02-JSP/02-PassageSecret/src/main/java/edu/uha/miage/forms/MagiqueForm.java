package edu.uha.miage.forms;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private static Cookie getCookiePseudo(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("pseudo".equals(cookie.getName())) {
                    return cookie;
                }
            }
        }
        return null;
    }

    /**
     * Méthode qui sera appelé à l'occasion d'une requête HTTP GET ou POST
     *
     * S'il y a un cookie pseudo, c'est que la personne est déjà connectée.
     * (GET)
     *
     * Dans ce cas, un message de bienvenue
     *
     * Sinon
     *
     * Vérification des champs de POST
     *
     *
     * @param request
     * @param response
     */
    public static void handle(HttpServletRequest request, HttpServletResponse response) {
        String pseudo = request.getParameter("pseudo");
        String phrase = request.getParameter("magique");
        boolean connecte;
        String message;

        Cookie cookie = getCookiePseudo(request);

        if (cookie != null) { // Déjà connecté
            connecte = true;
            try {
                pseudo = URLDecoder.decode(cookie.getValue(), "UTF-8");
            } catch (UnsupportedEncodingException ex) {
                pseudo = cookie.getValue();
            }
            message = "Bienvenue " + cookie.getValue() + " !";
        } else {
            if (pseudo == null || pseudo.isEmpty()) {
                connecte = false;
                message = "T'as bien un pseudo !";
            } else if ("Vive Java".equals(phrase)) {
                connecte = true;
                message = "Bienvenue " + pseudo + " !";
                try {
                    cookie = new Cookie("pseudo", URLEncoder.encode(pseudo, "UTF-8"));
                } catch (UnsupportedEncodingException ex) {
                    cookie = null;
                }
            } else {
                connecte = false;
                message = "Ce n'est pas la phrase magique. Reformule !";
            }
        }

        if (cookie != null) {
            // Expire dans une heure
            cookie.setMaxAge(60 * 60);
            try {
                response.addCookie(cookie);
            } catch (IllegalArgumentException ex) {
                System.out.println(ex);
                System.out.println(cookie);
            }
        }

        request.getSession().setAttribute("form", new MagiqueForm(pseudo, connecte, message));
    }

    /**
     * Pour se déconnecter
     *
     * @param request
     * @param response
     */
    public static void logout(HttpServletRequest request, HttpServletResponse response) {
        Cookie cookie = getCookiePseudo(request);

        if (cookie != null) {
            cookie.setMaxAge(0);
            response.addCookie(cookie);
        }
        request.getSession().setAttribute("form", new MagiqueForm("", false, ""));
    }
}
