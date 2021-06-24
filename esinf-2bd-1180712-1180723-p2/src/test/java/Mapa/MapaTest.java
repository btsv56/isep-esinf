/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Modulo.Pais;
import Modulo.Reader;
import graphbase.AdjacencyMatrixStructure.AdjacencyMatrixGraph;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
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
public class MapaTest {

    Mapa worldMap;
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

    public MapaTest() throws FileNotFoundException {
        worldMap = Modulo.Reader.read();
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
        System.out.println("\n------Teste adicionarPais------\n\nDeve ter os paises China e Russia");
        Pais pais1 = new Pais("China", "Asia", 123.1F, "Pequim", 39.916668F, 116.383331F);
        Pais pais2 = new Pais("Russia", "Asia", 321.31F, "Moscovo", 116.383331F, 37.618423F);
        Mapa m = new Mapa();
        m.adicionarPais(pais1);
        m.adicionarPais(pais2);
        for (Pais p : m.getPaises()) {
            System.out.println(p);
        }
    }

    /**
     * Test of adicionarFronteira method, of class Mapa.
     */
    @Test
    public void testAdicionarFronteira() {
        System.out.println("\n------Teste AdicionarFronteira------\n\nDeve incluir os paises Russia e China, que são fronteira um do outro");
        Pais pais1 = new Pais("China", "Asia", 123.1F, "Pequim", 39.916668F, 116.383331F);
        Pais pais2 = new Pais("Russia", "Asia", 321.31F, "Moscovo", 116.383331F, 37.618423F);
        double distancia = 5793.0913;
        Mapa m = new Mapa();
        m.adicionarPais(pais1);
        m.adicionarPais(pais2);
        m.adicionarFronteira(pais1, pais2, distancia);
        for (Pais p : m.getPaises()) {
            System.out.println("País: " + p.getNome() + "\nFronteiras: ");
            for (Pais p2 : m.getFronteirasDePais(p)) {
                System.out.print(p2.getNome() + " ");
            }
            System.out.print("\n\n");
        }
    }

    /**
     * Test of colorirMapa method, of class Mapa.
     */
    @Test
    public void testColorirMapa() {
        System.out.println("\n------Teste colorirMapa------\n");
        int result = worldMap.colorirMapa();
        int expResult = 4;
        for (Pais p : worldMap.getPaises()) {
            System.out.print("País: " + p.getNome() + " Cor: " + p.getCor() + "\nFronteira: ");
            for (Pais p2 : worldMap.getFronteirasDePais(p)) {
                System.out.print(p2.getNome() + "  Cor: " + p2.getCor() + " | ");
                assertTrue("Paises ligados deviam ter cores diferentes",p.getCor()!=p2.getCor());
            }
            System.out.print("\n-------------------------------\n");
        }
        System.out.print("Numero de cores usadas:"+result+"\n-------------------------------\n\n");
        assertEquals("O numero de cores usadas devia de ser 4:", expResult, result);
    }

