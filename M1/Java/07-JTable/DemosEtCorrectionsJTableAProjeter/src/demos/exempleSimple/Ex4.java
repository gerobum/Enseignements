package demos.exempleSimple;

import java.awt.Font;
import java.util.Vector;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * JTable avec le modèle par défaut
 *
 * @author Maillot
 */
public class Ex4 extends JFrame {

    private JTable table;
    //private Object[][] donnée;
    private final Vector donnée;
    private final Vector<String> columnNames;


    public Ex4(Vector donnée, Vector columnNames) {
        super("Table avec Modèle");
        this.donnée = donnée;
        this.columnNames = columnNames;

        init();
    }
    
    private void init() {


        DefaultTableModel dtm = new DefaultTableModel() {

            @Override
            public void setValueAt(Object aValue, int row, int column) {
                super.setValueAt(aValue, row, column);
                getContentPane().repaint();
            }
            
        };
        
        

        dtm.setDataVector(donnée, columnNames);


        table = new JTable(dtm);


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

    /*
     * public Ex4() { this(new Object[][] {{1, 1.0, true, "Un"}});
    }
     */
    public Vector getDonnee() {
        return donnée;
    }

    public static void main(String[] args) {

        Vector donnée = new Vector();
        donnée.setSize(1);
        
        Vector<String> columnNames;


        columnNames = new Vector<String>();
        columnNames.add("Integer");
        columnNames.add("Double");
        columnNames.add("Boolean");
        columnNames.add("String");

        new Ex4(donnée, columnNames);
    }
}
