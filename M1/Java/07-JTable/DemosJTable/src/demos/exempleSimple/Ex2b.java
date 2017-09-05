package demos.exempleSimple;

import java.awt.Dimension;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 *
 * @author Maillot
 */
public class Ex2b extends JFrame {

  public Ex2b() {
    super("Table IDBS");
    String[] columnNames = {"Integer", "Double", "Boolean", "String"};

    Object[][] rowData = new Object[2][4];
    rowData[0][0] = 1;
    rowData[0][1] = 1.0;
    rowData[0][2] = true;
    rowData[0][3] = "Un";
    rowData[1][0] = 2;
    rowData[1][1] = 2.0;
    rowData[1][2] = false;
    rowData[1][3] = "Deux";

    
    String[] titles = {"Integer", "Double", "Boolean", "String"};

    JTable table = new JTable(rowData, titles);


    JScrollPane scrollPane = new JScrollPane(table);
    //table.setFillsViewportHeight(true);
    getContentPane().add(scrollPane);
    table.setPreferredScrollableViewportSize(new Dimension(300, 170));


    setDefaultCloseOperation(EXIT_ON_CLOSE);
    setVisible(true);
    pack();
  }

  public static void main(String[] args) {
    new Ex2b();
  }
}
