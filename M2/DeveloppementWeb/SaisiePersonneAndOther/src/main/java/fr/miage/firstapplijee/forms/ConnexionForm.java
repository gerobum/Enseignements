package fr.miage.firstapplijee.forms;

import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yvan
 */
public class ConnexionForm {

    private String pseudo;
    private int age;
    private boolean connecte;
    private String message;
    private String code;

    public ConnexionForm() {
        this.pseudo = "";
        this.age = -1;
        this.connecte = false;
        this.message = "Entre ton pseudo, ton âge et le code secret.";
    }

    public ConnexionForm(String pseudo, int age, boolean connecte, String message) {
        this.pseudo = pseudo;
        this.age = age;
        this.connecte = connecte;
        this.message = message;
    }

    public ConnexionForm(String pseudo, int age, boolean connecte, String message, String code) {
        this.pseudo = pseudo;
        this.age = age;
        this.connecte = connecte;
        this.message = message;
        this.code = code;
    }
    
    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
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

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
    
    

    public static void handle(HttpServletRequest request) {
        String pseudo;
        pseudo = request.getParameter("pseudo");
        int age;
        try {
            age = Integer.parseInt(request.getParameter("age"));
        } catch (NumberFormatException ex) {
            age = -1;
        }
        String code = request.getParameter("code");
        boolean connecte = false;
        String message;

        if (age < 0 || age > 150) {
            message = "Âge incorrect ! Corrigez et tapez le code secret";
        } else if (age < 10) {
            message = "Vous êtes trop jeune.";
        } else if (pseudo == null || pseudo.isEmpty()) {
            message = "Pseudo incorrect ! Corrigez et tapez le code secret";
        } else if ("abc".equals(code)) {
            connecte = true;
            message = "Vous êtes connecté";
        } else {
            message = "Code incorrect ! Retapez-le";
        }

        request.getSession().setAttribute("form", new ConnexionForm(pseudo, age, connecte, message, code));
    }

}
