/*
 * @author : Yvan Maillot (yvan.maillot@uha.fr)
 */
package vocabulaire;

import static tags.CheckModifier.*;
import tags.GetterToCheck;
import tags.SetterToCheck;
import tags.ToCheck;

@ToCheck
public class A {
    
    @ToCheck(modifiers = {isFinal, isPrivate, isProtected, isPublic, isStatic})
    @SetterToCheck
    // TODO déclarer a, un attribut d'instance, int, privé 
    private int a;
    
    @ToCheck(modifiers = {isFinal, isPrivate, isProtected, isPublic, isStatic})
    @SetterToCheck
    // TODO déclarer B, un attribut d'instance, constant, char, protégé
    protected final char B;
    
    @ToCheck(modifiers = {isFinal, isPrivate, isProtected, isPublic, isStatic})
    @GetterToCheck
    /* TODO déclarer C, un attribut de classe, String, visibilité de paquetage 
    initilisé à "C".
    */
    static final String C = "C";
    
    @ToCheck(modifiers = {isFinal, isPrivate, isProtected, isPublic, isStatic})
    /* TODO définir un constructeur à 2 paramètres int a et char b pour initialiser 
    les attributs a et B. */
    public A(int a, char b) {
        this.a = a;
        this.B = b;
    }
    
    @ToCheck(modifiers = {isFinal, isPrivate, isProtected, isPublic, isStatic})
    /* TODO définir le constructeur par défaut qui initialise les attributs
    a à 0 et B à 'B'. */
    public A() {
        this(0, 'B');
    }
    
    /* TODO définir des getters et des éventuels setters pour les attributs a,
    B et C. */
    
    @ToCheck(modifiers = {isFinal, isPrivate, isProtected, isPublic, isStatic})
    public int getA() {
        return a;
    }
    
    @ToCheck(modifiers = {isFinal, isPrivate, isProtected, isPublic, isStatic})
    public void setA(int a) {
        this.a = a;
    }

    @ToCheck(modifiers = {isFinal, isPrivate, isProtected, isPublic, isStatic})
    public char getB() {
        return B;
    }

    @ToCheck(modifiers = {isFinal, isPrivate, isProtected, isPublic, isStatic})
    public static String getC() {
        return C;
    }
    
    public String quiSuisJe() {
        return "A";
    }
    
    public String quiSuisEtdOuViensJe() {
        return  "A -> Object";
    }
    
    protected String abc() {
        return a+B+C;
    }
    
    /*
    public String quiSuisEtdOuViensJe() {
        return "A -> Object";
    }*/
}
