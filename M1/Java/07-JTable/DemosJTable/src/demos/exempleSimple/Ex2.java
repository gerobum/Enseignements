package demos.exempleSimple;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * JTable avec des titres choisis
 * @author Maillot
 */
public class Ex2 extends JFrame {

    public Ex2() {
        super("Table IDBS");
        String[] columnNames = {"Integer", "Double", "Boolean", "String"};
        Object[][] rowData = new Object[2][4];
        rowData[0][0] = 1;
        rowData[0][1] = 1.0;
        rowData[0][2] = true;
        rowData[0][3] = "Un";

        JTable table = new JTable(rowData, columnNames);

        //JScrollPane scrollPane = new JScrollPane(table);
        //getContentPane().add(scrollPane);
        getContentPane().add(table);
        getContentPane().add(table.getTableHeader(), "South");
        
        table.setFillsViewportHeight(true);

        Font font = new Font("Georgia", Font.PLAIN, 30);
        table.setFont(font);
        table.setRowHeight(getFontMetrics(font).getHeight());
        table.getTableHeader().setFont(font.deriveFont(Font.BOLD));
        

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new Ex2();
    }
}
