/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author maillot
 */

// #### 5/5 (Pour la frame)
// #### 2/2 (Pour la sérialisation)
public class FrameModèleBDR extends JFrame {

    private JTextField entier = new JTextField(3);
    private JTree arbre;
    private JLabel valeurs = new JLabel(" ", JLabel.LEFT);

    public FrameModèleBDR() {
        super("Ajouter une valeur");
        miseEnPage();
        miseEnPlaceDeEcouteurs();
    }

    private void miseEnPage() {
        JPanel nord = new JPanel();
        nord.add(new JLabel("Un entier : ", JLabel.RIGHT));
        nord.add(entier);
        getContentPane().add(nord, "North");
        

        try {
            ObjectInputStream in = new ObjectInputStream(new FileInputStream("tmp"));
            NoeudBinaire<Integer> noeud = (NoeudBinaire<Integer>) in.readObject();
            arbre = new JTree(new ModèleArbreBDR<Integer>(noeud));
        } catch (ClassNotFoundException ex) {
            arbre = new JTree(new ModèleArbreBDR<Integer>(NoeudBinaire.vide));
        } catch (IOException ex) {
            arbre = new JTree(new ModèleArbreBDR<Integer>(NoeudBinaire.vide));
        }
        
        DefaultTreeCellRenderer dtcr = new DefaultTreeCellRenderer();
        dtcr.setLeafIcon(null);
        dtcr.setOpenIcon(null);
        dtcr.setClosedIcon(null);
        arbre.setCellRenderer(dtcr);
        
        
        toutDeployer();


        getContentPane().add(new JScrollPane(arbre), "Center");
        getContentPane().add(valeurs, "South");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    public static void main(String[] args) {
        new FrameModèleBDR();
    }

    private void miseEnPlaceDeEcouteurs() {
        entier.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Integer v = Integer.parseInt(entier.getText());
                    ModèleArbreBDR<Integer> modèle;
                    modèle = (ModèleArbreBDR<Integer>) arbre.getModel();
                    modèle.ajouter(v);
                    toutDeployer();
                } catch (NumberFormatException nfe) {
                }
            }
        });
        arbre.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                ModèleArbreBDR<Integer> modèle;
                modèle = (ModèleArbreBDR<Integer>) arbre.getModel();
                NoeudBinaire<Integer> noeud = (NoeudBinaire<Integer>) tse.getPath().getLastPathComponent();
                List<Integer> liste = modèle.getValeursEnOrdre(noeud);
                String message = "";
                for (Integer i : liste) {
                    message += i + " ";
                }
                valeurs.setText(message);
            }
        });

        addWindowListener(new WindowListener() {

            @Override
            public void windowOpened(WindowEvent we) {
            }

            @Override
            public void windowClosing(WindowEvent we) {
                try {

                    ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("tmp"));
                    out.writeObject(arbre.getModel().getRoot());

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(FrameModèleBDR.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(FrameModèleBDR.class.getName()).log(Level.SEVERE, null, ex);
                }

            }

            @Override
            public void windowClosed(WindowEvent we) {
            }

            @Override
            public void windowIconified(WindowEvent we) {
            }

            @Override
            public void windowDeiconified(WindowEvent we) {
            }

            @Override
            public void windowActivated(WindowEvent we) {
            }

            @Override
            public void windowDeactivated(WindowEvent we) {
            }
        });
    }

    private void toutDeployer() {
        for(int i = 0; i < arbre.getRowCount(); i++) {
            arbre.expandRow(i);
        }
    }
}
