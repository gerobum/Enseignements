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
package edu.uha.miage.person.models;

import edu.uha.miage.person.service.Person;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yvan
 */
public class ModelPersonEntry {

    private String firstname;
    private String lastname;
    private String age;
    private String gender;
    private List<String> messages;
    private boolean firstnameOk;
    private boolean lastnameOk;
    private boolean ageOk;
    private Person person;

    public ModelPersonEntry() {
        this("", "", "", "Femme");
    }

    public ModelPersonEntry(String firstname, String lastname, String age, String gender) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.age = age;
        this.gender = gender;
        this.firstnameOk = true;
        this.firstnameOk = true;
        this.lastnameOk = true;
        this.ageOk = true;
        this.messages = new ArrayList<>();
        this.person = null;
    }

    public String getFirstname() {
        return firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public String getAge() {
        return age;
    }

    public String getGender() {
        return gender;
    }

    public List<String> getMessages() {
        return messages;
    }

    public boolean isAgeOk() {
        return ageOk;
    }

    public Person getPerson() {
        return person;
    }

    public boolean isFirstnameOk() {
        return firstnameOk;
    }

    public boolean isLastnameOk() {
        return lastnameOk;
    }
    
    public boolean isOk() {
        return firstnameOk && lastnameOk && ageOk;
    }

    public static ModelPersonEntry handle(HttpServletRequest request) {

        ModelPersonEntry mpe = new ModelPersonEntry(
                request.getParameter("prenom"),
                request.getParameter("nom"),
                request.getParameter("age"),
                request.getParameter("genre")
        );

        checkFirstName(mpe);
        checkLastName(mpe);
        checkAge(mpe);
        
        if (mpe.isOk()) {
            mpe.person = new Person(mpe.firstname, mpe.lastname, Integer.parseInt(mpe.age), "Homme".equals(mpe.gender)?"M.":"Mme");
        }

        return mpe;
    }

    private static void checkFirstName(ModelPersonEntry mpe) {
        mpe.firstnameOk = true;
        if (!mpe.firstname.matches("[A-Z\u00C0-\u00D6\u00D8-\u00DE][a-z\u00E0-\u00F6\u00F8-\u00FF-'\\s]*")) {
            mpe.firstnameOk = false;
            mpe.messages.add("Le prénom n'est pas valide");
           
        }
    }

    private static void checkLastName(ModelPersonEntry mpe) {
        mpe.lastnameOk = true;
        if (!mpe.lastname.matches("[A-Z\u00C0-\u00D6\u00D8-\u00DE-'\\s]+")) {
            mpe.lastnameOk = false;
            mpe.messages.add("Le nom n'est pas valide");
        }
    }

    private static void checkAge(ModelPersonEntry mpe) {
        int age;
        try {
            age = Integer.parseInt(mpe.age);
            if (age < 0 || age > 150) {
                mpe.ageOk = false;
                mpe.messages.add("L'âge doit être entre 0 et 150");
            } else {
                mpe.ageOk = true;
            }
        } catch (NumberFormatException ex) {
            mpe.ageOk = false;
            mpe.messages.add("L'âge doit être un entier positif");
        }
    }

    @Override
    public String toString() {
        return ("Homme".equals(gender) ? "M. " : ("Mme ")) + firstname + " " + lastname + " (" + age + " ans)";
    }
}
