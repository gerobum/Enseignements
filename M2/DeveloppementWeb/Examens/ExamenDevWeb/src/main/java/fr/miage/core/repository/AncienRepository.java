package fr.miage.core.repository;

import fr.miage.core.entity.Ancien;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
// #### V0.0 https://docs.spring.io/spring-data/jpa/docs/current/api/org/springframework/data/jpa/repository/JpaRepository.html

public interface AncienRepository extends JpaRepository<Ancien, Long> {
    //Ancien findByNomAndPrenom(String nom, String prenom);  
// #### V0.0 https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods

    //List<Ancien> findByPromoByOrderByName(int promo);
}
