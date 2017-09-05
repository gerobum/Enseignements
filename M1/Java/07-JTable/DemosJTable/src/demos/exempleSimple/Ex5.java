package demos.exempleSimple;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;
import java.util.Vector;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import demos.modele.Modele5;

/**
 *        
 * @author Maillot
 */
public class Ex5 extends JFrame {

    private Class<?>[] types = {Integer.class, Double.class, Boolean.class, String.class};
    private String[] titles = {"Integer", "Double", "Boolean", "String"};
    private Object[][] data = new Object[10][types.length];
    
    public Ex5() {
        super("Table avec Modèle");


        Modele5 dtm = new Modele5(data, titles, types);

        JTable table = new JTable(dtm);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        getContentPane().add(scrollPane);

        JButton afficher = new JButton("Afficher données");
        afficher.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for(Object[] v : data) {
                    for(Object o : v) {
                        System.out.print(o + ", ");
                    }
                    System.out.println();
                }
            }
        });

        JButton remplir = new JButton("Remplir au hasard");
        remplir.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                Random r = new Random();
                int l = r.nextInt(10);
                data[l][0] = r.nextInt(10);
                data[l][1] = r.nextInt(100)/10.0;
                data[l][2] = r.nextBoolean();
                data[l][3] = "alea jacta est";
                getContentPane().repaint();
            }
        });

        Font font = new Font("Georgia", Font.PLAIN, 30);
        table.setFont(font);
        table.setRowHeight(getFontMetrics(font).getHeight());
        table.getTableHeader().setFont(font.deriveFont(Font.BOLD));


        getContentPane().add(afficher, "South");
        getContentPane().add(remplir, "North");


        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();


    }

    public static void main(String[] args) {
        Ex5 e = new Ex5();

    }
}
