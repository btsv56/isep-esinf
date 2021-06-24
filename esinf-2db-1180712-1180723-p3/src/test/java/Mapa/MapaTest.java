/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Modulo.Pais;
import Modulo.PaisOrdemAlfabetica;
import Modulo.PaisOrdemFronteirasPopulacao;
import Modulo.Reader;
import PL.BST;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
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
public class MapaTest {

    Mapa m;

    public MapaTest() throws FileNotFoundException {
        m = Reader.read();

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
     * Test of adicionarPais method, of class Mapa.
     */
    @Test
    public void testAdicionarPais() {
        System.out.println("------------BST------------\n");
        System.out.println(m.getBST());
        System.out.println("\n------------2DTree------------\n\n");
        System.out.println(m.get2D());
    }

    @Test
    public void testInsertRemove() {
        System.out.println("\n------------testInsert------------");
        PaisOrdemAlfabetica usa = new PaisOrdemAlfabetica("murica", "americanorte", 1200F, "washingtondc", 50, -100);
        m.adicionarPaisBST(usa);
        Pais result = m.findPais("murica");
        System.out.println(result);
        assertTrue(result == usa);
        m.getBST().remove(usa);
        result = m.findPais("murica");
        System.out.println(result);
        assertTrue(result == null);
    }

    @Test
    public void testListaFronteirasPais() {
        System.out.println("------------listaFronteirasPais------------\n");
        String pais = "espanha";
        LinkedList<Pais> expResult = new LinkedList<>();
        expResult.add(m.findPais("franca"));
        LinkedList<Pais> result = m.listaFronteirasPais(pais);
        System.out.println("Fronteiras de Espanha que contenham outras fronteiras:");
        for (Pais p : result) {
            System.out.println(" -" + p.getNome());
        }
        assertEquals("\nSo devia de conter espanha", result, expResult);
        expResult.clear();
        result = m.listaFronteirasPais("franca");
        expResult.add(m.findPais("alemanha"));
        expResult.add(m.findPais("belgica"));
        expResult.add(m.findPais("espanha"));
        expResult.add(m.findPais("italia"));
        expResult.add(m.findPais("luxemburgo"));
        expResult.add(m.findPais("suica"));
        System.out.println("\nFronteiras de França que contenham outras fronteiras:");
        for (Pais p : result) {
            System.out.println(" -" + p.getNome());
        }
        assertEquals("So devia de conter espanha, belgica, luxemburgo,alemanha, suica e italia", result,expResult);
        expResult.clear();
        expResult.add(m.findPais("espanha"));
        result = m.listaFronteirasPais("portugal");
        System.out.println("\nFronteiras de portugal que contenham outras fronteiras:");
        for (Pais p : result) {
            System.out.println(" -" + p.getNome());
        }
        assertEquals("Deve conter Espanha", result, expResult);
        expResult.clear();
        result = m.listaFronteirasPais("reinounido");
        System.out.println("\nFronteiras de Reino Unido que contenham outras fronteiras:");
        for (Pais p : result) {
            System.out.println(" -" + p.getNome());
        }
        assertEquals("Não deve conter paises nenhuns", result, expResult);
    }

    @Test
    public void testListaOrdenadaPaisesContinente() {
        System.out.println("------------listaOrdenadaPaisesContinente------------\n");
        String continente = "europa";
        LinkedList<Pais> result = m.listaOrdenadaPaisesContinente(continente);
        LinkedList<Pais> expResult = new LinkedList<>();
        int i=0;
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("alemanha")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("russia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("servia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("austria")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("hungria")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("polonia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("ucrania")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("franca")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("montenegro")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("macedonia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("croacia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("eslovaquia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("bulgaria")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("suica")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("bielorussia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("romenia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("kosovo")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("letonia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("eslovenia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("lituania")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("albania")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("republicacheca")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("grecia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("belgica")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("italia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("turquia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("luxemburgo")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("georgia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("bosnia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("noruega")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("finlandia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("liechtenstein")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("estonia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("armenia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("moldavia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("suecia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("holanda")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("espanha")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("monaco")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("irlanda")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("dinamarca")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("portugal")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("reinounido")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("islandia")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("malta")));
        expResult.add(new PaisOrdemFronteirasPopulacao(m.findPais("chipre")));
        for (Pais p : result) {
            System.out.println(p.getNome() + "\n    -Número de fronteiras: " + p.getNumFronteiras() + "\n    -População: " + p.getPopulacao() + "\n");
        }
        assertEquals(expResult, result);
    }

    /**
     * Test of pesquisaExata method, of class Mapa.
     */
    @Test
    public void testPesquisaExata() {
        System.out.println("\n------------pesquisaExata------------\n");
        System.out.println("40.4166909, -3.7003454 (Madrid)");
        double latitude = 40.4166909;
        double longitude = -3.7003454;
        String expResult = "espanha";
        Pais result = m.pesquisaExata(latitude, longitude);
        System.out.println(result);
        assertEquals(expResult, result.getNome());

        System.out.println("-15.7797200, 47.9297200 (Brasília)");
        latitude = -15.7797200;
        longitude = -47.9297200;
        expResult = "brasil";
        result = m.pesquisaExata(latitude, longitude);
        System.out.println(result);
        assertEquals(expResult, result.getNome());

        System.out.println("0, 0 (0)");
        latitude = 0;
        longitude = 0;
        expResult = null;
        result = m.pesquisaExata(latitude, longitude);
        System.out.println(result);
        assertEquals(expResult, result);
    }

    /**
     * Test of vizinhoMaisProximo method, of class Mapa.
     */
    @Test
    public void testVizinhoMaisProximo() {
        System.out.println("\n------------vizinhoMaisProximo------------");
        double latitude = 52.948;
        double longitude = 32.4481481;
        PaisOrdemAlfabetica result = m.vizinhoMaisProximo(latitude, longitude);
        Pais expResult= m.findPais("ucrania");
        for (PaisOrdemAlfabetica p : m.get2D().inOrder()) {
            if (!p.equals(result)) {
                assertTrue(Mapa2DTree.getDistance(p, latitude, longitude) > Mapa2DTree.getDistance(result, latitude, longitude));
            }
        }
        assertEquals("Devia ser ucrania",result,expResult);
        System.out.println("\nLatitude: " + latitude + "\nLongitude: " + longitude + "\n" + result + "Distancia: " + Mapa2DTree.getDistance(result, latitude, longitude) + "\n");
        latitude = -34.6131500F;
        longitude = -58.3772300F;
        result = m.vizinhoMaisProximo(latitude, longitude);
        for (PaisOrdemAlfabetica p : m.get2D().inOrder()) {
            if (!p.equals(result)) {
                assertTrue(Mapa2DTree.getDistance(p, latitude, longitude) > Mapa2DTree.getDistance(result, latitude, longitude));
            }
        }
        expResult=m.findPais("argentina");
        assertEquals("Devia ser Argentina",result,expResult);
        System.out.println("\nLatitude: " + latitude + "\nLongitude: " + longitude + "\n" + result + "Distancia: " + Mapa2DTree.getDistance(result, latitude, longitude) + "\n");
        latitude = 10.6131500F;
        longitude = 12.3772300F;
        result = m.vizinhoMaisProximo(latitude, longitude);
        for (PaisOrdemAlfabetica p : m.get2D().inOrder()) {
            if (!p.equals(result)) {
                assertTrue(Mapa2DTree.getDistance(p, latitude, longitude) > Mapa2DTree.getDistance(result, latitude, longitude));
            }
        }
        expResult= m.findPais("malta");
        assertEquals("Devia ser malta",result,expResult);
        System.out.println("\nLatitude: " + latitude + "\nLongitude: " + longitude + "\n" + result + "Distancia: " + Mapa2DTree.getDistance(result, latitude, longitude) + "\n");
        latitude = 80;
        longitude = 100;
        result = m.vizinhoMaisProximo(latitude, longitude);
        for (PaisOrdemAlfabetica p : m.get2D().inOrder()) {
            if (!p.equals(result)) {
                assertTrue(Mapa2DTree.getDistance(p, latitude, longitude) >= Mapa2DTree.getDistance(result, latitude, longitude));
            }
        }
        expResult=m.findPais("finlandia");
        assertEquals("Devia ser finlandia",result,expResult);
        System.out.println("\nLatitude: " + latitude + "\nLongitude: " + longitude + "\n" + result + "Distancia: " + Mapa2DTree.getDistance(result, latitude, longitude) + "\n");
        latitude = 30;
        longitude = 40;
        result = m.vizinhoMaisProximo(latitude, longitude);
        for (PaisOrdemAlfabetica p : m.get2D().inOrder()) {
            if (!p.equals(result)) {
                assertTrue(Mapa2DTree.getDistance(p, latitude, longitude) >= Mapa2DTree.getDistance(result, latitude, longitude));
            }
        }
        expResult=m.findPais("chipre");
        assertEquals("Devia ser chipre", result, expResult);
        System.out.println("\nLatitude: " + latitude + "\nLongitude: " + longitude + "\n" + result + "Distancia: " + Mapa2DTree.getDistance(result, latitude, longitude) + "\n");
    }

    @Test
    public void testPesquisaQuad() {
        System.out.println("\n------------testPesquisaQuad------------");
        ArrayList<PaisOrdemAlfabetica> listaPaises;
        ArrayList<String> expresult = new ArrayList<>();
        System.out.println("\n---------- 36.7071631, -10.135517 (Lisboa); 50.8566667, 3.3509871 (Paris) ----------");
        listaPaises = m.pesquisaQuad(36.7071631, -10.135517, 50.8566667, 3.3509871);
        expresult.add("portugal");
        expresult.add("franca");
        expresult.add("espanha");
        for (Pais p : listaPaises) {
            System.out.println(p);
            assertTrue(expresult.contains(p.getNome()));
        }
        expresult.clear();

        System.out.println("\n---------- -35, -59 (~Buenos Aires); -14, -45 (~Brasília) ----------");
        listaPaises = m.pesquisaQuad(-35, -59, -14, -45);
        expresult.add("brasil");
        expresult.add("argentina");
        expresult.add("paraguai");
        expresult.add("uruguai");
        for (Pais p : listaPaises) {
            System.out.println(p);
            assertTrue(expresult.contains(p.getNome()));
        }
        expresult.clear();

        System.out.println("\n---------- -100, -100; 100, 100 (Mapa inteiro) ----------");
        listaPaises = m.pesquisaQuad(-100, -100, 100, 100);
        for (Pais p : listaPaises) {
            System.out.println(p);
        }
        System.out.println(listaPaises.size());
        assertTrue(listaPaises.size() == 59);

        System.out.println("\n----------- 46.9479986, 7.4481481 (Berna); 52.2296756, 21.0122287 (Varsóvia) ----------");
        listaPaises = m.pesquisaQuad(46.9479986, 7.4481481, 52.2296756, 21.0122287);
        expresult.add("suica");
        expresult.add("austria");
        expresult.add("polonia");
        expresult.add("liechtenstein");
        expresult.add("hungria");
        expresult.add("republicacheca");
        expresult.add("eslovaquia");
        for (Pais p : listaPaises) {
            System.out.println(p);
            assertTrue(expresult.contains(p.getNome()));
        }
        expresult.clear();

        System.out.println("\n----------- 60, -25; 70, -20 (~Islândia) ----------");
        listaPaises = m.pesquisaQuad(60, -25, 70, -20);
        expresult.add("islandia");
        for (Pais p : listaPaises) {
            System.out.println(p);
            assertTrue(expresult.contains(p.getNome()));
        }
        expresult.clear();

        System.out.println("\n----------- 32, -7; 33, -8 (Perto de Lisboa sem intersecção) ----------");
        listaPaises = m.pesquisaQuad(32, -7, 33, -8);
        for (Pais p : listaPaises) {
            System.out.println(p);
        }
        assertTrue(listaPaises.size() == 0);
    }

    /**
     * Test of findPais method, of class Mapa.
     */
    @Test
    public void testFindPais() {
        System.out.println("\n----------- findPais ----------");
        String pais = "portugal";
        PaisOrdemAlfabetica expResult = new PaisOrdemAlfabetica("portugal", "europa", 10.31F, "lisboa", 38.7071631, -9.135517);
        Pais result = m.findPais(pais);
        assertEquals(expResult, result);
        System.out.print("Obtido: "+result+"Esperado: "+expResult);
    }
}
