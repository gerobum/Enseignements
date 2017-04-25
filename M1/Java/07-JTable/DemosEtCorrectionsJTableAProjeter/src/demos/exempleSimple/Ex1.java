package demos.exempleSimple;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;

/**
 * JTable toute simple
 * @author Maillot
 */
public class Ex1 extends JFrame {

    public Ex1() {
        super("Table vide");
        // Création d'une table de 10 lignes et 4 colonnes
        JTable table = new JTable(10, 4);

        getContentPane().add(table, "Center");

        JScrollPane scrollPane = new JScrollPane(table);
        getContentPane().add(scrollPane);
        // Pour remplir tout l'espace de la frame en présence d'ascenseur
        table.setFillsViewportHeight(true);
        
        // Pour placer l'en-tête en l'absence d'ascenseur
        // getContentPane().add(table.getTableHeader(), "North"); // ou South, ou East, ou West


        Font font = new Font("Georgia", Font.PLAIN, 30);
        table.setFont(font);
        table.setRowHeight(getFontMetrics(font).getHeight());
        table.getTableHeader().setFont(font.deriveFont(Font.BOLD));


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new Ex1();
    }
}
