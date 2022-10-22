/*
 * Creative commons CC BY-NC-SA 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 *     Share - You can copy and redistribute the material in any medium or format
 * 
 *     Adapt - You can remix, transform, and build upon the material 
 * 
 * Under the following terms :
 * 
 *     Attribution - You must give appropriate credit, provide a link to the license, 
 *     and indicate if changes were made. You may do so in any reasonable manner, 
 *     but not in any way that suggests the licensor endorses you or your use. 
 * 
 *     NonCommercial — You may not use the material for commercial purposes. 
 * 
 *     ShareAlike — If you remix, transform, or build upon the material, 
 *     you must distribute your contributions under the same license as the original. 
 * 
 * Notices:    You do not have to comply with the license for elements of 
 *             the material in the public domain or where your use is permitted 
 *             by an applicable exception or limitation. 
 * 
 * No warranties are given. The license may not give you all of the permissions 
 * necessary for your intended use. For example, other rights such as publicity, 
 * privacy, or moral rights may limit how you use the material. 
 * 
 * See <https://creativecommons.org/licenses/by-nc-sa/4.0/>.
 */
package edu.uha.miage.urlmapping.models;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class FilterModel {

    private String nom;
    private String ageMini;
    private String ageMaxi;

    public FilterModel() {
        nom = "*";
        ageMini = "0";
        ageMaxi = "99";
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = (nom == null || nom.trim().isEmpty()) ? "*" : nom;
    }

    public String getAgeMini() {
        return ageMini;
    }

    public void setAgeMini(String ageMini) {
        try {
            this.ageMini = Integer.parseInt(ageMini) + "";
        } catch (NumberFormatException ex) {
            this.ageMini = "0";
        }
    }

    public String getAgeMaxi() {
        return ageMaxi;
    }

    public void setAgeMaxi(String ageMaxi) {
        try {
            this.ageMaxi = Integer.parseInt(ageMaxi) + "";
        } catch (NumberFormatException ex) {
            this.ageMaxi = "99";
        }
    }

    public class Filter {

        private String patternName;
        private int ageMini;
        private int ageMaxi;

        public Filter(String patternName, String ageMiniAsString, String ageMaxiAsString) {
            this.patternName = (patternName == null || patternName.trim().isEmpty()) ? "%" : patternName.replaceAll("\\*", "%");
            try {
                ageMini = Integer.parseInt(ageMiniAsString);
            } catch (NumberFormatException ex) {
                ageMini = 0;
            }
            try {
                ageMaxi = Integer.parseInt(ageMaxiAsString);
            } catch (NumberFormatException ex) {
                ageMaxi = 99;
            }
        }

        public Filter() {
            this("", "", "");
        }

        public String getPatternName() {
            return patternName;
        }

        public int getAgeMini() {
            return ageMini;
        }

        public int getAgeMaxi() {
            return ageMaxi;
        }

    }

    public Filter getFilter() {
        return new Filter(nom.replaceAll("\\*", "%"), ageMini, ageMaxi);
    }

}
