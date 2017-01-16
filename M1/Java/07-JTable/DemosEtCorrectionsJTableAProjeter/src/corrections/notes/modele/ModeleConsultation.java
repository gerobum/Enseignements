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
public class ModeleConsultation extends DefaultTableModel {

    private Object[] columnTypes = {"", 0.0, 0.0, 0.0};
    private Vector<Vector> donnee;

    public ModeleConsultation(Vector<Vector> donnee, Vector columnNames, Object[] columnTypes) {
        this.donnee = donnee;
        
        setDataVector(donnee, columnNames);
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return columnTypes[columnIndex].getClass();
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
   

    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false;
    }
}
