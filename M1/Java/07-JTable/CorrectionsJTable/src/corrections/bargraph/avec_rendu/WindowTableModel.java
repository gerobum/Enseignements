package corrections.bargraph.avec_rendu;

import corrections.bargraph.avec_rendu.rendu.ACellRenderer;
import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.TableModel;

public class WindowTableModel extends JFrame {

    public WindowTableModel(TableModel pm, String titre) {
        this(pm, titre, false);
    }

    public WindowTableModel(TableModel pm, String titre, boolean check) {

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
        //setModal(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

    }

}
