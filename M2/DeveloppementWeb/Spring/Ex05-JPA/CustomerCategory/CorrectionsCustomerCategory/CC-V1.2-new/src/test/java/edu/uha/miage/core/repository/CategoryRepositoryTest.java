
package edu.uha.miage.core.repository;

import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author yvan
 */
// #### V1.2  @RunWith(SpringJUnit4ClassRunner.class) Indique que la classe utilise JUnit pour les tests unitaires
// #### V1.2 https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/context/junit4/SpringJUnit4ClassRunner.html
@RunWith(SpringRunner.class)// #### V1.2 Equivalent à @RunWith(SpringRunner.class)

// #### V1.2  @DataJpaTest doit être employé dans le cas de test Jpa, notamment 
// #### V1.2  pour que l'injection de dépendance se passe bien.

// #### V1.2  https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/orm/jpa/DataJpaTest.html
@DataJpaTest
public class CategoryRepositoryTest {
    
    public CategoryRepositoryTest() {
    }

    @org.junit.BeforeClass
    public static void setUpClass() throws Exception {
    }

    @org.junit.AfterClass
    public static void tearDownClass() throws Exception {
    }

    @org.junit.Before
    public void setUp() throws Exception {
    }

    @org.junit.After
    public void tearDown() throws Exception {
    }

    /**
     * Test of findByName method, of class CategoryRepository.
     */
    @org.junit.Test
    public void testFindByName() {
        System.out.println("findByName");
        fail("The test case is a prototype.");
    }
    
}
