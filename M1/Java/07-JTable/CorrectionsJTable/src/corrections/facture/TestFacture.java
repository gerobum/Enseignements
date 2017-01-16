package corrections.facture;



import corrections.facture.article.Article;
import corrections.facture.article.Articles;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultCellEditor;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;


public class TestFacture extends JPanel {
    // private boolean DEBUG = true;
    private JTable table;

    public TestFacture() throws IllegalArgumentException {
        super(new GridLayout(1,0));

        ModeleFacture modele = new ModeleFacture();
        table = new JTable(modele);
        //table = new JTable(10, 5);
        table.setPreferredScrollableViewportSize(new Dimension(1800, 300));
        table.setFillsViewportHeight(true);                        
      
        JComboBox comboBox = new JComboBox();
        
        comboBox.addItem(Articles.baguette);
        comboBox.addItem(Articles.croissant);
        comboBox.addItem(Articles.meringue);
        comboBox.addItem(Articles.painLong);
        comboBox.addItem(Articles.petitPain);
        comboBox.addItem(Articles.rien);

        Font font = new Font("Georgia", Font.BOLD, 30);
        table.setFont(font);
        table.setRowHeight(getFontMetrics(font).getHeight());
        table.getTableHeader().setFont(font);
        
        table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(comboBox));

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
    }



}