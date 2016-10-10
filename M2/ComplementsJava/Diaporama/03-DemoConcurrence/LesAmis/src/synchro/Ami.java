/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package synchro;

/**
 *
 * @author maillot
 */
public class Ami {

    public final String nom;
    private int num = 0;

    public Ami(String nom) {
        this.nom = nom;
    }

    public synchronized void estSalu�Par(Ami ami) {
        System.out.println(ami.nom + " salue " + nom + " (" + num++ + ")");
        ami.estSalu�EnRetourPar(this);
    }

    public synchronized void estSalu�EnRetourPar(Ami ami) {
        System.out.println(ami.nom + " r�pond a " + nom);
    }
}
