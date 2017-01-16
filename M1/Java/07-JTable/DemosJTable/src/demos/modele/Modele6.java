package demos.modele;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maillot
 */
public class Modele6 extends AbstractTableModel {

    private final String[] columnNames = {"Integer", "Boolean", "String"};
    private final Class<?>[] types = {Integer.class, Boolean.class, String.class};
    private Boolean coche = false;

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 1;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        coche = (Boolean) aValue;
        fireTableRowsUpdated(row, row);
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public int getRowCount() {
        return 1;
    }

    @Override
    public int getColumnCount() {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return coche ? 1 : 0;
            case 2:
                return coche ? "Vrai" : "Faux";
            default:
                return coche;
        }
    }

}
