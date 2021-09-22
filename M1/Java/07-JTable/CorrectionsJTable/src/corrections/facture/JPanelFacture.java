package corrections.facture;



import corrections.facture.article.Article;
import corrections.facture.article.Articles;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.DefaultCellEditor;
import javax.swing.JComboBox;


public class JPanelFacture extends JPanel {

    private JTable table;

    public JPanelFacture(Article... articles) throws IllegalArgumentException {
        super(new GridLayout(1,0));

        ModeleFacture modele = new ModeleFacture();
        
        table = new JTable(modele);
        
        table.setPreferredScrollableViewportSize(new Dimension(1800, 300));
        table.setFillsViewportHeight(true);                        
      
        JComboBox<Article> comboBox = new JComboBox<>();
        
        for (Article article : articles) {
            comboBox.addItem(article);
        }

        Font font = new Font("Georgia", Font.BOLD, 30);
        table.setFont(font);
        table.setRowHeight(getFontMetrics(font).getHeight());
        table.getTableHeader().setFont(font);
        
        table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(comboBox));

        JScrollPane scrollPane = new JScrollPane(table);

        add(scrollPane);
    }



}