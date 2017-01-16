/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corrections.bargraph.avec_rendu.main;

import corrections.bargraph.avec_rendu.BargraphModel;
import corrections.bargraph.avec_rendu.DialogTableModel;
import corrections.bargraph.avec_rendu.PercentageModel;
import corrections.bargraph.avec_rendu.SimpleModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;

/**
 *
 * @author yvan
 */
public class Main {

    private static DialogTableModel simple;
    private static DialogTableModel pourcentage;
    private static DialogTableModel bargraph;

    public static void main(String[] args) {
        Integer[][] donnees = {{1524, 510, 418}, {145, 12, 47}};
        SimpleModel sm = new SimpleModel(donnees);
        simple = new DialogTableModel(sm, "Référence");

        PercentageModel pm = new PercentageModel(donnees);

        pourcentage = new DialogTableModel(pm, "Pourcentages");
        
        BargraphModel bm = new BargraphModel(donnees);
        
        bargraph = new DialogTableModel(bm, "Barres", true);
        
        
        TableModelListener tml = new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                simple.repaint();
                pourcentage.repaint();
                bargraph.repaint();
            }
        };

        sm.addTableModelListener(tml);
        pm.addTableModelListener(tml);
        bm.addTableModelListener(tml);
    }
}
