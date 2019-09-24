package fr.miage.modele;

import fr.miage.metier.CoefANul;
import fr.miage.metier.IEquation;
import fr.miage.metier.impl.EquationImpl;
import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

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

    public static ModeleEquation handle(HttpServletRequest request) {

        ModeleEquation modele = new ModeleEquation();
        modele.messages.clear();

        modele.a = request.getParameter("a");
        modele.b = request.getParameter("b");
        modele.c = request.getParameter("c");

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
            if (!modele.aOk || ca == 0.0) {
                modele.aOk = false;
                modele.messages.add("Il y a un problème avec le coefficient a");
            }
            if (!modele.bOk) {
                modele.messages.add("Il y a un problème avec le coefficient b");
            }
            if (!modele.cOk) {
                modele.messages.add("Il y a un problème avec le coefficient c");
            }
        }

        return modele;
    }
    
    private String mb() {
        double cb = Double.parseDouble(b);
        String m;
        if (cb == 0)
            return "";
        else if (cb == 1.0)
            m = " + "+"x";
        else if (cb == -1.0)
            m = " - "+"x";
        else if (cb < 0.0)
            m = " - " + b.replace("-", "")+"x";
        else
            m = " + " + b+"x";
        return m;
    }
    private String mc() {
        double cc = Double.parseDouble(c);
        String m;
        if (cc == 0)
            return "";
        else if (cc < 0.0)
            m = " - " + c.replace("-", "");
        else
            m = " + " + c;
        return m;
    }

    // PRE : Les coefficients a, b et c doivent être corrects.
    private String equation() {
        double ca = Double.parseDouble(a);
        String sa;
        if (ca == 1.0)
            sa = "";
        else if (ca == -1.0)
            sa = "-";
        else
            sa = a+"";
        return String.format("%sx&#xB2;%s%s", sa, mb(), mc());
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
