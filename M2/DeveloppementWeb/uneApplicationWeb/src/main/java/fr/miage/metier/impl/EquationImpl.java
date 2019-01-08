/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.miage.metier.impl;

import fr.miage.metier.CoefANul;
import fr.miage.metier.IEquation;

/**
 *
 * @author yvan
 */
public class EquationImpl implements IEquation {

    public final double a;
    public final double b;
    public final double c;
    public final int nbRacines;
    private double delta;
    public final Double x1;
    public final Double x2;

    public EquationImpl(double a, double b, double c) throws CoefANul {
        if (a == 0.0) throw new CoefANul();
        this.a = a;
        this.b = b;
        this.c = c;
        
        delta = b*b-4*a*c;
        Double x1, x2;
        if (delta < 0) {
            this.nbRacines = 0;
            x1 = x2 = null;
        } else if (delta == 0) {
            this.nbRacines = 1;
            x1 = x2 = -b/(2*a);
        } else {
            this.nbRacines = 2;
            double rdelta = Math.sqrt(delta);
            x1 = (-b-rdelta)/(2*a);
            x2 = (-b+rdelta)/(2*a);
        }
        this.x1 = x1;
        this.x2 = x2;
    }
    
    

    @Override
    public int getNbRacines() {
        return nbRacines;
    }

    @Override
    public double getX1() {
        return x1;
    }

    @Override
    public double getX2() {
        return x2;
    }

    @Override
    public double getA() {
        return a;
    }

    @Override
    public double getB() {
        return b;
    }

    @Override
    public double getC() {
        return c;
    }
    
    @Override
    public String toString() {
        switch (nbRacines) {
            case 0:
                return "Il n'y a pas de solution";
            case 1:
                return "Il y a une solution double qui est " + x1;
            default:
                return "Il y a deux solutions qui sont " + x1 + " et " + x2;
        }
    }
}
