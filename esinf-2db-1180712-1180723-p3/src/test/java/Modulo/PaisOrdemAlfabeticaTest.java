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
public class PaisOrdemAlfabeticaTest {
    
    public PaisOrdemAlfabeticaTest() {
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
     * Test of compareTo method, of class PaisOrdemAlfabetica.
     */
    @Test
    public void testCompareTo() {
        System.out.println("compareTo");
        PaisOrdemAlfabetica o = new PaisOrdemAlfabetica("portugal", "europa", 10.31F, "lisboa", 38.7071631, -9.135517);
        PaisOrdemAlfabetica instance = new PaisOrdemAlfabetica("argentina", "americasul", 41.67F, "buenosaires", -34.6131500, -58.3772300);
        int result = instance.compareTo(o);
        assertTrue( result <0 );
    }
    
}
