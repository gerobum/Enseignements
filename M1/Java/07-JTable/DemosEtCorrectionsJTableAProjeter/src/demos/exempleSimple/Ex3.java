package demos.exempleSimple;

 
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.AbstractTableModel;

/**
 * JTable avec remplissage et écriture sur la sortie standard
 * @author Maillot 
 */
public class Ex3 extends JFrame {
    private final JButton alea = new JButton("Alea Jacta Est");
    private final JButton ecrire = new JButton("Ecrire");
    private final Integer[] titre = {1, 10, 100, 1000};
    private final Random random = new Random();
    private Object[][] donnee = new Object[10][4];
    
    private void remplissage() {
        // ----------- Remplissage bidon

        for(int i = 0; i < donnee.length; i++)
            for(int j = 0; j < donnee[i].length; j++) {
            
                donnee[i][j] = random.nextInt(10)*(titre[j]);
        }
        // ----------------------------        
    }

    public Ex3() {
        super("JTable avec remplissage aléatoire");
        remplissage();
        final JTable table = new JTable(donnee, titre);
        table.setPreferredScrollableViewportSize(new Dimension(500, 70));
        table.setFillsViewportHeight(true);
             
        getContentPane().add(table, "Center");
        getContentPane().add(alea, "East");
        getContentPane().add(ecrire, "West");
         
        JScrollPane scrollPane = new JScrollPane(table);

        Font font = new Font("Georgia", Font.PLAIN, 30);
        table.setFont(font);
        table.setRowHeight(getFontMetrics(font).getHeight());
        table.getTableHeader().setFont(font.deriveFont(Font.BOLD));

        getContentPane().add(scrollPane);
        
        alea.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                remplissage();
                getContentPane().repaint();
                //((AbstractTableModel)table.getModel()).fireTableDataChanged();
                
            }
        });
        
        ecrire.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for(Object[] ligne : donnee) {
                    for(Object o : ligne) {
                        System.out.print(o + " ");
                    }
                    System.out.println();
                }
                    
            }
        });
       
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public static void main(String[] args) {
        new Ex3();
    }
}
