package edu.uha.miage.core.service;

import edu.uha.miage.core.entity.Groupe;
import edu.uha.miage.core.entity.Etudiant;
import java.util.List;
import java.util.Optional;

public interface CustomerService {
    Groupe save(Groupe entity);
    void delete(Long id);
    List<Groupe> findAll();
    Optional<Groupe> findById(Long id);
    Groupe findByName(String name);
    Groupe getOne(Long id);
    // #### V2.0
    List<Groupe> findByCategoryOrderByName(Etudiant etudiant);
    List<Groupe> findByCategoryOrderByName(String categoryName);
}
