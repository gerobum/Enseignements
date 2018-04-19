
package facture;

import java.awt.Font;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JTable;
import modele.ModeleDeFacture;

/**
 *
 * @author yvan
 */
public class Facture extends JFrame {
    public static class Paire {
        public final String article;
        public final double prix;

        public Paire(String article, double prix) {
            this.article = article;
            this.prix = prix;
        }
        
        @Override
        public String toString() {
            return article;
        }
    }
    
    public Facture() {
        super("La facture est salée");
        
        JTable facture = new JTable(new ModeleDeFacture());
        
        Font font = new Font("Georgia", Font.PLAIN, 20);
        facture.setFont(font);
        facture.setRowHeight(getFontMetrics(font).getHeight());
       
        facture.getTableHeader().setFont(font.deriveFont(Font.BOLD));

        JComboBox comboBox = new JComboBox();
        comboBox.addItem(new Paire("Pain long", 1.56));
        comboBox.addItem(new Paire("Baguette", 0.95));
        comboBox.addItem(new Paire("Croissant", 1.56));
        comboBox.addItem(new Paire("Meringue", 2.85));
        facture.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(comboBox));
        
        getContentPane().add(facture.getTableHeader(), "North");
        getContentPane().add(facture, "Center");
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }
}
