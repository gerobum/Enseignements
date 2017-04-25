/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package corrections.notes;

import java.util.LinkedList;
import java.util.Vector;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import corrections.notes.modele.ModeleConsultation;
import corrections.notes.modele.ModeleFinal;
import corrections.notes.modele.ModelePartiel;

/**
 *
 * @author yvan
 */
public class PlusieursNotes {
    private Object[] columnTypes = {1, 1.0, true, ""};
    private Vector<Vector> donnee;
    private Vector columnNames;
    private LinkedList<FrameNotes> listeFrames = new LinkedList<FrameNotes>();

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
        v.add("Nom");
        v.add("Partiel");
        v.add("Final");
        v.add("Moyenne");
        return v;
    }

    public PlusieursNotes() {
        donnee = getDataVector(5, 4);
        columnNames = getColumnNamesVector();

        DefaultTableModel tm;
        TableModelListener action = new TableModelListener() {

            @Override
            public void tableChanged(TableModelEvent e) {
                for(FrameNotes f : listeFrames)
                    f.getContentPane().repaint();
            }
        };
        
        tm = new ModelePartiel(donnee, columnNames, columnTypes);
        
        tm.addTableModelListener(action);

        listeFrames.add(new FrameNotes("Partiel", tm));
        
        tm = new ModeleFinal(donnee, columnNames, columnTypes);
        tm.addTableModelListener(action);

        listeFrames.add(new FrameNotes("Final", tm));

       
        
        tm = new ModeleConsultation(donnee, columnNames, columnTypes);
        tm.addTableModelListener(action);

        listeFrames.add(new FrameNotes("Consultation", tm));
     


    }

    public static void main(String[] args) {
        new PlusieursNotes();
    }

}
