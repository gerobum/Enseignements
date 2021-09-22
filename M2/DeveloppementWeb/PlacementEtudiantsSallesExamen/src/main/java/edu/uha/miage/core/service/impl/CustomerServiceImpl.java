package edu.uha.miage.core.service.impl;

import edu.uha.miage.core.entity.Etudiant;
import edu.uha.miage.core.entity.Groupe;
import edu.uha.miage.core.repository.EtudiantRepository;
import edu.uha.miage.core.repository.GroupRepository;
import edu.uha.miage.core.service.CustomerService;
import java.util.LinkedList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    GroupRepository groupRepository;
    @Autowired
    EtudiantRepository etudiantRepository;

    @Override
    public Groupe save(Groupe entity) {
        return groupRepository.save(entity);
    }

    @Override
    public void delete(Long id) {
        groupRepository.deleteById(id);
    }

    @Override
    public List<Groupe> findAll() {
        return (List<Groupe>) groupRepository.findAllByOrderByName();
    }

    @Override
    public Optional<Groupe> findById(Long id) {
        return groupRepository.findById(id);
    }

    @Override
    public Groupe findByName(String name) {
        return groupRepository.findByName(name);
    }

    @Override
    public Groupe getOne(Long id) {
        return groupRepository.getOne(id);
    }

    // #### V2.0
    @Override
    public List<Groupe> findByCategoryOrderByName(Etudiant etudiant) {
        return groupRepository.findByCategoryOrderByName(etudiant);
    }

    @Override
    public List<Groupe> findByCategoryOrderByName(String categoryName) {
        if (categoryName == null || categoryName.isBlank()) {
            return findAll();
        } else {
            if ("0".equals(categoryName)) {
                return groupRepository.findByCategoryOrderByName(null);
            } else {
                Etudiant etudiant = etudiantRepository.findByName(categoryName);
                if (etudiant != null) {
                    return groupRepository.findByCategoryOrderByName(etudiant);
                } else {
                    return new LinkedList<>();
                }
            }
        }
    }
}
