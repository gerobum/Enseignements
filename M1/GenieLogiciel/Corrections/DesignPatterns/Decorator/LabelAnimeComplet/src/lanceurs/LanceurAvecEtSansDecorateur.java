package lanceurs;

import javax.swing.SwingUtilities;

public class LanceurAvecEtSansDecorateur {

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new avec_decorateur.swing.FenetreAvecTroisAnimations("À animer");
            }
        });
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new sans_decorateur.swing.FenetreSansDecorateur("Démonstration");
            }
        });

    }
}
