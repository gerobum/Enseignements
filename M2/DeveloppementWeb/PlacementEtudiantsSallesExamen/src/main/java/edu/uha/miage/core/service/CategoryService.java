package edu.uha.miage.core.service;

import edu.uha.miage.core.entity.Etudiant;

import java.util.List;
import java.util.Optional;


// #### V0.1 On y définit les méthodes dont on a besoin.
// #### V0.1 Cette interface nécessite une implémentation, mais elle est très
// #### V0.1 simple à faire. Voir le paquetage impl
public interface CategoryService {
    Etudiant save(Etudiant entity);
    void delete(Long id);
    List<Etudiant> findAll();
    Optional<Etudiant> findById(Long id);
    Etudiant findByName(String name);
    Etudiant getOne(Long id);
}
