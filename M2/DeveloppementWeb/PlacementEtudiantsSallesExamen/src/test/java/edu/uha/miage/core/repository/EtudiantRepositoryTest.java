/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uha.miage.core.repository;

import edu.uha.miage.core.entity.Etudiant;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author yvan
 */

// #### V1.2  @RunWith(SpringJUnit4ClassRunner.class) Indique que la classe utilise JUnit pour les tests unitaires
// #### V1.2 https://docs.spring.io/spring-framework/docs/current/javadoc-api/org/springframework/test/context/junit4/SpringJUnit4ClassRunner.html
@RunWith(SpringJUnit4ClassRunner.class)// #### V1.2 Equivalent à @RunWith(SpringRunner.class)

// #### V1.2  @DataJpaTest doit être employé dans le cas de test Jpa, notamment 
// #### V1.2  pour que l'injection de dépendance se passe bien.

// #### V1.2  https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/orm/jpa/DataJpaTest.html
@DataJpaTest
public class EtudiantRepositoryTest {
    // #### V1.2  Du logging peut être utile
    private static final Logger LOGGER = LoggerFactory.getLogger(EtudiantRepositoryTest.class);
    // #### V1.2 Du logging peut être utile
    
    // #### V1.2 Injection de dépendance 
    @Autowired
    EtudiantRepository etudiantRepository;
    

    public EtudiantRepositoryTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    // #### V1.2 Des exemples sont enregistrés avant chaque test. 
    @Before
    public void setUp() {
        saveCategory("A");
        saveCategory("B");
        saveCategory("Z");
    }

    private void saveCategory(String name) {
        if (etudiantRepository.findByName(name) == null) {
            Etudiant etudiant = new Etudiant(name);
            etudiantRepository.save(etudiant);
        }
    }

    @After
    public void tearDown() {
    }

    // #### V1.2 Test pour voir si le findByName se passe bien.
    @Test
    public void testFindByName() {
        LOGGER.info("Test findByName");
        System.out.println("Test findByName");
        assertNotNull(etudiantRepository.findByName("A"));
        assertNull(etudiantRepository.findByName("W"));
        saveCategory("W");
        assertNotNull(etudiantRepository.findByName("W"));
    }

    // #### V1.2 Et ainsi de suite. Voir un cours sur les tests unitaires.
    @Test
    public void testCount() {
        LOGGER.info("Test Count");
        System.out.println("Test Count");
        assertEquals(3, etudiantRepository.count());
        saveCategory("W");
        assertEquals(4, etudiantRepository.count());
    }

    // #### V1.2 Ce ne sont que des exemples. 
    @Test
    public void testDelete() {
        LOGGER.info("Test Count");
        System.out.println("Test Count");
        Etudiant a = etudiantRepository.findByName("A");
        long n = etudiantRepository.count();
        etudiantRepository.delete(a);
        assertEquals(n-1, etudiantRepository.count());
        etudiantRepository.deleteAll();
        assertEquals(0, etudiantRepository.count());
    }

}
