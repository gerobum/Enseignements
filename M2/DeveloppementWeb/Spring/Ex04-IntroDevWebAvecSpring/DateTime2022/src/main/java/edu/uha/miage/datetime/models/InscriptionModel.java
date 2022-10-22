package edu.uha.miage.datetime.models;/*
 * Creative commons CC BY-NC-SA 2021 Yvan Maillot <yvan.maillot@uha.fr>
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

/**
 * Modèle de la vue d'inscription.
 */
public class InscriptionModel {
    private String name;
    private String age;

    public InscriptionModel(String name, String age) {
        this.name = name;
        this.age = age;
    }


     // Prendre la bonne habitude de mettre un constructeur par défaut
    public InscriptionModel() {
        this("", "");
    }

    // prendre la bonne habitude d'avoir les bons getters
    public String getName() {
        return name;
    }

    // et les bons setters
    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    // toString est exploité dans la vue. Ce n'est ni optimal ni recommandé mais pour vous montrer cet usage.
    // L'idée est d'avoir un affichage qui tient des différents cas, nom absent, âge incorrect pour un affichage joli.
    public String toString() {
        String name;
        String age = "";
        int ageAsInt;
        if (this.name.isBlank())
            name = "tout le monde";
        else
            name = this.name;
        try {
            ageAsInt = Integer.parseInt(this.age);
            if (ageAsInt > 0 && ageAsInt < 150)
                age = String.format("(%d ans)", ageAsInt);
        } catch (Exception e) {
        }
        return String.format("Bonjour %s %s", name, age);
    }
}
