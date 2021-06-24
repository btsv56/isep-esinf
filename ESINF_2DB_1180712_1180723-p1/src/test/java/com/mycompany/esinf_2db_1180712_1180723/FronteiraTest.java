/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf_2db_1180712_1180723;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;
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
public class FronteiraTest {

    private Fronteira instance;

    public FronteiraTest() throws FileNotFoundException {
        Map<Pais, Set<Pais>> m = new HashMap<>();
        m = Reader.read();
        instance = new Fronteira(m);
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    /**
     * Test of devolverPaisesMaisNHabitantes method, of class Fronteira.
     */
    @Test
    public void testDevolverPaisesMaisNHabitantes() {
        System.out.println("devolverPaisesMaisNHabitantes");
        String continente = "americasul";
        float numHabitantes = 46.0F;
        LinkedList<Pais> result = instance.devolverPaisesMaisNHabitantes(continente, numHabitantes);
        LinkedList<Pais> expResult = new LinkedList<>();
        expResult.add(new Pais("colombia", "americasul", 46.86F, "bogota", 4.6097100F, -74.0817500F));
        expResult.add(new Pais("brasil", "americasul", 206.12F, "brasilia", -15.7797200F, -47.9297200F));
        assertEquals(expResult, result);
    }

    /**
     * Test of devolverPaisesPorNumFronteiras method, of class Fronteira.
     */
    @Test
    public void testDevolverPaisesPorNumFronteiras() {
        System.out.println("devolverPaisesPorNumFronteiras");
        String continente = "europa";
        Map<Integer, Set<Pais>> result = instance.devolverPaisesPorNumFronteiras(continente);
        Iterator<Integer> it = result.keySet().iterator();
        Integer i = it.next();
        assertTrue(i == 9);
        assertTrue(result.get(i).contains(new Pais("alemanha", "europa", 82.8F, "berlim", 52.5234051F, 13.4113999F)));

        i = it.next();
        assertTrue(i == 8);
        assertTrue(result.get(i).contains(new Pais("austria", "europa", 8.77F, "viena", 48.2092062F, 16.3727778F)));
        assertTrue(result.get(i).contains(new Pais("servia", "europa", 7.04F, "belgrado", 44.802416F, 20.465601F)));
        assertTrue(result.get(i).contains(new Pais("russia", "europa", 146.5F, "moscovo", 55.755786F, 37.617633F)));

        i = it.next();
        assertTrue(i == 7);
        assertTrue(result.get(i).contains(new Pais("ucrania", "europa", 42.59F, "kiev", 50.440951F, 30.5271814F)));
        assertTrue(result.get(i).contains(new Pais("hungria", "europa", 9.8F, "budapeste", 47.4984056F, 19.0407578F)));
        assertTrue(result.get(i).contains(new Pais("franca", "europa", 66.99F, "paris", 48.8566667F, 2.3509871F)));

        while (it.hasNext()) {
            i = it.next();
        }
        assertTrue(i == 0);
        assertTrue(result.get(i).contains(new Pais("islandia", "europa", 0.34F, "reiquiavique", 64.135338F, -21.89521F)));
        assertTrue(result.get(i).contains(new Pais("malta", "europa", 0.44F, "valletta", 35.904171F, 14.518907F)));
        assertTrue(result.get(i).contains(new Pais("chipre", "europa", 0.85F, "nicosia", 35.167604F, 33.373621F)));

    }

    @Test
    public void testDevolverPaisesPorNumFronteiras2() {
        System.out.println("devolverPaisesPorNumFronteiras");
        String continente = "americasul";
        Map<Integer, Set<Pais>> result = instance.devolverPaisesPorNumFronteiras(continente);
        Iterator<Integer> it = result.keySet().iterator();
        Integer i = it.next();
        assertTrue(i == 10);
        assertTrue(result.get(i).contains(new Pais("brasil", "americasul", 206.12F, "brasilia", -15.7797200F, -47.9297200F)));

        i = it.next();
        assertTrue(i == 5);
        assertTrue(result.get(i).contains(new Pais("bolivia", "americasul", 9.70F, "lapaz", -16.5000000F, -68.1500000F)));
        assertTrue(result.get(i).contains(new Pais("argentina", "americasul", 41.67F, "buenosaires", -34.6131500F, -58.3772300F)));
        assertTrue(result.get(i).contains(new Pais("peru", "americasul", 28.22F, "lima", -12.0431800F, -77.0282400F)));

        i = it.next();
        assertTrue(i == 4);
        assertTrue(result.get(i).contains(new Pais("colombia", "americasul", 46.86F, "bogota", 4.6097100F, -74.0817500F)));

        i = it.next();
        assertTrue(i == 3);
        assertTrue(result.get(i).contains(new Pais("chile", "americasul", 16.80F, "santiago", -33.4569400F, -70.6482700F)));
        assertTrue(result.get(i).contains(new Pais("paraguai", "americasul", 6.24F, "assuncao", -25.3006600F, -57.6359100F)));
        assertTrue(result.get(i).contains(new Pais("venezuela", "americasul", 31.02F, "caracas", 10.4880100F, -66.8791900F)));
        assertTrue(result.get(i).contains(new Pais("guiana", "americasul", 0.07F, "georgetwon", 6.8044800F, -58.1552700F )));
        assertTrue(result.get(i).contains(new Pais("suriname", "americasul", 0.04F, "paramaribo", 5.8663800F, -55.1668200F)));
        
        i=it.next();
        assertTrue(i==2);
        assertTrue(result.get(i).contains(new Pais("equador", "americasul", 14.88F, "quito", -0.2298500F, -78.5249500F)));
        assertTrue(result.get(i).contains(new Pais("guianafrancesa", "americasul", 2.88F, "caiena", 4.9333300F, -52.3333300F)));
        assertTrue(result.get(i).contains(new Pais("uruguai", "americasul", 3.35F, "montevideu", -34.9032800F, -56.1881600F)));
    }

    /**
     * Test of boasViagens method, of class Fronteira.
     */
    @Test
    public void testObterMinFronteiras() {
        System.out.println("boasViagens");
        Pais pais1 = new Pais("portugal", "europa", 10.31F, "lisboa", 38.7071631F, -9.135517F);
        Pais pais2 = new Pais("grecia", "europa", 10.76F, "atenas", 37.97918F, 23.716647F);
        int expResult = 8;
        int result = instance.obterMinFronteiras(pais1, pais2);
        assertEquals(expResult, result);
    }

    @Test
    public void testObeterMinFronteiras2() {
        System.out.println("boasViagens");
        Pais pais1 = new Pais("portugal", "europa", 10.31F, "lisboa", 38.7071631F, -9.135517F);
        Pais pais2 = new Pais("argentina", "americasul", 41.67F, "buenosaires", -34.6131500F, -58.3772300F);
        int expResult = -1;
        int result = instance.obterMinFronteiras(pais1, pais2);
        assertEquals(expResult, result);
    }

}
