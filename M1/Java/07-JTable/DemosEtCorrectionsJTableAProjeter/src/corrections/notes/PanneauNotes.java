package corrections.notes;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

public class PanneauNotes extends JPanel {
    // private boolean DEBUG = true;

    private JTable table;

    public PanneauNotes(int colonneAZero, DefaultTableModel modele) {
        super(new GridLayout(1, 0));

        table = new JTable(modele);
        table.setPreferredScrollableViewportSize(new Dimension(400, 200));
        table.setFillsViewportHeight(true);

        
        if (colonneAZero >= 0) {
            table.getColumnModel().getColumn(colonneAZero).setMaxWidth(-1);
        }


        //Create the scroll pane and add the table to it.
        JScrollPane scrollPane = new JScrollPane(table);

        //Add the scroll pane to this panel.
        add(scrollPane);
    }
}