/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package arbre_binaire;

import arbre_binaire.NoeudBinaire;
import arbre_binaire.NoeudBDRFromTN;
import java.awt.Color;
import java.util.Arrays;
import java.util.Random;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

/**
 *
 * @author maillot
 */
public class ArbreBinaire extends JFrame {

    private static void affiche(Object[] essai) {
        System.out.print("{");
        for(Object o : essai) {
            System.out.print(o);
        }
        System.out.println("}");
    }
    //Where instance variables are declared:

    private JTree tree;
    private static Random random = new Random();

    public ArbreBinaire(Integer... nombre) {

        NoeudBinaire racine = créationRacine(nombre);

        tree = new JTree(racine);
        DefaultTreeCellRenderer dtcr = new DefaultTreeCellRenderer();
        //dtcr.setLeafIcon(null);
        //dtcr.setOpenIcon(null);
        //dtcr.setClosedIcon(null);
        dtcr.setBackground(Color.BLACK);
        tree.setCellRenderer(dtcr);
        

        JScrollPane treeView = new JScrollPane(tree);

        getContentPane().add(treeView);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();


    }

    public static void main(String[] args) {
        Integer[] table;
        table = new Integer[random.nextInt(51)+10];
        for(int i = 0; i < table.length; i++) {
            table[i] = random.nextInt(100);
        }
        new ArbreBinaire(table);

    }

    private NoeudBinaire créationRacine(Integer[] t) {
        if (t.length == 0)
            return new NoeudBinaire();
        else {
            Integer[] t1 = new Integer[t.length/2];
            Integer[] t2 = new Integer[t.length-t1.length-1];
            int k = 1;
            for(int i = 0; i < t1.length; i++)
                t1[i] = t[k++];
            for(int i = 0; i < t2.length; i++)
                t2[i] = t[k++];
            
            return new NoeudBinaire(créationRacine(t1), créationRacine(t2), t[0]);            
        }
    }

}
