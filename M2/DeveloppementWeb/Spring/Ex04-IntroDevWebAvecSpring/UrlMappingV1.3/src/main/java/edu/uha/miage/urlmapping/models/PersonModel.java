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


import edu.uha.miage.urlmapping.services.Person;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 *
 * @author Yvan Maillot <yvan.maillot@uha.fr>
 */
// #### 03
public class PersonModel {

    @NotNull
    @Size(min = 2, max = 30, message = "Le nom doit contenir de 2 à 30 lettres")
    private String nom;
    @NotNull
    @Pattern(regexp = "18|19|[2-9][0-9]", message = "L'âge doit être compris entre 18 et 99 ans")
    private String age;
    @NotNull
    private Person person;

    public PersonModel(String nom, String age) {
        this.nom = nom;
        this.age = age;
        this.person = new Person();
    }

    public PersonModel() {
        this("", "");
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
        this.person.setName(nom);
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
        try {
            this.person.setAge(Integer.parseInt(age));
        } catch (NumberFormatException ex) {
            this.person.setAge(-1);
        }
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

}
