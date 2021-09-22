package edu.uha.miage.core.repository;

import edu.uha.miage.core.entity.Groupe;
import edu.uha.miage.core.entity.Etudiant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface GroupRepository extends JpaRepository<Groupe, Long> {

    Groupe findByName(String name);
    List<Groupe> findAllByOrderByName();
    // #### V2.0
    List<Groupe> findByCategoryOrderByName(Etudiant etudiant);
}
