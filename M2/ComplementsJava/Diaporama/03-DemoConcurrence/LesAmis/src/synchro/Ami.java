
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

    public synchronized void estSalu�Par(Ami ami) {
        System.out.println(ami.NOM + " salue " + NOM + " (" + num++ + ")");
        ami.estSalu�EnRetourPar(this);
    }

    public synchronized void estSalu�EnRetourPar(Ami ami) {
        System.out.println(ami.NOM + " r�pond a " + NOM);
    }
}
