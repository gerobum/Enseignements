/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corrections.notes_ATM;

import java.util.LinkedList;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;
import corrections.notes_ATM.modele.ModeleConsultation;
import corrections.notes_ATM.modele.ModeleFinal;
import corrections.notes_ATM.modele.ModelePartiel;

/**
 *
 * @author yvan
 */
public class PlusieursNotes {

  private Object[][] donnee = new Object[10][4];
  private String[] nom = {"Nom", "Partiel", "Final", "Moyenne"};
  private LinkedList<FrameNotes> listeFrames = new LinkedList<>();

  public PlusieursNotes() {
    AbstractTableModel tm;
    TableModelListener action = new TableModelListener() {
      @Override
      public void tableChanged(TableModelEvent e) {
        for (FrameNotes f : listeFrames) {
          f.getContentPane().repaint();
        }
      }
    };

    tm = new ModelePartiel(donnee, nom);

    tm.addTableModelListener(action);

    listeFrames.add(new FrameNotes("Partiel", tm));

    tm = new ModeleFinal(donnee, nom);
    tm.addTableModelListener(action);

    listeFrames.add(new FrameNotes("Final", tm));



    tm = new ModeleConsultation(donnee, nom);
    tm.addTableModelListener(action);

    listeFrames.add(new FrameNotes("Consultation", tm));
  }

  public static void main(String[] args) {
    new PlusieursNotes();
  }
}
