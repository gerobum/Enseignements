/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package corrections.bargraph.avec_rendu;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author yvan
 */
public class SimpleModel extends AbstractTableModel {
    private final Object[][] donnees;
    
    public SimpleModel(Object[][] donnees) {
        this.donnees = donnees; 
    }

    @Override
    public int getRowCount() {
        return donnees.length;
    }

    @Override
    public int getColumnCount() {
        return donnees[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return donnees[rowIndex][columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return donnees[0][columnIndex].getClass();
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        donnees[rowIndex][columnIndex] = aValue;
        fireTableDataChanged();
    }
    
}
