
package edu.uha.miage.model;

import edu.uha.miage.metier.Person;
import java.io.Serializable;
import javax.servlet.http.HttpServletRequest;

/**
 *
 * @author yvan
 */
public class PersonFormModel implements Serializable {
    // Les champs de saisie du formulaire
    private String surname = "", name = "", age = "", gender = "Homme";
    // Pour la validation de l'age
    private boolean ageOk;
    // Le message de bienvenue ou d'alerte.
    private String message = "";
    // La person s'il est possible de la créer.
    private Person person;

    public PersonFormModel(Person person) {
        this.setPerson(person);
    }

    public PersonFormModel() {
        person = new Person();
        message = "";
        ageOk = true;
    }

    public PersonFormModel(String msg) {
        person = new Person();
        message = msg;
        ageOk = true;
    }

    public Person getPerson() {
        return person;
    }

    public void setPerson(Person person) {
        this.person = person;
        surname = person.getSurname();
        name = person.getName();
        age = person.getAge()+"";
        gender = person.isMister() ? "Homme" : "Femme";
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isAgeOk() {
        return ageOk;
    }

    public void setAgeOk(boolean ageOk) {
        this.ageOk = ageOk;
    }

    public String getGender() {
        return gender;
    }

    public void setGenre(String gender) {
        this.gender = gender;
    }    
    
    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public void setÂge(String age) {
        this.age = age;
    }
       
    public static PersonFormModel handle(HttpServletRequest request) {
        // Création d'un modèle de formulaire vierge
        PersonFormModel model = new PersonFormModel();
        // Récupération des champs saisies dans le formulaire
        model.surname = (String) request.getParameter("nom");
        model.name = (String) request.getParameter("prenom");
        model.age = request.getParameter("age");
        model.gender = request.getParameter("genre");
        // Vérification de l'age
        int âge = -1;
        try {
            âge = Integer.parseInt(model.age);
            model.ageOk = âge > 0;
        } catch (NumberFormatException ex) {
            model.ageOk = false;
        }
        // Construction du message et des alertes.
        if (model.ageOk) {
            model.person = new Person(model.gender.equals("Homme"), model.surname, model.name, âge);
            model.message = String.format("Bonjour %s %s %s, %d ans", 
                    (model.person.isMister()?"M.":"Mme"), 
                    model.person.getName(), 
                    model.person.getSurname(), 
                    model.person.getAge());
        } else {
            model.setMessage("L'âge doit être un entier supérieur à 0 écrit en chiffres");
        }
        return model;
    }
}
