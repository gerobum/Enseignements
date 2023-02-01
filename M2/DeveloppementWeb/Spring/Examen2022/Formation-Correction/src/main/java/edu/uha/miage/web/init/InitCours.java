/*
 * Creative commons CC BY-NC-SA 2020 Yvan Maillot <yvan.maillot@uha.fr>
 *
 *     Share - You can copy and redistribute the material in any medium or format
 * 
 *     Adapt - You can remix, transform, and build upon the material 
 * 
 * Under the following terms :
 * 
 *     Attribution - You must give appropriate credit, provide a link to the license, 
 *     and indicate if changes were made. You may do so in any reasonable manner, 
 *     but not in any way that suggests the licensor endorses you or your use. 
 * 
 *     NonCommercial — You may not use the material for commercial purposes. 
 * 
 *     ShareAlike — If you remix, transform, or build upon the material, 
 *     you must distribute your contributions under the same license as the original. 
 * 
 * Notices:    You do not have to comply with the license for elements of 
 *             the material in the public domain or where your use is permitted 
 *             by an applicable exception or limitation. 
 * 
 * No warranties are given. The license may not give you all of the permissions 
 * necessary for your intended use. For example, other rights such as publicity, 
 * privacy, or moral rights may limit how you use the material. 
 * 
 * See <https://creativecommons.org/licenses/by-nc-sa/4.0/>.
 */
package edu.uha.miage.web.init;


import edu.uha.miage.dao.CoursRepo;
import edu.uha.miage.dao.EnseignantRepo;
import edu.uha.miage.web.core.entity.Cours;
import edu.uha.miage.web.core.entity.Enseignant;
import java.util.List;
import java.util.Random;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;



/**
 *
 * @author yvan
 */
@Component
@Order(2)
public class InitCours implements CommandLineRunner{
    @Autowired
    private EnseignantRepo repoEnseignant;
    @Autowired
    private CoursRepo repoCours;
    
    private Random R = new Random();

    @Override
    @Transactional
    public void run(String... args) throws Exception {
        List<Enseignant> le = repoEnseignant.findAll();
        repoCours.save(new Cours("Génie Logiciel", 40, le.get(R.nextInt(le.size()))));
        repoCours.save(new Cours("Java", 20, le.get(R.nextInt(le.size()))));
        repoCours.save(new Cours("Structure de données", 38, le.get(R.nextInt(le.size()))));
        repoCours.save(new Cours("DevWeb", 60, le.get(R.nextInt(le.size()))));
        repoCours.save(new Cours("Langage C", 20, le.get(R.nextInt(le.size()))));
        repoCours.save(new Cours("Anglais", 8, le.get(R.nextInt(le.size()))));
    }
    
}
