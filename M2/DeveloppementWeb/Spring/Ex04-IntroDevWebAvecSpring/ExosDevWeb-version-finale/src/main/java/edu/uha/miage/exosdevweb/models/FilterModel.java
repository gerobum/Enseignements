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
package edu.uha.miage.exosdevweb.models;

import java.io.Serializable;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class FilterModel implements Serializable {
    private String nom;
    private Integer ageMini;
    private Integer ageMaxi;

    public FilterModel(String nom, Integer ageMini, Integer ageMaxi) {
        this.nom = nom;
        this.ageMini = ageMini;
        this.ageMaxi = ageMaxi;
    }

    public FilterModel() {
        this("%", 0, 150);
    }
    
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        nom = (nom == null || nom.trim().isEmpty()) ? "%" : nom;
        this.nom = nom;
    }

    public Integer getAgeMini() {
        return ageMini;
    }

    public void setAgeMini(Integer ageMini) {
        ageMini = (ageMini == null || ageMini < 0) ? 0 : ageMini;
        this.ageMini = ageMini;
    }

    public Integer getAgeMaxi() {
        return ageMaxi;
    }

    public void setAgeMaxi(Integer ageMaxi) {
        ageMaxi = (ageMaxi == null || ageMaxi < ageMini) ? 150 : ageMaxi;
        this.ageMaxi = ageMaxi;
    }
    
    
}
