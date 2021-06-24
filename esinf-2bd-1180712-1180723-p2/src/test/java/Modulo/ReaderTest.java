/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo;

import Mapa.Mapa;
import static Modulo.Reader.getDistance;
import java.util.ArrayList;
import java.util.Iterator;
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
 * @author joaol
 */
public class ReaderTest {
    
    public ReaderTest() {
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
     * Test of read method, of class Reader.
     * @throws java.lang.Exception
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("========= read =========");
        Mapa m=Reader.read();
        
        System.out.println("\n### Países e fronteiras de Mapa ###\n");
        for (Pais p : m.getPaises()) {
            System.out.println(p.getNome()+":");
            for (Pais p1 : m.getFronteirasDePais(p)) {
                System.out.println("    -"+p1.getNome()+";");
            }
        }
        
        Pais por=new Pais("portugal", "europa", (float) 10.31, "lisboa", (float) 38.7071631, (float) -9.135517);
        Pais den=new Pais("dinamarca", "europa", (float) 5.75, "copenhaga", (float) 55.6762944, (float) 12.5681157);
        Pais col=new Pais("colombia", "americasul", (float) 46.86, "bogota", (float) 4.6097100, (float) -74.0817500);
        Pais fin=new Pais("finlandia", "europa", (float) 5.5, "helsinque", (float) 60.1698791, (float) 24.9384078);
        Pais ita=new Pais("italia", "europa", (float) 60.59, "roma", (float) 41.8954656, (float) 12.4823243);
        Pais uru=new Pais("uruguai", "americasul", (float) 3.35, "montevideu", (float) -34.9032800, (float) -56.1881600);
        Pais chi=new Pais("chile", "americasul", (float) 16.80, "santiago", (float) -33.4569400, (float) -70.6482700);
        Pais ukr=new Pais("ucrania", "europa", (float) 42.59, "kiev", (float) 50.440951, (float) 30.5271814);
        Pais gfr=new Pais("guianafrancesa", "americasul", (float) 2.88, "caiena", (float) 4.9333300, (float) -52.3333300);
        Pais brs=new Pais("brasil", "americasul", (float) 206.12, "brasilia", (float) -15.7797200, (float) -47.9297200);
        Pais equ=new Pais("equador", "americasul", (float) 14.88, "quito", (float) -0.2298500, (float) -78.5249500);
        Pais per=new Pais("peru", "americasul", (float) 28.22, "lima", (float) -12.0431800, (float) -77.0282400);
        Pais ven=new Pais("venezuela", "americasul", (float) 31.02, "caracas", (float) 10.4880100, (float) -66.8791900);
        
        ArrayList<Pais> paisList=new ArrayList<>();
        paisList.add(por);
        paisList.add(den);
        paisList.add(col);
        paisList.add(fin);
        paisList.add(ita);
        paisList.add(uru);
        paisList.add(chi);
        paisList.add(ukr);
        paisList.add(gfr);
        paisList.add(brs);
        paisList.add(equ);
        paisList.add(per);
        paisList.add(ven);
        
        System.out.println("\n### Verificação de países ###\n");
        for (Pais p : paisList) {
            System.out.format("Verificando a existência de %s como vértice.", p.getNome());
            assertTrue("Mapa não contém "+p.getNome()+" como vértice.", m.getPaises().contains(p));
            System.out.format(" (Verificado)%n");
        }
        
        Set<Pais> colSet=new TreeSet<>();
        colSet.add(brs);
        colSet.add(equ);
        colSet.add(per);
        colSet.add(ven);
        Set<Pais> i=m.getFronteirasDePais(col);
        System.out.println("\n### Verificação de fronteiras ###\n");
        System.out.println("Colômbia:");
        for (Pais p : colSet) {
            System.out.format("    -%s", p.getNome());
            assertTrue("Colômbia não contém "+p.getNome()+" como vértice adjacente.", i.contains(p));
            System.out.format(" (Verficado)%n");
        }
    }
    
    @Test
    public void testGetDistance() {
        System.out.println("\n========= getDistance =========");
        Pais por=new Pais("portugal", "europa", (float) 10.31, "lisboa", (float) 38.7071631, (float) -9.135517);
        Pais fin=new Pais("finlandia", "europa", (float) 5.5, "helsinque", (float) 60.1698791, (float) 24.9384078);
        int result=(int) getDistance(por, fin);
        int expresult=3361;
        System.out.println("\nPortugal-Finlândia = "+result+" kms");
        assertTrue("Resultado devia ser 3361 kms.", expresult==result);
        Pais ita=new Pais("italia", "europa", (float) 60.59, "roma", (float) 41.8954656, (float) 12.4823243);
        Pais brs=new Pais("brasil", "americasul", (float) 206.12, "brasilia", (float) -15.7797200, (float) -47.9297200);
        result=(int) getDistance(ita, brs);
        expresult=8905;
        System.out.println("\nItália-Brasil = "+result+" kms");
        assertTrue("Resultado devia ser 8905 kms.", expresult==result);
        Pais den=new Pais("dinamarca", "europa", (float) 5.75, "copenhaga", (float) 55.6762944, (float) 12.5681157);
        result=(int) getDistance(den, den);
        expresult=0;
        System.out.println("\nDinamarca-Dinamarca = "+result+" kms");
        assertTrue("Resultado devia ser 0 kms.", expresult==result);
    }
    
}
