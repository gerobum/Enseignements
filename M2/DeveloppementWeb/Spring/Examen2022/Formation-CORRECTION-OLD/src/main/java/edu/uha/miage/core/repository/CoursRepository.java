package edu.uha.miage.core.repository;


import edu.uha.miage.core.entity.Cours;
import edu.uha.miage.core.entity.Enseignant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CoursRepository extends JpaRepository<Cours, Long> {
    public List<Cours> findCoursByResponsable(Enseignant responsable);
}
