package fr.miage.core.service;

import java.util.List;
import java.util.Optional;

import fr.miage.core.entity.Ancien;
// #### V0.0 Couche service
// #### V0.0 C'est une couche intermédiaire

// #### V0.0 On y définit les méthodes dont on a besoin.
// #### V0.0 Cette interface nécessite une implémentation, mais elle est très
// #### V0.0 simple à faire. Voir le paquetage impl
public interface AncienService {
    Ancien save(Ancien entity);
    void delete(Long id);
    List<Ancien> findAll();
    Optional<Ancien> findById(Long id);
    Ancien findByNomAndPrenom(String name);
    Ancien getOne(Long id);
}
