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

import edu.uha.miage.datetime.services.Person;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Modèle de la vue de validation.
 *
 * C'est le même que pour la vue inscription à la différence des contraintes rajoutées aux champs
 */
public class ValidationModel {

    @NotNull
    @Size(min = 2, message = "Au moins deux lettres")
    private String name;

    @NotNull
    @Pattern(regexp = "18|19|[2-9][0-9]|1[0-4][0-9]|150", message = "entre 18 et 150")
    private String age;

    public ValidationModel(String name, String age) {
        this.name = name;
        this.age = age;
    }

     // Prendre la bonne habitude de mettre un constructeur par défaut
    public ValidationModel() {
        this("", "");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public Person getPerson() {
        try {
            return new Person(name, Integer.parseInt(age));
        } catch(Exception ex) {
            return null;
        }
    }
}
