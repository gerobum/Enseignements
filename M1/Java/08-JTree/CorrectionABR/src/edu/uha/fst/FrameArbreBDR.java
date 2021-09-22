/*
 * Creative commons CC BY-NC-SA 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 *     Share - You can copy and redistribute the material in any medium or format
 * 
 *     Adapt - You can remix, transform, and build upon the material 
 * 
 * Under the following terms :
 * 
 *     Attribution - You must give appropriate credit, provide a link to the license, 
 *     and indicate if changes were made. You may do so in any reasonable manner, 
 *     but not in any way that suggests the licensor endorses you or your use. 
 * 
 *     NonCommercial — You may not use the material for commercial purposes. 
 * 
 *     ShareAlike — If you remix, transform, or build upon the material, 
 *     you must distribute your contributions under the same license as the original. 
 * 
 * Notices:    You do not have to comply with the license for elements of 
 *             the material in the public domain or where your use is permitted 
 *             by an applicable exception or limitation. 
 * 
 * No warranties are given. The license may not give you all of the permissions 
 * necessary for your intended use. For example, other rights such as publicity, 
 * privacy, or moral rights may limit how you use the material. 
 * 
 * See <https://creativecommons.org/licenses/by-nc-sa/4.0/>.
 */
package edu.uha.fst;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.*;
import java.util.List;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class FrameArbreBDR extends JFrame {

    private JTree arbre;
    private Noeud<Integer> racine;
    private JButton ajout;
    private JTextField valeur;
    private JTextArea affichage;

    public FrameArbreBDR() {
        super("Ajouter une valeur entière");
        //TreeModel
        racine = null;
        ModelArbreBDR<Integer> model = new ModelArbreBDR<Integer>(racine);
        
        DefaultTreeCellRenderer dtcr = new DefaultTreeCellRenderer();
        dtcr.setLeafIcon(null);
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
        
        ajout = new JButton("+");
        ActionListener action = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Integer v = Integer.parseInt(valeur.getText().trim());
                    ModelArbreBDR model = (ModelArbreBDR) arbre.getModel();
                    model.ajouter(v);
                    for (int i = 0; i < arbre.getRowCount(); i++) {
                        arbre.expandPath(arbre.getPathForRow(i));
                    }
                    valeur.selectAll();
                    //arbre.updateUI();
                } catch (NumberFormatException nbe) {
                    valeur.setText("");
                }
            }
        };
        ajout.addActionListener(action);
        
        valeur.addActionListener(action);
        
        valeur.addFocusListener(new FocusListener() {

            @Override
            public void focusGained(FocusEvent fe) {
                JTextField t = (JTextField) fe.getSource();
                t.selectAll();
            }

            @Override
            public void focusLost(FocusEvent fe) {
            }
        });

        valeur.addMouseMotionListener(new MouseMotionListener() {

            @Override
            public void mouseDragged(MouseEvent me) {
            }

            @Override
            public void mouseMoved(MouseEvent me) {
                JTextField t = (JTextField) me.getSource();
                t.selectAll();
            }
        });

        arbre.addTreeSelectionListener(new TreeSelectionListener() {

            @Override
            public void valueChanged(TreeSelectionEvent tse) {
                System.out.println(tse.getPath());
            }
        });

        arbre.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getButton() == MouseEvent.BUTTON1) {
                    TreePath path = arbre.getPathForLocation(me.getX(), me.getY());
                    if (path == null) {
                        System.out.println("null");
                        return;
                    }
                        
                    Noeud<Integer> n = (Noeud<Integer>) path.getLastPathComponent();
                    
                    ModelArbreBDR model = (ModelArbreBDR) arbre.getModel();
                    
                    List<Integer> l = model.getValeursEnOrdre(n);
                    StringBuilder s = new StringBuilder();
                    for (Integer i : l) {
                        System.out.print(i + " ");
                        s.append(i).append(" ");
                    }
                    s.append('\n');
                    System.out.println();
                    affichage.setText(s.toString());
                }
            }

            @Override
            public void mousePressed(MouseEvent me) {
            }

            @Override
            public void mouseReleased(MouseEvent me) {
            }

            @Override
            public void mouseEntered(MouseEvent me) {
            }

            @Override
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

}