    /**
     * Test of caminhoMaisCurtoNPontos, of class Mapa
     */
    @Test
    public void testCaminhoMaisCurtoNPontos() {
        System.out.println("\n------caminhoMaisCurtoNPontos------\n");

        ArrayList<String> pontos = new ArrayList<>();
        double expresult;
        double result;
        LinkedList<Pais> caminho = new LinkedList<>();

        System.out.println("Portugal - Alemanha");
        pontos.add("lisboa");
        pontos.add("berlim");
        result = worldMap.caminhoMaisCurtoNPontos(pontos, caminho);
        System.out.format("Distância = %.2f kms%n", result);
        for (Pais p : caminho) {
            System.out.println("    -" + p.getNome());
        }
        expresult = 2430F;
        assertEquals(result, expresult, 20);
        System.out.println("Verificado.");

        System.out.println("\nPortugal - Alemanha - Grécia");
        caminho.clear();
        pontos.clear();
        pontos.add("lisboa");
        pontos.add("berlim");
        pontos.add("atenas");
        result = worldMap.caminhoMaisCurtoNPontos(pontos, caminho);
        System.out.format("Distância = %.2f kms%n", result);
        for (Pais p : caminho) {
            System.out.println("    -" + p.getNome());
        }
        expresult = 4282F;
        assertEquals(result, expresult, 20);
        System.out.println("Verificado.");

        System.out.println("\nPortugal - Alemanha - Suécia - Grécia");
        caminho.clear();
        pontos.clear();
        pontos.add("lisboa");
        pontos.add("berlim");
        pontos.add("estocolmo");
        pontos.add("atenas");
        result = worldMap.caminhoMaisCurtoNPontos(pontos, caminho);
        System.out.format("Distância = %.2f kms%n", result);
        for (Pais p : caminho) {
            System.out.println("    -" + p.getNome());
        }
        expresult = 8998F;
        assertEquals(result, expresult, 20);
        System.out.println("Verificado.");
        
        System.out.println("\nPortugal - Alemanha - Suécia - Grécia (Fora de ordem)");
        caminho.clear();
        pontos.clear();
        pontos.add("lisboa"); pontos.add("estocolmo"); pontos.add("berlim"); pontos.add("atenas");
        result=worldMap.caminhoMaisCurtoNPontos(pontos, caminho);
        System.out.format("Distância = %.2f kms%n",result);
        for (Pais p : caminho) {
            System.out.println("    -"+p.getNome());
        }
        expresult=8998F;
        assertEquals(result, expresult, 20);
        System.out.println("Verificado.");
        
        System.out.println("\nPortugal - Espanha - França - Alemanha");
        caminho.clear();
        pontos.clear();
        pontos.add("lisboa");
        pontos.add("madrid");
        pontos.add("paris");
        pontos.add("berlim");
        result = worldMap.caminhoMaisCurtoNPontos(pontos, caminho);
        System.out.format("Distância = %.2f kms%n", result);
        for (Pais p : caminho) {
            System.out.println("    -" + p.getNome());
        }
        expresult = 2430F;
        assertEquals(result, expresult, 20);
        System.out.println("Verificado.");
        
        System.out.println("\nEspanha - Suiça - Rússia - Áustria");
        caminho.clear();
        pontos.clear();
        pontos.add("madrid");
        pontos.add("berna");
        pontos.add("moscovo");
        pontos.add("viena");
        result = worldMap.caminhoMaisCurtoNPontos(pontos, caminho);
        System.out.format("Distância = %.2f kms%n", result);
        for (Pais p : caminho) {
            System.out.println("    -" + p.getNome());
        }
        expresult = 5646F;
        assertEquals(result, expresult, 20);
        System.out.println("Verificado.");
        
        System.out.println("\nEspanha - Rússia - Suiça - Áustria (Fora de ordem)");
        caminho.clear();
        pontos.clear();
        pontos.add("madrid");
        pontos.add("moscovo");
        pontos.add("berna");
        pontos.add("viena");
        result = worldMap.caminhoMaisCurtoNPontos(pontos, caminho);
        System.out.format("Distância = %.2f kms%n", result);
        for (Pais p : caminho) {
            System.out.println("    -" + p.getNome());
        }
        expresult = 5646F;
        assertEquals(result, expresult, 20);
        System.out.println("Verificado.");

        System.out.println("\nPortugal - Portugal - Itália");
        caminho.clear();
        pontos.clear();
        pontos.add("lisboa");
        pontos.add("lisboa");
        pontos.add("roma");
        result = worldMap.caminhoMaisCurtoNPontos(pontos, caminho);
        System.out.format("Distância = %.2f kms%n", result);
        for (Pais p : caminho) {
            System.out.println("    -" + p.getNome());
        }
        expresult = 2677F;
        assertEquals(result, expresult, 20);
        System.out.println("Verificado.");
        
        System.out.println("\nPortugal - Itália - Portugal");
        caminho.clear();
        pontos.clear();
        pontos.add("lisboa");
        pontos.add("roma");
        pontos.add("lisboa");
        result = worldMap.caminhoMaisCurtoNPontos(pontos, caminho);
        System.out.format("Distância = %.2f kms%n", result);
        for (Pais p : caminho) {
            System.out.println("    -" + p.getNome());
        }
        expresult = 5323F;
        assertEquals(result, expresult, 20);
        System.out.println("Verificado.");

        System.out.println("\nPortugal");
        caminho.clear();
        pontos.clear();
        pontos.add("lisboa");
        result = worldMap.caminhoMaisCurtoNPontos(pontos, caminho);
        System.out.format("Distância = %.2f kms%n", result);
        for (Pais p : caminho) {
            System.out.println("    -" + p.getNome());
        }
        expresult = 0F;
        assertEquals(result, expresult, 0);
        System.out.println("Verificado.");

        System.out.println("\nPortugal - China (null)");
        caminho.clear();
        pontos.clear();
        pontos.add("lisboa");
        pontos.add("pequim");
        result = worldMap.caminhoMaisCurtoNPontos(pontos, caminho);
        assertTrue("Result devia ser null.", result == -1);
        System.out.println("Verificado.");

        System.out.println("\nPortugal - Brasil (null)");
        caminho.clear();
        pontos.clear();
        pontos.add("lisboa");
        pontos.add("brasilia");
        result = worldMap.caminhoMaisCurtoNPontos(pontos, caminho);
        assertTrue("Result devia ser null.", result == -1);
        System.out.println("Verificado.");
        
        System.out.println("\nPortugal - Chipre");
        caminho.clear();
        pontos.clear();
        pontos.add("lisboa");
        pontos.add("madrid");
        pontos.add("londres");
        result = worldMap.caminhoMaisCurtoNPontos(pontos, caminho);
        System.out.format("Distância = %.2f kms%n", result);
        for (Pais p : caminho) {
            System.out.println("    -" + p.getNome());
        }
        expresult = -1F;
        assertEquals(result, expresult, 0);
        System.out.println("Verificado.\n");
    }

