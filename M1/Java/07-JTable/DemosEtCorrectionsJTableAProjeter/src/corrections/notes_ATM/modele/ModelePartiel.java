/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corrections.notes_ATM.modele;

import java.util.Vector;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maillot
 */
public class ModelePartiel extends AbstractTableModel {

  private Class[] type = {String.class, Double.class, Double.class, Double.class};
  private Object[][] donnee;
  private String[] nom;

  public ModelePartiel(Object[][] donnee, String[] nom) {
    this.donnee = donnee;
    this.nom = nom;
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return type[columnIndex];
  }

  @Override
  public void setValueAt(Object aValue, int row, int column) {
    donnee[row][column] = aValue;

    if (column == 1) {
      if (aValue != null) {
        try {
          Double notefinal = (Double) donnee[row][2];
          Double partiel = (Double) donnee[row][1];
          donnee[row][3] = (notefinal + partiel) / 2;
        } catch (NullPointerException npe) {
          donnee[row][3] = aValue;
        }
      }
    }
    fireTableRowsUpdated(row, row);
  }

  @Override
  public boolean isCellEditable(int row, int column) {
    return column == 0 || column == 1;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    return donnee[rowIndex][columnIndex];
  }

  @Override
  public int getRowCount() {
    return donnee.length;
  }

  @Override
  public int getColumnCount() {
    return donnee[0].length;
  }
}
