
package corrections.bargraph.classique.bargraph;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author yvan
 */
public class PercentageModel extends AbstractTableModel {
    private final Integer[][] donnees;
    
    public PercentageModel(Integer[][] donnees) {
        this.donnees = donnees; 
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return donnees[0].length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return 100*donnees[1][columnIndex]/donnees[0][columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Integer.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return true;
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Integer v = (Integer) aValue;
        if (v >= 0 && v <= 100) {
            donnees[1][columnIndex] = (Integer)Math.round((float) (donnees[0][columnIndex] * (v/100.0)));
            fireTableDataChanged();
        }
    }

    
    
}