    /**
     * Test of shortestPath method, of class Mapa.
     */
    @Test
    public void testShortestPath() {
        System.out.println("\n------Teste shortestPath------\n\nCaminho: Lisboa - Berlim");
        LinkedList<String> capitais = new LinkedList<>();
        double expResult = 2433.92;
        double result = worldMap.shortestPath(capitais, "lisboa", "berlim");
        for (String s : capitais) {
            System.out.println("    -" + s);
        }
        LinkedList<String> expCapitais= new LinkedList<>();
        expCapitais.add("lisboa");
        expCapitais.add("madrid");
        expCapitais.add("paris");
        expCapitais.add("berlim");
        assertEquals(expCapitais, capitais);
        System.out.println("Distancia: " + result + "\n");
        assertEquals("O caminho devia de ser Lisboa-Madrid-Paris-Berlim com uma distancia de " + expResult, expResult, result, 0.01);

        expResult = 0;
        capitais.clear();
        result = worldMap.shortestPath(capitais, "lisboa", "lisboa");
        System.out.println("Caminho: Lisboa-Lisboa");
        for (String s : capitais) {
            System.out.println("    -" + s);
        }
        expCapitais.clear();
        expCapitais.add("lisboa");
        assertEquals(expCapitais,capitais);
        System.out.println("Distancia: " + result + "\n");
        assertEquals("O caminho devia de ser Lisboa distancia de " + expResult, expResult, result, 0.01);

        expResult = -1;
        capitais.clear();
        result = worldMap.shortestPath(capitais, "lisboa", null);
        System.out.println("Caminho Lisboa-null:");
        for (String s : capitais) {
            System.out.println("    -" + s);
        }
        System.out.println("Distancia: " + result + "\n");
        assertEquals("O caminho devia de ser null com uma distancia de " + expResult, expResult, result, 0.01);

    }

