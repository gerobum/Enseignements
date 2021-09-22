package corrections.bargraph.classique.bargraph;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

public class WindowTableModel extends JFrame {

    public WindowTableModel(AbstractTableModel pm, String titre) {
        this(pm, titre, false);
    }

    public WindowTableModel(AbstractTableModel pm, String titre, boolean check) {

        JTable table = new JTable(pm);
        
        setTitle(titre);

        setLayout(new BorderLayout());

        Font font = new Font("Georgia", Font.BOLD, 30);
        table.setFont(font);
        table.setRowHeight(getFontMetrics(font).getHeight());
        
        table.getTableHeader().setFont(font);

        getContentPane().add(table.getTableHeader(), "North");
        getContentPane().add(table, "Center");
        
        
        if (check) {
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(new ACellRenderer());
            }
        }

        pack();
        setVisible(true);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

}
