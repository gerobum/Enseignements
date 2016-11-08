/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gridLayoutDansBorderLayout;


import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author yvan
 */
public class JPanelGrid extends JPanel {
     private JLabel a1, a2, a3, a4, a5, a6;
    private GridLayout gridLayout = new GridLayout(2,3);

    private void definirLesElements() {
        a1.setOpaque(true);
        a1.setBackground(Color.CYAN);
        a2.setOpaque(true);
        a2.setBackground(Color.CYAN.darker());
        a3.setOpaque(true);
        a3.setBackground(Color.CYAN.darker().darker());
        a4.setOpaque(true);
        a4.setBackground(Color.CYAN.darker().darker().darker());
        a5.setOpaque(true);
        a5.setBackground(Color.CYAN.darker().darker().darker().darker());
        a6.setOpaque(true);
        a6.setBackground(Color.CYAN.darker().darker().darker().darker().darker());
    }

    public JPanelGrid() {
        a1 = new JLabel("", JLabel.CENTER);
        a2 = new JLabel("", JLabel.CENTER);
        a3 = new JLabel("", JLabel.CENTER);
        a4 = new JLabel("", JLabel.CENTER);
        a5 = new JLabel("", JLabel.CENTER);
        a6 = new JLabel("", JLabel.CENTER);

        definirLesElements();

        setLayout(gridLayout);

        placerLesElements();


    }

  

    private void placerLesElements() {
        add(a1);
        add(a2);
        add(a3);
        add(a4);
        add(a5);
        add(a6);
        a1.setPreferredSize(new Dimension(100, 200));
        a2.setPreferredSize(new Dimension(45, 50));
        a5.setPreferredSize(new Dimension(250, 250));
        a3.setPreferredSize(new Dimension(50, 10));
        a4.setPreferredSize(new Dimension(75, 25));
        gridLayout.setHgap(10);
        gridLayout.setVgap(10);

    }
 
}
