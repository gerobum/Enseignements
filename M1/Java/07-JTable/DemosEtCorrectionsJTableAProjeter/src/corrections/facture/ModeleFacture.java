
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package corrections.facture;

import corrections.facture.article.Article;
import corrections.facture.article.Articles;
import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Yvan
 */
public class ModeleFacture extends AbstractTableModel {

    private final String[] titres;
    private final Class<?>[] types;
    private final ArrayList<Ligne> donnees;

    private class Ligne {

        Object[] ligne;

        public Ligne(Object... ligne) {
            this.ligne = new Object[6];
            for (int i = 0; i < ligne.length; ++i) {
                this.ligne[i] = ligne[i];
            }
            for (int i = ligne.length; i < this.ligne.length; ++i) {
                this.ligne[i] = null;
            }
        }
    }

    public ModeleFacture() {
        this.donnees = new ArrayList<>();
        this.types = new Class<?>[]{String.class, Double.class, Integer.class, Boolean.class, Double.class, Double.class};
        this.titres = new String[]{"Article", "Prix HT", "Quantité", "Remise 20%", "Prix TTC", "Prix Total"};
        this.donnees.add(new Ligne("Cliquez ici"));
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        return types[columnIndex];
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return column == 0 || ((column == 2 || column == 3) && row < donnees.size() - 1);
    }

    @Override
    public void setValueAt(Object aValue, int row, int column) {

        if (column == 0) {
            Article article = (Article) aValue;

            if (article != Articles.rien) {
                donnees.get(row).ligne[0] = article.toString();
                donnees.get(row).ligne[1] = article.getPrix();
                donnees.get(row).ligne[2] = 1;
                donnees.get(row).ligne[3] = false;

                if (row == donnees.size() - 1) {
                    donnees.add(new Ligne("Total"));
                }
            } else {
                if (row != getRowCount() - 1) {
                    donnees.remove(row);
                }
            }

        } else {
            if (column == 2 && ((Integer) aValue) <= 0) {
                donnees.remove(row);
            } else {
                donnees.get(row).ligne[column] = aValue;
            }
        }
        calculDuneLigne(row);
        calculDuTotalDesTotaux();

        fireTableDataChanged();
    }

    private void calculDuneLigne(int row) {
        if (row != donnees.size() - 1) {
            double prix = (double) donnees.get(row).ligne[1];
            int quantite = (int) donnees.get(row).ligne[2];
            boolean reduction = (boolean) donnees.get(row).ligne[3];
            donnees.get(row).ligne[4] = prix * 1.196 * (reduction ? 0.8 : 1.0);

            donnees.get(row).ligne[5] = ((double) donnees.get(row).ligne[4]) * quantite;
        }
    }

    private void calculDuTotalDesTotaux() {
        Double totalDesTotaux = 0.0;
        for (int i = 0; i < getRowCount() - 1; i++) {
            totalDesTotaux += (Double) getValueAt(i, 5);
        }
        donnees.get(donnees.size() - 1).ligne[5] = totalDesTotaux;
        fireTableRowsUpdated(donnees.size() - 1, donnees.size() - 1);
    }

    @Override
    public int getRowCount() {
        return donnees.size();
    }

    @Override
    public int getColumnCount() {
        return titres.length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        return donnees.get(rowIndex).ligne[columnIndex];
    }

    @Override
    public String getColumnName(int column) {
        return titres[column];
    }

}
