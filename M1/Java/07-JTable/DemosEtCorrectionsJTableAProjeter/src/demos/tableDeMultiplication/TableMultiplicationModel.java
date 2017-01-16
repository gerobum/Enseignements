/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demos.tableDeMultiplication;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;



/**
 *
 * @author yvan
 */
public class TableMultiplicationModel extends AbstractTableModel {
  private int nbLignes, nbColonnes;

  public TableMultiplicationModel(int l, int c) {
    nbLignes = l; nbColonnes = c;
  }

  @Override
  public int getRowCount() {
    return nbLignes;
  }

  @Override
  public int getColumnCount() {
    return nbColonnes;
  }

  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    return (rowIndex+1)*(columnIndex+1);
  }
 
}
