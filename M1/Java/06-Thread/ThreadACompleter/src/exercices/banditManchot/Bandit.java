/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package exercices.banditManchot;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Exercice à faire.
 * Quand on tire sur la poignée, les chiffres doivent "tourner" indépendemment
 * les uns des autres pendant des durées aléatoires différentes.
 * La poignée reste baissée tant que les chiffres tournent. Elle se relève 
 * aussitot que les chiffres s'arrêtent de tourner, et on peut rejouer.
 * @author yvan
 */
public class Bandit extends JFrame {

    private static final Random random = new Random();
    private final JLabel n1 = new JLabel("0", JLabel.CENTER);
    private final JLabel n2 = new JLabel("0", JLabel.CENTER);
    private final JLabel n3 = new JLabel("0", JLabel.CENTER);
    private final JButton lance = new JButton(new ImageIcon(Bandit.class.getResource("poignee.png")));

    public Bandit() {
        super("Bandit manchot");
        JPanel centre = new JPanel(new GridLayout(3, 0, 50, 50));
        centre.setBackground(new Color(200, 50, 0));
        Font fonte = n1.getFont().deriveFont(30.0f);
        n1.setFont(fonte);
        n1.setOpaque(true);
        n1.setBackground(Color.white);
        n2.setFont(fonte);
        n2.setOpaque(true);
        n2.setBackground(Color.white);
        n3.setFont(fonte);
        n3.setOpaque(true);
        n3.setBackground(Color.white);
        centre.add(new JLabel(" "));
        centre.add(new JLabel(" "));
        centre.add(new JLabel(" "));
        centre.add(n1);
        centre.add(n2);
        centre.add(n3);
        centre.add(new JLabel(" "));
        centre.add(new JLabel(" "));
        centre.add(new JLabel(" "));
        getContentPane().add(centre, "Center");
        getContentPane().add(lance, "East");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(500, 300));
        setVisible(true);
        pack();

        lance.setPressedIcon(new ImageIcon(Bandit.class.getResource("poigneeBaissee.png")));
        lance.setDisabledIcon(new ImageIcon(Bandit.class.getResource("poigneeBaissee.png")));
    }


    public static void main(String[] args) {
        new Bandit();
    }
}
