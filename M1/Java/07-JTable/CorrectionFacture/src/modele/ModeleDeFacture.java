/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import facture.Facture;
import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author yvan
 */
public class ModeleDeFacture extends AbstractTableModel {

    private static class Ligne {

        public Facture.Paire articlePrix;
        public Integer quantity;
        public Boolean remise;

        public Ligne(Facture.Paire articlePrix, Integer quantity, Boolean remise) {
            this.articlePrix = articlePrix;
            this.quantity = quantity;
            this.remise = remise;
        }

        public Ligne() {
            this(null, null, false);
        }
    }

    List<Ligne> data = new ArrayList<>();

    public ModeleDeFacture() {
        for (int i = 0; i < 10; ++i) {
            data.add(new Ligne());
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0:
                return "Article";
            case 1:
                return "Prix HT";
            case 2:
                return "Quantité";
            case 3:
                return "Remise 20%";
            case 4:
                return "Prix TTC";
            case 5:
                return "Prix total";
            default:
                return null;
        }
    }

    @Override
    public Class<?> getColumnClass(int column) {

        switch (column) {
            case 0:
                return Object.class;
            case 1:
                return Double.class;
            case 2:
                return Integer.class;
            case 3:
                return Boolean.class;
            case 4:
                return Double.class;
            case 5:
                return Double.class;
            default:
                return null;
        }
    }

    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 0 || columnIndex == 2 || columnIndex == 3;
    }

    @Override
    public int getRowCount() {
        return data.size();
    }

    @Override
    public int getColumnCount() {
        return 6;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return article(rowIndex);
            case 1:
                return prixHT(rowIndex);
            case 2:
                return quantité(rowIndex);
            case 3:
                return remise(rowIndex);
            case 4:
                return prixTTC(rowIndex);
            case 5:
                return prixTotal(rowIndex);
            default:
                return null;
        }
    }

    @Override
    public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                data.get(rowIndex).articlePrix = (Facture.Paire) aValue;
                break;

            case 2:
                data.get(rowIndex).quantity = (Integer) aValue;
                break;

            case 3:
                data.get(rowIndex).remise = (Boolean) aValue;
                break;
        }
        fireTableRowsUpdated(rowIndex, rowIndex);
    }

    private String article(int rowIndex) {
        try {
            return data.get(rowIndex).articlePrix.article;
        } catch (NullPointerException ex) {
            return null;
        }
    }

    private Double prixHT(int rowIndex) {
        try {
            return data.get(rowIndex).articlePrix.prix;
        } catch (NullPointerException ex) {
            return null;
        }
    }

    private Integer quantité(int rowIndex) {
        try {
            return data.get(rowIndex).quantity;
        } catch (NullPointerException ex) {
            return null;
        }
    }

    private Boolean remise(int rowIndex) {
        try {
            return data.get(rowIndex).remise;
        } catch (NullPointerException ex) {
            return null;
        }
    }

    private Double prixTTC(int rowIndex) {
        try {
            return prixHT(rowIndex) * 1.2;
        } catch (NullPointerException ex) {
            return null;
        }
    }

    private Double prixTotal(int rowIndex) {
        try {
            return prixHT(rowIndex) * quantité(rowIndex) * (remise(rowIndex) ? 0.8 : 1.0);
        } catch (NullPointerException ex) {
            return null;
        }
    }

}
