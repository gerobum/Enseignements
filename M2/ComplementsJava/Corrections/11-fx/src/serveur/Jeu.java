package serveur;

/**
 *
 * @author yvan
 */
public class Jeu extends Thread {
    private Pair pair;
    private boolean notFinished = false;

    @Override
    public void run() {
        while (notFinished) {
            
        }
    }


    private class Pair {
        int p1, p2;

        public Pair(int p1, int p2) {
            this.p1 = p1;
            this.p2 = p2;
        }
    }
}
