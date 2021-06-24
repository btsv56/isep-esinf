/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo;

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
        System.out.println("------hashCode------\n");
        Pais instance = new Pais("Portugal", "Europa", 10000000, "Lisboa", (float) 12.2, 13);
        int expResult = -1341748274;
        int result = instance.hashCode();
        System.out.println("Hash code esperado: "+expResult+"\nHash code obtido: "+result);
        System.out.println("------------------------------------");
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pais.
     */
    @Test
    public void testEquals() {
        System.out.println("\nequals\n");
        Object obj = new Pais("Portugal", "Europa", 10000000, "Lisboa", (float) 12.2, 13);
        Pais instance = new Pais("Portugal", "Europa", 10000000, "Lisboa", (float) 12.2, 13);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        System.out.println("Objeto:\n"+obj+"\nPais:\n"+instance);
        System.out.println("Paises iguais? True\n------------------------------------");
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals2() {
        System.out.println("equals");
        Object obj = new Pais("Portugal", "Europa", 10000000, "Lisboa", (float) 12.2, 14);
        Pais instance = new Pais("Portugal", "Europa", 10000000, "Lisboa", (float) 12.2, 13);
        boolean expResult = false;
        boolean result = instance.equals(obj);
        System.out.println("Objeto:\n"+obj+"\nPais:\n"+instance);
        System.out.println("Paises iguais? false\n------------------------------------");
        assertEquals(expResult, result);
    }

    /**
     * Test of compareTo method, of class Pais.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        Pais o = new Pais("Portugal", "Europa", 10000000, "Lisboa", (float) 12.2, 13);;
        Pais instance = new Pais("Espanha", "Europa", 20000000, "Lisboa", (float) 14.2, 17);;
        int expResult = 1;
        int result = instance.compareTo(o);
        System.out.println("Pais 1:\n"+o);
        System.out.println("Pais 2:\n"+instance);
        System.out.println("Pais com maior população: "+instance.getNome());
        System.out.println("------------------------------------");
        assertEquals(expResult, result);
    }
}
