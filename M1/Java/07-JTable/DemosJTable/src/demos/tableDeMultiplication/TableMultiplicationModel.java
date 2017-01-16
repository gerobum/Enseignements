package demos.tableDeMultiplication;

import javax.swing.event.TableModelListener;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author yvan
 */
public class TableMultiplicationModel extends AbstractTableModel {

    private final int nbLignes, nbColonnes;

    public TableMultiplicationModel(int l, int c) {
        nbLignes = l;
        nbColonnes = c;
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
    public String getColumnName(int column) {
        return "x" + (column + 1);
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return (rowIndex + 1) * (columnIndex + 1);
    }
}
