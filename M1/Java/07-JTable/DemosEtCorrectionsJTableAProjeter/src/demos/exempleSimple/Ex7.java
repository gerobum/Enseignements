package demos.exempleSimple;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import demos.modele.Modele7;
import demos.modele.Modele6;

/**
 *
 * @author Maillot
 */
public class Ex7 extends JFrame {
    public Ex7() {
        super("Table avec Modèle");
        Modele7 dtm = new Modele7();
       
        JTable table = new JTable(dtm);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        getContentPane().add(scrollPane);


        Font font = new Font("Georgia", Font.PLAIN, 30);
        table.setFont(font);
        table.setRowHeight(getFontMetrics(font).getHeight());
       
        table.getTableHeader().setFont(font.deriveFont(Font.BOLD));

        JComboBox comboBox = new JComboBox();
        comboBox.addItem("Pommes");
        comboBox.addItem("Poires");
        comboBox.addItem("Fraises");
        comboBox.addItem("Cerise");
        comboBox.addItem("Orange");
        table.getColumnModel().getColumn(4).setCellEditor(new DefaultCellEditor(comboBox));


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new Ex7();
    }
}
