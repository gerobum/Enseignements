package corrections.bargraph.avec_rendu;

import corrections.bargraph.avec_rendu.rendu.ACellRenderer;
import corrections.bargraph.avec_rendu.rendu.Coche;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Font;
import javax.swing.DefaultCellEditor;
import javax.swing.JDialog;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

public class DialogTableModel extends JDialog {

    public DialogTableModel(TableModel pm, String titre) {
        this(pm, titre, false);
    }

    public DialogTableModel(TableModel pm, String titre, boolean check) {

        JTable table = new JTable(pm);
        setTitle(titre);
        Font font = new Font("Arial", Font.PLAIN, 30);
        table.setFont(font);
        table.setRowHeight(getFontMetrics(font).getHeight());
        if (check) {
            System.out.println(table.getColumnModel().getColumn(0).getCellRenderer());
        }
        table.getTableHeader().setFont(font.deriveFont(Font.BOLD));

        if (check) {
            for (int i = 0; i < table.getColumnCount(); i++) {
                table.getColumnModel().getColumn(i).setCellRenderer(new ACellRenderer());
            }
        }
        setLayout(new BorderLayout());

        getContentPane().add(table.getTableHeader(), "North");
        getContentPane().add(table, "Center");

        pack();
        setVisible(true);
        setModal(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

}
