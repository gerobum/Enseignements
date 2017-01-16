package corrections.bargraph.classique.bargraph;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author yvan
 */
public class BargraphModel extends AbstractTableModel {

    private final Integer[][] donnees;

    public BargraphModel(Integer[][] donnees) {
        this.donnees = donnees;
    }

    @Override
    public int getRowCount() {
        return 20;
    }

    @Override
    public int getColumnCount() {
        return donnees[0].length;
    }

    @Override
    public Boolean getValueAt(int rowIndex, int columnIndex) {

        return rowIndex * 5 < 100 * donnees[1][columnIndex] / donnees[0][columnIndex];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return Boolean.class;
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        if (getValueAt(rowIndex, columnIndex)) {
            return rowIndex == 19 || !getValueAt(rowIndex + 1, columnIndex);
        } else {
            return rowIndex == 0 || getValueAt(rowIndex - 1, columnIndex);
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        Boolean v = (Boolean) aValue;
        if (v) {
            donnees[1][columnIndex] = (Integer) Math.round((float) (donnees[0][columnIndex] * ((rowIndex + 1) * 5 / 100.0)));
        } else {

            donnees[1][columnIndex] = (Integer) Math.round((float) (donnees[0][columnIndex] * ((rowIndex) * 5 / 100.0)));

        }
        fireTableDataChanged();
    }

}
