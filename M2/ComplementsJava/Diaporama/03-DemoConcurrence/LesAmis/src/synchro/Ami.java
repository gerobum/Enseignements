
package synchro;

/**
 *
 * @author maillot
 */
public class Ami {

    public final String NOM;
    private int num = 0;

    public Ami(String nom) {
        this.NOM = nom;
    }

    public synchronized void estSaluéPar(Ami ami) {
        System.out.println(ami.NOM + " salue " + NOM + " (" + num++ + ")");
        ami.estSaluéEnRetourPar(this);
    }

    public synchronized void estSaluéEnRetourPar(Ami ami) {
        System.out.println(ami.NOM + " répond a " + NOM);
    }
}
