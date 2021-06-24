/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo;

import java.util.LinkedList;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Utilizador
 */
public class PaisTest {
    
    public PaisTest() {
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
     * Test of hashCode method, of class Pais.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Pais instance = new Pais("portugal", "europa", 10.31F, "lisboa", 38.7071631, -9.135517);
        int expResult = -828794553;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pais.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Pais("portugal", "europa", 10.31F, "lisboa", 38.7071631, -9.135517);
        Pais instance = new Pais("portugal", "europa", 10.31F, "lisboa", 38.7071631, -9.135517);;
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }
}
