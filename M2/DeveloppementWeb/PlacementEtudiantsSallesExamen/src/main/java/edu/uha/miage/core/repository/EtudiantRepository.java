package edu.uha.miage.core.repository;

import edu.uha.miage.core.entity.Etudiant;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EtudiantRepository extends JpaRepository<Etudiant, Long> {
    Etudiant findByName(String name);
    List<Etudiant> findAllByOrderByName();
}
