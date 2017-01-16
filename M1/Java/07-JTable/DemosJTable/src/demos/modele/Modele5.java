package demos.modele;

import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Maillot
 */
public class Modele5 extends AbstractTableModel {

    private final Object[][] data;
    private final String[] titles;
    private final Class<?>[] types;

    public Modele5(Object[][] data, String[] titles, Class<?>[] types) {
        this.data = data;
        this.titles = titles;
        this.types = types;
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return types[columnIndex].getClass();
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {
        System.out.println("set " + row + ", " + column + " : " + aValue);
    }

    @Override
    public Object getValueAt(int row, int column) {
        return data[row][column];
    }

    @Override
    public int getRowCount() {
        return data.length;
    }

    @Override
    public int getColumnCount() {
        return titles.length;
    }

}
