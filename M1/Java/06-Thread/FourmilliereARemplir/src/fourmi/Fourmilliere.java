
package fourmi;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

/**
 * JFrame qui possède 
 * <ol>
 * <li>un label "quantite" au nord</li><br>
 * <li>un bouton "fourmi" au sud</li>
 * <li>une zone de saisie "combien" au sud</li>
 * <li>un panneau dans lequel s'anime des fourmis au centre</li>
 * </ol>
 * Appuyer sur le bouton "fourmi" lance le nombre de fourmis donné dans 
 * le champ de saisie "combien" ou 1 fourmi si rien n'y est indiqué ou si
 * la champ n'est pas convertible en entier.
 * Le label "quantite" indique en permanance le nombre de fourmis qui s'animent
 * dans le panneau.
 * @author maillot
 */
public class Fourmilliere extends JFrame {
    
    private JButton fourmi;
    private Terrain terrain;
    private final JLabel quantite;
    private JTextField combien;
    private final JPanel lancementDesFourmis;
    
    public Fourmilliere(String titre) {
        super(titre);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        quantite = new JLabel("0", JLabel.CENTER);
        terrain = new Terrain(quantite);
        terrain.setBackground(Color.white);
        getContentPane().add(terrain, "Center");          
        
        lancementDesFourmis = new JPanel();
        lancementDesFourmis.setLayout(new BorderLayout());
        getContentPane().add(lancementDesFourmis, "South");
        addComponentListener(new ComponentAdapter() {

        @Override
        public void componentResized(ComponentEvent e) {
                Rectangle rectangle = terrain.getBounds();
                terrain.setMaxX((int)(rectangle.getWidth()/terrain.getTailleFourmiX()));
                terrain.setMaxY((int)(rectangle.getHeight()/terrain.getTailleFourmiY()));
                pack();
            }
        });
        
        combien = new JTextField("");
        
        fourmi = new JButton("Envoyez les fourmis");
        fourmi.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent e) {
                int n;
                try {
                    n = Integer.parseInt(combien.getText());
                } catch(NumberFormatException ne) {
                    terrain.ajouterFourmi(terrain.getMaxX()/2, terrain.getMaxY()/2);
                    n = 0;
                }
                for(int i = 0; i < n; i++)
                    terrain.ajouterFourmi();
                combien.setText("");

            }
        });    
        
        combien.addActionListener(new ActionListener() {

          @Override
          public void actionPerformed(ActionEvent e) {
            fourmi.doClick();
          }
        });
            
        lancementDesFourmis.add(combien, "Center");
        lancementDesFourmis.add(fourmi, "East");
        
        getContentPane().add(quantite, "North");
        pack();
    }
}