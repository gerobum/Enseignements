package fr.miage.firstapplijee.forms;

import fr.miage.firstapplijee.metier.Personne;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yvan
 */
public class ConnexionForm {

    private String pseudo;
    private String age;
    private boolean connecte;
    private String message;
    private String code;
    private boolean pseudoAbsent;
    private boolean ageErronne;
    private Personne personne;

    public boolean isPseudoAbsent() {
        return pseudoAbsent;
    }

    public void setPseudoAbsent(boolean pseudoAbsent) {
        this.pseudoAbsent = pseudoAbsent;
    }

    public ConnexionForm() {
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Personne getPersonne() {
        return personne;
    }

    public void setPersonne(Personne personne) {
        this.personne = personne;
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

        ConnexionForm form = new ConnexionForm();
        form.pseudo = request.getParameter("pseudo");
        form.connecte = false;
        int age;
        try {
            form.age = request.getParameter("age");
            age = Integer.parseInt(form.age);
            form.ageErronne = false;
        } catch (NumberFormatException ex) {
            form.ageErronne = true;
            age = -1;
        }
        String code = request.getParameter("code");

        if (form.ageErronne) {
            form.message = "Âge erronné";
            form.connecte = false;
        } else {
            if (age < 0 || age > 150) {
                form.message = "Âge incorrect ! Corrigez et tapez le code secret";
            } else if (age < 10) {
                form.message = "Vous êtes trop jeune.";
            } else {
                if (form.pseudo == null || form.pseudo.isEmpty()) {
                    form.message = "Pseudo incorrect ! Corrigez et tapez le code secret";
                    form.connecte = false;
                } else if ("Vive Java".equals(code)) {
                    form.connecte = true;
                    form.message = "Vous êtes connecté";
                } else {
                    form.message = "Code incorrect ! Retapez-le";
                    form.connecte = false;
                }
            }
        }

        request.getSession().setAttribute("form", form);
    }

}
