package edu.uha.miage.init;


import edu.uha.miage.core.entity.Enseignant;
import edu.uha.miage.core.repository.EnseignantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Order(1)
@Component
public class InitEnseignant implements CommandLineRunner {

    @Autowired
    private EnseignantRepository enseignantRepository;

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        enseignantRepository.save(new Enseignant("Donald","Knuth".toUpperCase(), false));
        enseignantRepository.save(new Enseignant("Ada","Lovelace".toUpperCase(), true));
        enseignantRepository.save(new Enseignant("Margaret", "Hamilton".toUpperCase(), true));
        enseignantRepository.save(new Enseignant("Alan","Turing".toUpperCase(), false));
    }
}
