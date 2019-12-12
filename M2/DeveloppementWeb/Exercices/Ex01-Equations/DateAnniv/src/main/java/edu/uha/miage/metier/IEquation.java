
package edu.uha.miage.metier;

/**
 * L'interface de l'objet métier Equation. 
 * 
 * L'utilisation des interfaces pour favoriser l'injection de dépendances.
 * 
 * C'est une bonne habitude à prendre.
 * 
 * @author yvan
 */
public interface IEquation {
    double getA();
    double getB();
    double getC();
    int getNbRacines();
    double getX1();
    double getX2();
}
