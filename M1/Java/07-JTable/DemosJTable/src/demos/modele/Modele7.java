package demos.modele;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maillot
 */
public class Modele7 extends AbstractTableModel {
    private final String[] columnNames = {"Integer", "Double", "Boolean", "String", "Fruit"};
    private final Class<?>[] types = {Integer.class, Double.class, Boolean.class, String.class, Object.class};
    private final Object data[][];
    public Modele7() {
        data = new Object[10][5];
        data[0][0] = 1;
        data[0][1] = 1.0;
        data[0][2] = true;
        data[0][3] = "Un";
        data[0][4] = null;
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 1 || column == 4;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return data[rowIndex][columnIndex];
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        data[rowIndex][columnIndex] = aValue;
    } 
}
