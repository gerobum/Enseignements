package edu.uha.miage.core.repository;

import edu.uha.miage.core.entity.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EnseignantRepository extends JpaRepository<Enseignant, Long> {
    public List<Enseignant> findEnseignantsByFirstnameAndLastname(String firstname, String lastname);
    public List<Enseignant> findEnseignantsByMiss(boolean miss);
}
