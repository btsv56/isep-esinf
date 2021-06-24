/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf_2db_1180712_1180723;

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

    /**
     * Test of hashCode method, of class Pais.
     */
    @Test
    public void testHashCode() {
        System.out.println("hashCode");
        Pais instance = new Pais("Portugal", "Europa", 10000000, "Lisboa", (float) 12.2, 13);
        int expResult = -1341748274;
        int result = instance.hashCode();
        assertEquals(expResult, result);
    }

    /**
     * Test of equals method, of class Pais.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = new Pais("Portugal", "Europa", 10000000, "Lisboa", (float) 12.2, 13);
        Pais instance = new Pais("Portugal", "Europa", 10000000, "Lisboa", (float) 12.2, 13);
        boolean expResult = true;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
    }

    @Test
    public void testEquals2() {
        System.out.println("equals");
        Object obj = new Pais("Portugal", "Europa", 10000000, "Lisboa", (float) 12.2, 14);
        Pais instance = new Pais("Portugal", "Europa", 10000000, "Lisboa", (float) 12.2, 13);
        boolean expResult = false;
        boolean result = instance.equals(obj);
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
        assertEquals(expResult, result);
    }

}
