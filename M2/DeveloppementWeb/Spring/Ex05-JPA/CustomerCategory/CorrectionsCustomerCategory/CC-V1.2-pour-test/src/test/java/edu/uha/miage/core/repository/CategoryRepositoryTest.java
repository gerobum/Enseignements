/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.uha.miage.core.repository;

import edu.uha.miage.core.entity.Category;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author yvan
 */
@RunWith(SpringJUnit4ClassRunner.class)// #### V1.2 Equivalent à @RunWith(SpringRunner.class)

// #### V1.2  @DataJpaTest doit être employé dans le cas de test Jpa, notamment 
// #### V1.2  pour que l'injection de dépendance se passe bien.

// #### V1.2  https://docs.spring.io/spring-boot/docs/current/api/org/springframework/boot/test/autoconfigure/orm/jpa/DataJpaTest.html
@DataJpaTest
public class CategoryRepositoryTest {
    
    public CategoryRepositoryTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of findByName method, of class CategoryRepository.
     */
    @Test
    public void testFindByName() {
        System.out.println("findByName");
        fail("The test case is a prototype.");
    }
    
}
