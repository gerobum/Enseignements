/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package arithmetique;

/**
 *
 * @author yvan
 */
public class Variable extends Expression {
    private double valeur;

    public Variable(double valeur) {
        this.valeur = valeur;
    }

    public void setValeur(double valeur) {
        this.valeur = valeur;
    }    

    @Override
    public double evaluer() {
        return valeur;
    }
    
}