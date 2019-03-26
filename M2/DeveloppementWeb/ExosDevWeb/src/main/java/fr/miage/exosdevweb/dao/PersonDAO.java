// 5.1
package fr.miage.exosdevweb.dao;


import fr.miage.exosdevweb.models.Person;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonDAO extends JpaRepository<Person, Long> {
    List<Person> findByNom(String nom);
    List<Person> findByAge(int age);
    List<Person> findByNomLikeAndAgeBetween(String nom, int min, int max);
}
