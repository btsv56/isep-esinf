/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo;

import Mapa.Mapa;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
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
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        Mapa result = Reader.read();
        System.out.println("\n### Países e fronteiras de Mapa ###\n");
        for (Pais p : result.listaOrdenadaPaisesContinente("americasul")) {
            System.out.println(p.getNome()+":");
            for (Pais p1 : p.getFronteiras()) {
                System.out.println("    -"+p1.getNome()+";");
            }
        }
        for (Pais p : result.listaOrdenadaPaisesContinente("europa")) {
            System.out.println(p.getNome()+":");
            for (Pais p1 : p.getFronteiras()) {
                System.out.println("    -"+p1.getNome()+";");
            }
        }
        
        int sizeBST=result.getBST().size();
        int size2D=result.get2D().size();
        
        System.out.println("\nNúmero de países inseridos na BST = "+sizeBST);
        assertTrue("BST não contém 59 países", sizeBST==59);
        System.out.println("Número de países inseridos na 2DTree = "+size2D);
        assertTrue("2D não contém 59 países", size2D==59);
        
        PaisOrdemAlfabetica por=new PaisOrdemAlfabetica("portugal", "europa", (float) 10.31, "lisboa", 38.7071631, -9.135517);
        PaisOrdemAlfabetica den=new PaisOrdemAlfabetica("dinamarca", "europa", (float) 5.75, "copenhaga", 55.6762944, 12.5681157);
        PaisOrdemAlfabetica col=new PaisOrdemAlfabetica("colombia", "americasul", (float) 46.86, "bogota", 4.6097100, -74.0817500);
        PaisOrdemAlfabetica fin=new PaisOrdemAlfabetica("finlandia", "europa", (float) 5.5, "helsinque", 60.1698791, 24.9384078);
        PaisOrdemAlfabetica ita=new PaisOrdemAlfabetica("italia", "europa", (float) 60.59, "roma", 41.8954656, 12.4823243);
        PaisOrdemAlfabetica uru=new PaisOrdemAlfabetica("uruguai", "americasul", (float) 3.35, "montevideu", -34.9032800, -56.1881600);
        PaisOrdemAlfabetica chi=new PaisOrdemAlfabetica("chile", "americasul", (float) 16.80, "santiago", -33.4569400, -70.6482700);
        PaisOrdemAlfabetica ukr=new PaisOrdemAlfabetica("ucrania", "europa", (float) 42.59, "kiev", 50.440951, 30.5271814);
        PaisOrdemAlfabetica gfr=new PaisOrdemAlfabetica("guianafrancesa", "americasul", (float) 2.88, "caiena", 4.9333300, -52.3333300);
        PaisOrdemAlfabetica brs=new PaisOrdemAlfabetica("brasil", "americasul", (float) 206.12, "brasilia", -15.7797200, -47.9297200);
        PaisOrdemAlfabetica equ=new PaisOrdemAlfabetica("equador", "americasul", (float) 14.88, "quito", -0.2298500, -78.5249500);
        PaisOrdemAlfabetica per=new PaisOrdemAlfabetica("peru", "americasul", (float) 28.22, "lima", -12.0431800, -77.0282400);
        PaisOrdemAlfabetica ven=new PaisOrdemAlfabetica("venezuela", "americasul", (float) 31.02, "caracas", 10.4880100, -66.8791900);
        
        ArrayList<Pais> paisList=new ArrayList<>();
        paisList.add(por);
        paisList.add(den);
        paisList.add(fin);
        paisList.add(ita);
        paisList.add(ukr);
        paisList.add(col);
        paisList.add(uru);
        paisList.add(chi);
        paisList.add(gfr);
        paisList.add(brs);
        paisList.add(equ);
        paisList.add(per);
        paisList.add(ven);
        
        System.out.println("\n### Verificação de países (BST)###\n");
        for (Pais p : paisList) {
            boolean flag=false;
            System.out.format("Verificando a existência de %s como vértice.", p.getNome());
            for (int i=0;i<result.getBST().height();i++) {
                for (List<PaisOrdemAlfabetica> l : result.getBST().nodesByLevel().values()) {
                    if (l.contains(p)) {
                        flag=true;
                    }
                }
            }
            assertTrue("Mapa não contém "+p.getNome()+" como vértice.", flag);
            System.out.format(" (Verificado)%n");
        }
        
        System.out.println("\n### Verificação de países (2D)###\n");
        for (Pais p : paisList) {
            boolean flag=false;
            System.out.format("Verificando a existência de %s como vértice.", p.getNome());
            for (int i=0;i<result.get2D().height();i++) {
                for (List<PaisOrdemAlfabetica> l : result.get2D().nodesByLevel().values()) {
                    if (l.contains(p)) {
                        flag=true;
                    }
                }
            }
            assertTrue("Mapa não contém "+p.getNome()+" como vértice.", flag);
            System.out.format(" (Verificado)%n");
        }
        
        Set<PaisOrdemAlfabetica> colSet=new TreeSet<>();
        colSet.add(brs);
        colSet.add(equ);
        colSet.add(per);
        colSet.add(ven);
        LinkedList<Pais> i=result.findPais("colombia").getFronteiras();
        System.out.println("\n### Verificação de fronteiras ###\n");
        System.out.println("Colômbia:");
        for (Pais p : colSet) {
            System.out.format("    -%s", p.getNome());
            assertTrue("Colômbia não contém "+p.getNome()+" como vértice adjacente.", i.contains(p));
            System.out.format(" (Verficado)%n");
        }
    }
    
}
