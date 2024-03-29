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
package edu.uha.miage.urlmapping.services.interfaces.implementations;

import edu.uha.miage.urlmapping.dao.PersonRepository;
import edu.uha.miage.urlmapping.services.Filter;
import edu.uha.miage.urlmapping.services.Person;
import edu.uha.miage.urlmapping.services.interfaces.PersonService;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

// #### 05
@Service
public class PersonServiceImpl implements PersonService {
    @Autowired
    private PersonRepository personRepo;

    @Override
    public Person save(Person person) {
        return personRepo.save(person);
    }

    @Override
    public void delete(Long id) {
        personRepo.deleteById(id);
    }

    @Override
    public List<Person> findAll() {
        return personRepo.findAll();
    }

    @Override
    public List<Person> findByNameLikeAndAgeBetween(String nom, int min, int max) {
        return personRepo.findByNameLikeAndAgeBetween(nom, min, max);
    }

    @Override
    public Optional<Person> findById(Long id) {
        return personRepo.findById(id);
    }

    @Override
    public List<Person> findByFilter(Filter filter) {
        return findByNameLikeAndAgeBetween(filter.getPatternName(), filter.getAgeMini(), filter.getAgeMaxi());
    }
}
