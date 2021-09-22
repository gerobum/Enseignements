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

import java.io.Serializable;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
public class FilterModel implements Serializable{
    @NotNull
    @Size(min = 1)
    private String nom;
    @Min(18)
    @Max(99)
    private int min;
    @Min(18)
    @Max(99)
    private int max;
    
    @javax.validation.constraints.AssertTrue(message = "Attention : âge min > âge max")
    private boolean minMax = false;
    
    public FilterModel() {
        nom = "%";
        min = 18;
        max = 99;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public int getMin() {
        return min;
    }

    public void setMin(int min) {
        this.min = min;
        minMax = min <= max;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
        minMax = min <= max;
    }

    public boolean isMinMax() {
        return minMax;
    }
    
}
