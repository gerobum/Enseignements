package fr.miage.firstapplijee.modèle;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class MagiqueForm implements Serializable {

    private String pseudo;
    private boolean connecte;
    private String message;

    private static DateFormat df = DateFormat.getDateTimeInstance(DateFormat.SHORT, DateFormat.SHORT);

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
            try {
                connecte = true;
                message = "Bienvenue " + pseudo + " !";
                cookie = new Cookie("pseudo", URLEncoder.encode(pseudo, "UTF-8"));
            } catch (UnsupportedEncodingException ex) {
                connecte = false;
                message = "";
            }
        } else {
            connecte = false;
            message = "Ce n'est pas la phrase magique. Reformule !";
        }
        // #### request.setAttribute("form", new MagiqueForm(pseudo, connecte, message));
        // #### Si mémoriser la connection pour la session.

        if (cookie != null) {
            // Expire dans une heure
            cookie.setMaxAge(60 * 60);
            response.addCookie(cookie);
        }

        request.getSession().setAttribute("form", new MagiqueForm(pseudo, connecte, message));
    }

    public static void setMagiqueFormFromCookie(HttpServletRequest request) {
        MagiqueForm form = new MagiqueForm();
        Cookie[] cookies = request.getCookies();

        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("pseudo".equals(cookie.getName())) {
                    try {
                        form.setConnecte(true);
                        form.setPseudo(URLDecoder.decode(cookie.getValue(), "UTF-8"));
                        form.setMessage("Bienvenue");
                        break;
                    } catch (UnsupportedEncodingException ex) {

                    }
                }
            }
        }
        request.setAttribute("form", form);
    }

    public static void logout(HttpServletRequest request, HttpServletResponse response) {

        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {

                if ("pseudo".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    response.addCookie(cookie);
                    //response.addCookie(new Cookie("logout", "OK"/*df.format(new Date())*/));
                }

            }
        }
    }
}