    /**
     * Test of graphToAdjMatrix method, of class GraphAlgorithms.
     */
    @Test
    public void testGetAdjMatrixGraph() {
        System.out.println("\n------getAdjMatrixGraph------\n");
        AdjacencyMatrixGraph result = worldMap.getAdjMatrixGraph();
        System.out.println(result.toString());
    }

    /**
     * Test of floydWarshall method, of class Mapa.
     */
    @Test
    public void testFloydWarshall() {
        System.out.println("\n------floydWarshall------\n");
        AdjacencyMatrixGraph adj = worldMap.getAdjMatrixGraph();
        double[][] fwresult = worldMap.floydWarshall(adj);
        
        Pais pais1=worldMap.getPaisByCapital("lisboa");
        Pais pais2=worldMap.getPaisByCapital("berlim");
        double result=fwresult[worldMap.getPosPais(pais1)][worldMap.getPosPais(pais2)];
        double expresult=2430D;
        System.out.println("Distância de Lisboa a Berlim = "+result);
        assertEquals(result, expresult, 10);
        
        pais1=worldMap.getPaisByCapital("berlim");
        pais2=worldMap.getPaisByCapital("lisboa");
        result=fwresult[worldMap.getPosPais(pais1)][worldMap.getPosPais(pais2)];
        expresult=2430D;
        System.out.println("Distância de Berlim a Lisboa = "+result);
        assertEquals(result, expresult, 10);

        pais1=worldMap.getPaisByCapital("lisboa");
        pais2=worldMap.getPaisByCapital("lisboa");
        result=fwresult[worldMap.getPosPais(pais1)][worldMap.getPosPais(pais2)];
        expresult=0D;
        System.out.println("Distância de Lisboa a Lisboa = "+result);
        assertEquals(result, expresult, 0);
        
        pais1=worldMap.getPaisByCapital("lisboa");
        pais2=worldMap.getPaisByCapital("caracas");
        result=fwresult[worldMap.getPosPais(pais1)][worldMap.getPosPais(pais2)];
        expresult=999999D;
        System.out.println("Distância de Lisboa a Colômbia = "+result+"\n");
        assertEquals(result, expresult, 0);
    }

    /**
     * Test of cirucuitoCurtoHeuristicaVizinhoMCurto method, of class Mapa.
     */
    @Test
    public void testCircuitoCurtoHeuristicaVizinhoMProx() {
        System.out.println("\n-------Circuito Mais Curto Heuristica Vizinho Mais Proximo--------\n");
        LinkedList<String> capitaisVisitadas = new LinkedList<>();
        int result;
        int expResult=17;
        result = worldMap.numeroMaximoCidadesHeuristicaVizinhoMProx(capitaisVisitadas);
        System.out.print("Caminho com mais capitais visitadas: ");
        for(String s: capitaisVisitadas) {
            System.out.print(s+" ");
        }
        LinkedList<String> expCapitaisVisitadas= new LinkedList<>();
        expCapitaisVisitadas.add("tallinn");
        expCapitaisVisitadas.add("riga");
        expCapitaisVisitadas.add("vilnius");
        expCapitaisVisitadas.add("minsk"); 
        expCapitaisVisitadas.add("kiev");
        expCapitaisVisitadas.add("chisinau");
        expCapitaisVisitadas.add("bucareste");
        expCapitaisVisitadas.add("sofia");
        expCapitaisVisitadas.add("escopia");
        expCapitaisVisitadas.add("pristina");
        expCapitaisVisitadas.add("podgorica");
        expCapitaisVisitadas.add("tirana");
        expCapitaisVisitadas.add("atenas");
        expCapitaisVisitadas.add("ancara");
        expCapitaisVisitadas.add("erevan");
        expCapitaisVisitadas.add("tbilisi");
        expCapitaisVisitadas.add("moscovo");
        expCapitaisVisitadas.add("tallinn");
        assertEquals(expCapitaisVisitadas, capitaisVisitadas);
        System.out.print("\nNúmero máximo capitais visitadas: "+result+"\n\n");
        assertEquals("O numero maximo de capitais visitadas devia de ser 17",expResult,result);
    }

