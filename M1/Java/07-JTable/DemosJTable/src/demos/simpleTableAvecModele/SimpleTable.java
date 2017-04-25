/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package demos.simpleTableAvecModele;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;



/**
 *
 * @author yvan
 */
public class SimpleTable /*extends AbstractTableModel*/ implements TableModel {
  private int[][] table = {{1,2,3,4},{10,20,30,40},{100,200,300,400}};

  
  @Override
  public int getRowCount() {
    return table.length;
  }
  @Override
  public int getColumnCount() {
    return table[0].length;
  }
  @Override
  public Object getValueAt(int rowIndex, int columnIndex) {
    return table[rowIndex][columnIndex];
  }
  @Override
  public String getColumnName(int column) {
    return "x "+(column+1);
  }
  @Override
  public Class<?> getColumnClass(int columnIndex) {
    return Integer.class;
  }

  @Override
  public boolean isCellEditable(int rowIndex, int columnIndex) {
    return columnIndex == 0;
  }

  @Override
  public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
    Integer v = (Integer) aValue;
    table[rowIndex][columnIndex] = v;
    
    table[rowIndex][1] = v*10;
    table[rowIndex][2] = v*100;
    
  }

  @Override
  public void addTableModelListener(TableModelListener l) {
    //throw new UnsupportedOperationException("Not supported yet.");
  }

  @Override
  public void removeTableModelListener(TableModelListener l) {
    //throw new UnsupportedOperationException("Not supported yet.");
  }
  
  

}
