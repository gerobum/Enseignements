package fr.miage;

import fr.miage.metier.CoefANul;
import fr.miage.metier.IEquation;
import fr.miage.metier.impl.EquationImpl;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yvan
 */
public class ModeleEquation implements Serializable {

    private String a, b, c;
    private String resultat;
    private IEquation equation;
    private boolean aOk, bOk, cOk;

    public ModeleEquation() {
        try {
            this.equation = new EquationImpl(1, 0, 0);
            this.a = "1";
            this.b = "0";
            this.c = "0";
            this.resultat = this.equation.toString();
            this.aOk = true;
            this.bOk = true;
            this.cOk = true;

        } catch (CoefANul ex) {

        }
    }

    public static ModeleEquation handle(HttpServletRequest request) {
        
        ModeleEquation modele = new ModeleEquation();
        
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
                modele.resultat = modele.equation.toString();
            } catch (CoefANul ex) {
                modele.aOk = false;
                modele.resultat = "Le coefficient a ne doit pas être nul";
            }
        } else {
            modele.resultat = "Il y a un problème avec un coefficient";
        }

        return modele;
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

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
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
