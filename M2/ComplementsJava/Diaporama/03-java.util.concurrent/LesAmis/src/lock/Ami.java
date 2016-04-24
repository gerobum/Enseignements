/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lock;

import java.util.concurrent.locks.ReentrantLock;

/**
 *
 * @author maillot
 */
public class Ami {

    public final String nom;
    private int num = 0;
    public ReentrantLock verrou = new ReentrantLock();

    public Ami(String nom) {
        this.nom = nom;
    }

    public void estSalu�Par(Ami ami) {
        boolean monVerrou = false;
        boolean tonVerrou = false;
        try {
            monVerrou = verrou.tryLock();
            tonVerrou = ami.verrou.tryLock();
            if (monVerrou && tonVerrou) { // Les deux verrous �taient libres, on peut y aller
                System.out.println(ami.nom + " salue " + nom + " (" + num++ + ")");
                ami.estSalu�EnRetourPar(this);
            }
        } finally {
            if (monVerrou) {
                verrou.unlock();
            }
            if (tonVerrou) {
                ami.verrou.unlock();
            }
        }
    }

    public void estSalu�EnRetourPar(Ami ami) {
        verrou.lock();
        try {
            System.out.println(ami.nom + " r�pond a " + nom);
        } finally {
            verrou.unlock();
        }
    }

}