    /**
     * Test of paisbyKey method, of class Mapa.
     */
    @Test
    public void testPaisbyKey() {
        System.out.println("\n------paisbyKey------\n");
        int key = 0;
        Pais expResult = new Pais("argentina", "americasul", 41.67F, "buenosaires", -34.6131500F, -58.3772300F);
        Pais result = worldMap.paisbyKey(key);
        System.out.println("Pais esperado:\n"+expResult+"\nPais obtido:\n"+result);
        assertEquals("O país esperado era Argentina",expResult, result);
    }

    /**
     * Test of getPosPais method, of class Mapa.
     */
    @Test
    public void testGetPosPais() {
        System.out.println("\n------getPosPais------\n");
        Pais p = new Pais("argentina", "americasul", 41.67F, "buenosaires", -34.6131500F, -58.3772300F);
        int expResult = 0;
        int result = worldMap.getPosPais(p);
        System.out.println("Pais:" +p);
        System.out.println("Posição esperada: "+expResult+"\nPosição obtida: "+result+"\n");
        assertEquals("A posição obtida devia ser 1",expResult, result);
    }
    
    /**
     * Test of getPaisByCapital method, of class Mapa.
     */
    @Test
    public void testGetPaisByCapital() {
        System.out.println("\n------getPaisByCapital------\n");
        String capital = "buenosaires";
        Pais expResult = new Pais("argentina", "americasul", 41.67F, "buenosaires", -34.6131500F, -58.3772300F);
        Pais result = worldMap.getPaisByCapital(capital);
        System.out.println("Capital: "+capital+"\nPais obtido: "+expResult+"\n");
        System.out.println("----------------------------------------------------");
        assertEquals(expResult, result);
    }

    /**
     * Test of getPaises method, of class Mapa.
     */
    @Test
    public void testGetPaises() {
        System.out.println("\n------getPaises------\n");
        ArrayList<Pais> result=worldMap.getPaises();
        
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
        
        int size=59;
        System.out.println("Número de países: "+result.size());
        assertTrue("Nem todos os países foram adicionados.", size==result.size());
        for (Pais p : paisList) {
            assertTrue("Resultado não tem "+p.getNome(),result.contains(p));
        }
        System.out.println();
    }

    /**
     * Test of getFronteirasDePais method, of class Mapa.
     */
    @Test
    public void testGetFronteirasDePais() {
        System.out.println("\n------getFronteirasDePais------\n");
        Set<Pais> result=worldMap.getFronteirasDePais(col);
        Set<Pais> expresult=new TreeSet<>();
        expresult.add(brs);
        expresult.add(equ);
        expresult.add(per);
        expresult.add(ven);
        System.out.println("Colômbia:");
        for (Pais p : expresult) {
            System.out.format("    -%s", p.getNome());
            assertTrue("Colômbia não contém "+p.getNome()+" como vértice adjacente.", result.contains(p));
            System.out.format(" (Verficado)%n");
        }
        System.out.println();
    }

    /**
     * Test of getPaisesInContinente method, of class Mapa.
     */
    @Test
    public void testGetPaisesInContinente() {
        System.out.println("\n------getPaisesInContinente------\n");
        String cont="europa";
        ArrayList<Pais> result = worldMap.getPaisesInContinente(cont);
        System.out.println("Continente: "+cont);
        for (Pais p : result) {
            System.out.format("    -%s", p.getNome());
            assertTrue("País não pertence à "+cont, p.getContinente().equals(cont));
            System.out.format(" (Verficado)%n");
        }
        System.out.println();
    }

}
