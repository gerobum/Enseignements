/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre_binaire_corrige;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.*;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import javax.swing.event.TreeSelectionEvent;
import javax.swing.event.TreeSelectionListener;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.TreePath;

/**
 *
 * @author maillot
 */
public class FrameArbreBDR extends JFrame {

    private JTree arbre;
    private JTextField valeur;
    private JLabel affichage;
    private Font fonte;

    public FrameArbreBDR() {
        super("Ajouter une valeur entière");
        NoeudBinaire<Integer> racine = lireRacine("tmp");

        ModeleArbreBDR<Integer> model = new ModeleArbreBDR<Integer>(racine);
        
        affichage = new JLabel(" ", JLabel.LEFT);
        fonte = affichage.getFont().deriveFont(25f);
        affichage.setFont(fonte);
        
        
        DefaultTreeCellRenderer dtcr = new DefaultTreeCellRenderer();
        dtcr.setLeafIcon(null);
        dtcr.setOpenIcon(null);
        dtcr.setClosedIcon(null);
        
        dtcr.setAutoscrolls(true);
        dtcr.setFont(fonte);

        arbre = new JTree(model);
        arbre.setCellRenderer(dtcr);
        arbre.setShowsRootHandles(true);
        
        déployerToutesLesBranches();
        
        valeur = new JTextField(2);
        valeur.setFont(fonte);


        JScrollPane vueAffichage = new JScrollPane(affichage);

        JScrollPane vueArbre = new JScrollPane(arbre);
        vueArbre.setPreferredSize(new Dimension(50, 100));
        JPanel nord = new JPanel();

        ActionListener action = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent ae) {
                try {
                    Integer v = Integer.parseInt(valeur.getText().trim());
                    ModeleArbreBDR model = (ModeleArbreBDR) arbre.getModel();
                    model.ajouter(v);
                    for (int i = 0; i < arbre.getRowCount(); i++) {
                        arbre.expandPath(arbre.getPathForRow(i));
                    }
                    valeur.selectAll();
                } catch (NumberFormatException nbe) {
                    valeur.setText("");
                }
            }
        };

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

        arbre.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent me) {
                if (me.getButton() == MouseEvent.BUTTON1) {
                    TreePath path = arbre.getPathForLocation(me.getX(), me.getY());
                    if (path == null) {
                        System.out.println("null");
                        return;
                    }

                    NoeudBinaire<Integer> n = (NoeudBinaire<Integer>) path.getLastPathComponent();

                    ModeleArbreBDR model = (ModeleArbreBDR) arbre.getModel();

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
        });

        this.addWindowListener(new WindowAdapter() {
            public void windowClosing(WindowEvent we) {
                System.out.println("Fermé");
                ObjectOutputStream oout = null;
                try {
                    oout = new ObjectOutputStream(new FileOutputStream("tmp"));
                    oout.writeObject(arbre.getModel().getRoot());
                } catch (FileNotFoundException ex) {
                    System.out.println(ex);
                } catch (IOException ex) {
                    System.out.println(ex);
                } finally {
                    try {
                        oout.close();
                    } catch (IOException ex) {
                        Logger.getLogger(FrameArbreBDR.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }

        });


        //sud.add(ajout);
        JLabel texte = new JLabel("Un entier : ", JLabel.RIGHT);
        texte.setFont(fonte);
        nord.add(texte);
        nord.add(valeur);

        getContentPane().add(vueAffichage, "South");

        getContentPane().add(nord, "North");

        getContentPane().add(vueArbre);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
    }

    private NoeudBinaire<Integer> lireRacine(String nom) {
        ObjectInputStream in = null;
        NoeudBinaire<Integer> racine = null;
        try {
            in = new ObjectInputStream(new FileInputStream(nom));
            racine = (NoeudBinaire<Integer>) in.readObject();
        } catch (ClassNotFoundException ex) {
        } catch (FileNotFoundException ex) {
        } catch (IOException ex) {
        } finally {
            try {
                in.close();
            } catch (IOException ex) {
            } finally {
                return racine;
            }
        }
    }

    private void déployerToutesLesBranches() {
        for(int i = 0; i < arbre.getRowCount(); i++) {
            arbre.expandRow(i);
        }
    }
}
