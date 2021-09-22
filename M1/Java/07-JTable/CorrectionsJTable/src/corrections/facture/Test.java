

package corrections.facture;

import corrections.facture.article.Articles;



/**
 *
 * @author Maillot
 */
public class Test {
    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Facture();
            }
        });
    }
}
