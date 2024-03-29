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

    private Object[] columnTypes = {1, 1.0, true, ""};
    private Vector<Vector> donnee;
    private Vector columnNames;

    private Vector getDataVector(int l, int c) {
        Vector v = new Vector();
        v.setSize(10);
        for(int i = 0; i < v.size(); i++) {
            Vector x = new Vector();
            x.setSize(4);
            v.set(i, x);
        }
        return  v;
    }

    private Vector getColumnNamesVector() {
        Vector v = new Vector<Vector>();
        v.add("Integer");
        v.add("Double");
        v.add("Boolean");
        v.add("String");
        return v;
    }

    public Ex5() {
        super("Table avec Modèle");
        donnee = getDataVector(10, 4);
        columnNames = getColumnNamesVector();

        Modele5 dtm = new Modele5(donnee, columnNames, columnTypes);

        JTable table = new JTable(dtm);

        JScrollPane scrollPane = new JScrollPane(table);
        table.setFillsViewportHeight(true);
        getContentPane().add(scrollPane);

        JButton afficher = new JButton("Afficher données");
        afficher.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                for(Vector v : donnee) {
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
                donnee.get(l).set(0, r.nextInt(10));
                donnee.get(l).set(1, r.nextInt(100)/10.0);
                donnee.get(l).set(2, r.nextBoolean());
                donnee.get(l).set(3, "alea jacta est");
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
