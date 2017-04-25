package demos.tableDeMultiplication;

import java.awt.Font;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 * JTable avec le modèle par défaut
 *
 * @author Maillot
 */
public class Table extends JFrame {

    private JTable table;

    private Table(int l, int c) {

        table = new JTable(new TableMultiplicationModel(l,c));


        Font font = new Font("Georgia", Font.PLAIN, 30);
        table.setFont(font);
        table.setRowHeight(getFontMetrics(font).getHeight());
        table.getTableHeader().setFont(font.deriveFont(Font.BOLD));


        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        getContentPane().add(scrollPane);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }


    public static void main(String[] args) {
      new Table(12, 15);
    }
}
