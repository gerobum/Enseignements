/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre_binaire;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeModel;
import javax.swing.tree.TreePath;

/**
 *
 * @author maillot
 */
public class TestArbreBinaire extends JFrame {

    private JTree arbre;
    private NoeudBDRFromTN racine;
    private JButton ajout;
    private JTextField valeur;
    private JTextArea affichage;

    public TestArbreBinaire() {
        super("Ajouter une valeur entière");
        //TreeModel
        racine = new NoeudBDRFromTN();
        DefaultTreeModel model = new DefaultTreeModel(racine);
        
        DefaultTreeCellRenderer dtcr = new DefaultTreeCellRenderer();
        //dtcr.setLeafIcon(null);
        dtcr.setOpenIcon(null);
        dtcr.setClosedIcon(null);
        dtcr.setBorder(BorderFactory.createLineBorder(Color.red.darker()));
        dtcr.setAutoscrolls(true);
        dtcr.setFont(new JLabel().getFont().deriveFont(25f));

        arbre = new JTree(model);
        arbre.setCellRenderer(dtcr);
        arbre.setShowsRootHandles(true);
        valeur = new JTextField(20);
        
        affichage = new JTextArea(3, 30);
        JScrollPane vueAffichage = new JScrollPane(affichage);

        JScrollPane vueArbre = new JScrollPane(arbre);
        vueArbre.setPreferredSize(new Dimension(50, 100));
        JPanel sud = new JPanel();
        DefaultTreeCellRenderer d;
        ajout = new JButton("+");
        ajout.addActionListener(new ActionListener() {

            public void actionPerformed(ActionEvent ae) {
                try {
                    Integer v = Integer.parseInt(valeur.getText().trim());
                    racine.add(v);
                    for (int i = 0; i < arbre.getRowCount(); i++) {
                        arbre.expandPath(arbre.getPathForRow(i));
                    }
                    arbre.updateUI();
                } catch (NumberFormatException nbe) {
                    valeur.setText("");
                }
            }
        });
        valeur.addFocusListener(new FocusListener() {

            public void focusGained(FocusEvent fe) {
                JTextField t = (JTextField) fe.getSource();
                t.selectAll();
            }

            public void focusLost(FocusEvent fe) {
            }
        });

        valeur.addMouseMotionListener(new MouseMotionListener() {

            public void mouseDragged(MouseEvent me) {
            }

            public void mouseMoved(MouseEvent me) {
                JTextField t = (JTextField) me.getSource();
                t.selectAll();
            }
        });

        arbre.addTreeSelectionListener(new TreeSelectionListener() {

            public void valueChanged(TreeSelectionEvent tse) {
                System.out.println(tse.getPath());
            }
        });

        arbre.addMouseListener(new MouseListener() {

            public void mouseClicked(MouseEvent me) {
                if (me.getButton() == MouseEvent.BUTTON1) {
                    TreePath path = arbre.getPathForLocation(me.getX(), me.getY());
                    if (path == null) {
                        System.out.println("null");
                        return;
                    }
                        
                    NoeudBDRFromTN n = (NoeudBDRFromTN) path.getLastPathComponent();
                    
                    Comparable[] t = n.getValeursEnOrdre(n);
                    StringBuilder s = new StringBuilder();
                    for (int i = 0; i < t.length; i++) {
                        System.out.print(t[i] + " ");
                        s.append(t[i] + " ");
                    }
                    s.append('\n');
                    System.out.println();
                    affichage.setText(s.toString());
                }
            }

            public void mousePressed(MouseEvent me) {
            }

            public void mouseReleased(MouseEvent me) {
            }

            public void mouseEntered(MouseEvent me) {
            }

            public void mouseExited(MouseEvent me) {
            }
        });


        sud.add(ajout);
        sud.add(valeur);

        getContentPane().add(vueAffichage, "South");

        getContentPane().add(sud, "North");

        getContentPane().add(vueArbre);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    public static void main(String[] args) {
        new TestArbreBinaire();
    }
}
