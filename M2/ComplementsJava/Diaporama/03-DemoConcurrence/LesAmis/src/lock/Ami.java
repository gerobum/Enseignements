
package lock;

import java.util.concurrent.locks.ReentrantLock;

/*
 *
 * @author maillot
 */
public class Ami {

    public final String NOM;
    private int num = 0;
    public ReentrantLock verrou = new ReentrantLock();

    public Ami(String nom) {
        this.NOM = nom;
    }

    public void estSaluéPar(Ami ami) {
        boolean monVerrou = false;
        boolean tonVerrou = false;
        try {
            monVerrou = verrou.tryLock();
            tonVerrou = ami.verrou.tryLock();
            if (monVerrou && tonVerrou) { // Les deux verrous étaient libres, on peut y aller
                System.out.println(ami.NOM + " salue " + NOM + " (" + num++ + ")");
                ami.estSaluéEnRetourPar(this);
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

    public void estSaluéEnRetourPar(Ami ami) {
        verrou.lock();
        try {
            System.out.println(ami.NOM + " répond a " + NOM);
        } finally {
            verrou.unlock();
        }
    }

}
