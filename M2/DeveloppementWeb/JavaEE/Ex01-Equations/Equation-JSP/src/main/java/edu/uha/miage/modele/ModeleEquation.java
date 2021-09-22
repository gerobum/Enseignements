package edu.uha.miage.modele;

import edu.uha.miage.metier.CoefANul;
import edu.uha.miage.metier.IEquation;
import edu.uha.miage.metier.impl.EquationImpl;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;
import javax.servlet.http.HttpServletRequest;

/**
 * Le modèle doit "coller" avec la vue. Son objectif est garantir la production
 * de l'objet métier Equation et un message du succès si les données sont
 * correctes ou de produire des messages d'échecs appropriés en cas d'erreur.
 *
 * Dans la vue, les entrées sont les coefficients a, b et c.
 *
 * Même s'ils sont des entiers dans l'objet métier Equation, ils sont bien des
 * chaînes de caractères dans la vue.
 *
 * Même si la vue fait un petit travail préliminaire et semble empêcher l'envoi
 * de valeurs autres qu'entières. Il faut quand-même vérifier qu'ils sont
 * convertibles en entiers.
 *
 * les sorties sont
 * 
 * 1. En cas de succès :
 * 
 *     - l'objet métier
 *     - un message qui indique la solution 
 *
 * 2. En cas d'échec :
 * 
 *     - des messages d'erreur
 * 
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class ModeleEquation implements Serializable {

    // Dans la vue les coefficients sont des chaines
    private String a, b, c;
    // Pour que le message au bas de la page puisse tenir
    // sur plusieurs lignes.
    private List<String> messages = new LinkedList<>();
    // Pour distinguer différents types de messages d'erreur
    private boolean aOk, bOk, cOk;
    // L'objet métier
    private IEquation equation;

    /**
     * Modèle par défaut (la résolution de x^2 = 0)
     */
    public ModeleEquation() {
        try {
            equation = new EquationImpl(1, 0, 0);
            a = "1";
            b = "0";
            c = "0";
            messages.add(equation());
            messages.add(equation.toString());
            aOk = true;
            bOk = true;
            cOk = true;

        } catch (CoefANul ex) {

        }
    }

    /**
     * Contruit un modèle à partir d'une requète HTTP.
     * @param request
     * @return 
     */
    public static ModeleEquation handle(HttpServletRequest request) {

        ModeleEquation modele = new ModeleEquation();
        modele.messages.clear();

        // Récupération des valeurs des entrées a, b et c saisies dans la vue
        modele.a = request.getParameter("a");
        modele.b = request.getParameter("b");
        modele.c = request.getParameter("c");

        // Tentative de transformation en nombre à virgule
        double ca = 0, cb = 0, cc = 0;
        try {
            ca = Double.parseDouble(modele.a);
        } catch (NumberFormatException ex) {
            modele.aOk = false;
        }
        try {
            cb = Double.parseDouble(modele.b);
        } catch (NumberFormatException ex) {
            modele.bOk = false;
        }
        try {
            cc = Double.parseDouble(modele.c);
        } catch (NumberFormatException ex) {
            modele.cOk = false;
        }

        if (modele.aOk && modele.bOk && modele.cOk) {
            try {
                modele.equation = new EquationImpl(ca, cb, cc);
                modele.messages.add(modele.equation());
                modele.messages.add(modele.equation.toString());
            } catch (CoefANul ex) {
                modele.aOk = false;
                modele.messages.add("Le coefficient a ne doit pas être nul");
            }
        } else {
            if (!modele.aOk) {
                modele.aOk = false;
                modele.messages.add(String.format("Il y a un problème avec le coefficient a"));
                modele.messages.add(String.format("\"%s\" n'est pas un nombre à virgule ", modele.getA()));
            }
            if (!modele.bOk) {
                modele.messages.add("Il y a un problème avec le coefficient b");
                modele.messages.add(String.format("\"%s\" n'est pas un nombre à virgule ", modele.getB()));
            }
            if (!modele.cOk) {
                modele.messages.add("Il y a un problème avec le coefficient c");
                modele.messages.add(String.format("\"%s\" n'est pas un nombre à virgule ", modele.getC()));
            }
        }

        return modele;
    }


    /** 
     * @return  un "joli" premier membre de l'équation à partir du coef a
     */
    private String ma() {
        double ca = Double.parseDouble(a);
        String m;
        if (ca == 1.0) {
            m = "x&#xB2;";
        } else if (ca == -1.0) {
            m = "-x&#xB2;";
        } else {
            m = String.format(Locale.FRANCE, "%,fx&#xB2;", ca);
            //m = b + "x&#xB2;";
        }
        return m;
    }

    /** 
     * @return  un "joli" deuxième membre de l'équation à partir du coef b
     */
    private String mb() {
        double cb = Double.parseDouble(b);
        String m;
        if (cb == 0) {
            return "";
        } else if (cb == 1.0) {
            m = " + " + "x";
        } else if (cb == -1.0) {
            m = " - " + "x";
        } else if (cb < 0.0) {
            m = String.format(Locale.FRANCE, " - %fx", -cb);
            //m = " - " + b.replace("-", "") + "x";
        } else {
            m = String.format(Locale.FRANCE, " + %fx", cb);
            //m = " + " + b + "x";
        }
        return m;
    }

    /** 
     * @return  un "joli" troisième membre de l'équation à partir du coef c
     */
    private String mc() {
        double cc = Double.parseDouble(c);
        String m;
        if (cc == 0) {
            return "";
        } else if (cc < 0.0) {
            m = String.format(Locale.FRANCE, " - %f", -cc);
            //m = " - " + c.replace("-", "");
        } else {
            m = String.format(Locale.FRANCE, " + %f", cc);
            //m = " + " + c;
        }
        return m;
    }

    // PRE : Les coefficients a, b et c doivent être corrects.
    private String equation() {
        return String.format("%s%s%s = 0", ma(), mb(), mc());
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public String getB() {
        return b;
    }

    public void setB(String b) {
        this.b = b;
    }

    public String getC() {
        return c;
    }

    public void setC(String c) {
        this.c = c;
    }

    public List<String> getMessages() {
        return messages;
    }

    public void setMessages(List<String> messages) {
        this.messages = messages;
    }

    public IEquation getEquation() {
        return equation;
    }

    public void setEquation(IEquation equation) {
        this.equation = equation;
    }

    public boolean isaOk() {
        return aOk;
    }

    public void setaOk(boolean aOk) {
        this.aOk = aOk;
    }

    public boolean isbOk() {
        return bOk;
    }

    public void setbOk(boolean bOk) {
        this.bOk = bOk;
    }

    public boolean iscOk() {
        return cOk;
    }

    public void setcOk(boolean cOk) {
        this.cOk = cOk;
    }

}
