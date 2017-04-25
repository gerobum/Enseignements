/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corrections.notes.modele;

import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Maillot
 */
public class ModelePartiel extends DefaultTableModel {

    private Object[] columnTypes = {"", 0.0, 0.0, 0.0};
    private Vector<Vector> donnee;

    //public Modele5(Object[][] donnee, String[] columnNames, Object[] columnTypes) {
    public ModelePartiel(Vector<Vector> donnee, Vector columnNames, Object[] columnTypes) {
        this.donnee = donnee;
        setDataVector(donnee, columnNames);

    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnTypes[columnIndex].getClass();
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        super.setValueAt(aValue, row, column);
        //donnee.get(row).set(column, aValue);

        if (column == 1) {
            if (getValueAt(row, 2) != null) {
                Double notefinal =(Double) getValueAt(row, 2);
                Double partiel =  (Double) aValue;
                donnee.get(row).set(3, (notefinal+partiel)/2);
            } else {
                donnee.get(row).set(3, aValue);
            }
        }

    }

    @Override
    public boolean isCellEditable(int row, int column) {
        if (column == 2 || column == 3) {
            return false;
        } else {
            return true;
        }
    }
}
