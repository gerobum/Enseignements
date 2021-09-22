package edu.uha.miage.core.service.impl;

import edu.uha.miage.core.entity.Etudiant;
import edu.uha.miage.core.repository.EtudiantRepository;
import edu.uha.miage.core.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// #### V1.0 @Service est une annotation qui spécialise @Component.
// #### V1.0 Elle indique une classe métier.
// #### V1.0 https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/stereotype/Service.html
@Service
public class CategoryServiceImpl implements CategoryService {

// #### V1.0 @Autowired permet d'injecter automatiquement la bonne dépendance.
// #### V1.0 Elle est fabriquée automatiquement par Spring (voir EtudiantRepository)
    @Autowired
EtudiantRepository etudiantRepository;

// #### V1.0 Le reste s'écrit facilement.
    @Override
    public Etudiant save(Etudiant entity) {
        return etudiantRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        etudiantRepository.deleteById(id);
    }
// #### V1.3 L'avantage de modifier à l'implémentation de findAll dans la couche
// #### V1.3 service est que toute invocation à cette méthode aura pour effet
// #### V1.3 de toujours retourner la liste dans l'ordre alphabétique. 
// #### V1.3 Il n'y aura rien d'autres à toucher. 
    @Override
    public List<Etudiant> findAll() {
        return (List<Etudiant>) etudiantRepository.findAllByOrderByName();
    }

    @Override
    public Optional<Etudiant> findById(Long id) {
        return etudiantRepository.findById(id);
    }

    @Override
    public Etudiant findByName(String name) {
        return etudiantRepository.findByName(name);
    }

    @Override
    public Etudiant getOne(Long id) {
        return etudiantRepository.getOne(id);
    }
}
