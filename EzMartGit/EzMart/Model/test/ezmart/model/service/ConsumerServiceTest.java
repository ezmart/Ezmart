/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ezmart.model.service;

import ezmart.model.entity.Consumer;
import ezmart.model.entity.User;
import java.util.List;
import java.util.Map;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Marcos Paulo
 */
public class ConsumerServiceTest {
    
    public ConsumerServiceTest() {
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
     * Test of create method, of class ConsumerService.
     */
    @Test
    public void testCreate() throws Exception {
        System.out.println("create");
        Consumer entity = null;
        ConsumerService instance = new ConsumerService();
        instance.create(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readById method, of class ConsumerService.
     */
    @Test
    public void testReadById_Long() throws Exception {
        System.out.println("readById");
        Long id = null;
        ConsumerService instance = new ConsumerService();
        Consumer expResult = null;
        Consumer result = instance.readById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readByCriteria method, of class ConsumerService.
     */
    @Test
    public void testReadByCriteria() throws Exception {
        System.out.println("readByCriteria");
        Map<Long, Object> criteria = null;
        Long limit = null;
        Long offset = null;
        ConsumerService instance = new ConsumerService();
        List<Consumer> expResult = null;
        List<Consumer> result = instance.readByCriteria(criteria, limit, offset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ConsumerService.
     */
    @Test
    public void testUpdate() throws Exception {
        System.out.println("update");
        Consumer entity = null;
        ConsumerService instance = new ConsumerService();
        instance.update(entity);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ConsumerService.
     */
    @Test
    public void testDelete() throws Exception {
        System.out.println("delete");
        Long id = null;
        ConsumerService instance = new ConsumerService();
        instance.delete(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of validate method, of class ConsumerService.
     */
    @Test
    public void testValidate() throws Exception {
        System.out.println("validate");
        Map<String, Object> fields = null;
        ConsumerService instance = new ConsumerService();
        Map<String, String> expResult = null;
        Map<String, String> result = instance.validate(fields);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readById method, of class ConsumerService.
     */
    @Test
    public void testReadById_User() throws Exception {
        System.out.println("readById");
        User user = null;
        ConsumerService instance = new ConsumerService();
        instance.readById(user);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readByUserId method, of class ConsumerService.
     */
    @Test
    public void testReadByUserId() throws Exception {
        System.out.println("readByUserId");
        Long id = null;
        ConsumerService instance = new ConsumerService();
        Consumer expResult = null;
        Consumer result = instance.readByUserId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
