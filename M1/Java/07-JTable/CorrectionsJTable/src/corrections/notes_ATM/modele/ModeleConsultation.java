/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corrections.notes_ATM.modele;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maillot
 */
public class ModeleConsultation extends AbstractTableModel {

  private Class[] type = {String.class, Double.class, Double.class, Double.class};
  private Object[][] donnee;
  private String[] nom;

  public ModeleConsultation(Object[][] donnee, String[] nom) {
    this.donnee = donnee;
    this.nom = nom;
  }

  @Override
  public int getRowCount() {
    return donnee.length;
  }

  @Override
  public int getColumnCount() {
    return donnee[0].length;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    return donnee[rowIndex][columnIndex];
  }

  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return type[columnIndex];
  }

  @Override
  public String getColumnName(int column) {
    return nom[column];
  }
  
  
}
