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
package edu.uha.miage.models;

import java.io.Serializable;

/**
 *
 * @author yvan
 */
public class PersonModel implements Serializable {

    private String nom;
    private String age;
    private String toString;

    public PersonModel() {
        this("", "");
    }

    public PersonModel(String nom, String age) {
        this.nom = nom;
        this.age = age;
        createToString();
    }

    private void createToString() {
        toString = "";
        if (nom == null || nom.isBlank()) {
            toString = "cher visiteur";
        } else {
            toString = nom;
            try {
                int ageAsInt = Integer.parseInt(age);
                if (ageAsInt >= 0) {
                    if (ageAsInt < 2) {
                        toString += " (" + ageAsInt + " an)";
                    } else {
                        toString += " (" + ageAsInt + " ans)";
                    }
                }
            } catch (Exception ex) {
                
            }
        }
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
        if (nom == null || nom.isBlank()) {
            
        }
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }
    
    @Override
    public String toString() {
        return toString;
    }

}
