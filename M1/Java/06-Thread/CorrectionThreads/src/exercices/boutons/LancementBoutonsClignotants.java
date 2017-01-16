package exercices.boutons;

import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JFrame;

public class LancementBoutonsClignotants extends JFrame {

    public LancementBoutonsClignotants() {
        initUI();
    }

    public static void main(String[] args) {
        new LancementBoutonsClignotants();
    }

    private void initUI() {
        getContentPane().setLayout(new GridLayout(0, 1));
        getContentPane().add(new exercices.boutons.implementation.BoutonClignotant("Bling Bling !!!", new Color(0, 255, 0), new Color(0, 100, 0)));
        getContentPane().add(new exercices.boutons.interruption.BoutonClignotantAvecArret("Je m'arrête de clignoter et je m'éteinds quand on clique", new Color(0, 0, 255), new Color(50, 0, 50)));
        getContentPane().add(new exercices.boutons.derivation.BoutonClignotant("Bling Bling !!!", new Color(255, 0, 0), new Color(100, 0, 0)), "South");

        getContentPane().add(new exercices.boutons.interruption.BoutonClignotantAvecArretEtDepart("Stop and go", Color.YELLOW, Color.YELLOW.darker().darker()));
        pack();
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
}
