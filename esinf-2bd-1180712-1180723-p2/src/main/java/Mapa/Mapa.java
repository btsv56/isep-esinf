/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Modulo.Pais;
import graphbase.AdjacencyMatrixStructure.AdjacencyMatrixGraph;
import graphbase.Edge;
import graphbase.Graph;
import graphbase.GraphAlgorithms;
import static graphbase.GraphAlgorithms.shortestPath;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.LinkedList;
import java.util.Set;
import java.util.TreeSet;
import sun.security.provider.certpath.AdjacencyList;

/**
 *
 * @author Utilizador
 */
public class Mapa{

    private Graph<Pais, Integer> worldMap;

    /**
     * Construtor. Inicializa um grafo não orientado.
     */
    public Mapa() {
        this.worldMap = new Graph(false);
    }
    
    /**
     * Adiciona um Pais ao grafo como Vertex.
     * @param pais Objeto Pais
     */
    public void adicionarPais(Pais pais) {
        worldMap.insertVertex(pais);
    }

    /**
     * Adiciona uma Edge ao grafo.
     * @param pais1 Pais origem
     * @param pais2 Pais destino
     * @param distancia Distancia (em km)
     */
    public void adicionarFronteira(Pais pais1, Pais pais2, double distancia) {
        worldMap.insertEdge(pais1, pais2, 0, distancia);
    }

    /**
     * Retorna um Pais pela posição no grafo.
     * @param key int
     * @return Pais
     */
    public Pais paisbyKey(int key) {
        if (key > worldMap.numVertices()) {
            return null;
        }
        Pais[] nomePais = worldMap.allkeyVerts();
        return nomePais[key];
    }

    /**
     * Retorna a posição de um Pais no grafo.
     * @param p Pais
     * @return int
     */
    public int getPosPais(Pais p) {
        return worldMap.getKey(p);
    }
    
    /**
     * Retorna uma ArrayList com todos os Paises.
     * @return ArrayList de Pais
     */
    public ArrayList<Pais> getPaises() {
        Pais[] paisVec = worldMap.allkeyVerts();
        ArrayList<Pais> paisArray = new ArrayList<>();
        paisArray.addAll(Arrays.asList(paisVec));
        return paisArray;
    }

    /**
     * Retorna um Set dos Paises que fazem fronteira com um Pais especificado.
     * @param p Pais origem
     * @return Set de Pais
     */
    public Set<Pais> getFronteirasDePais(Pais p) {
        Set<Pais> set = new TreeSet<>();
        Iterable<Pais> i = worldMap.adjVertices(p);
        for (Pais p1 : i) {
            set.add(p1);
        }
        return set;
    }
    
     /**
     * Retorna um Pais pela capital
     * @param capital String
     * @return Pais
     */
    public Pais getPaisByCapital(String capital) {
        ArrayList<Pais> paisList = getPaises();
        for (Pais p : paisList) {
            if (p.getCapital().equals(capital)) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Retorna os Paises dum continente especificado.
     * @param continente String
     * @return ArrayList de Pais
     */
    public ArrayList<Pais> getPaisesInContinente(String continente) {
        Pais[] paisVec = worldMap.allkeyVerts();
        ArrayList<Pais> paisArray = new ArrayList<>();
        paisArray.addAll(Arrays.asList(paisVec));
        ArrayList<Pais> continenteArray = new ArrayList<>();
        for (Pais p : paisArray) {
            if (p.getContinente().equals(continente)) {
                continenteArray.add(p);
            }
        }
        return continenteArray;
    }

    /**
     * Ordena uma LinkedList pelo número de fronteiras.
     * @param listaPaises LinkedList de Pais
     */
    private void getListaOrdenadaPaises(LinkedList<Pais> listaPaises) {
        for (int i=0;i<listaPaises.size()-1;i++) {
            for (int j=i+1;j<listaPaises.size();j++) {
                Pais pais1=listaPaises.get(i);
                Pais pais2=listaPaises.get(j);
                if (worldMap.outDegree(pais1)<worldMap.outDegree(pais2)) {
                    listaPaises.set(i, pais2);
                    listaPaises.set(j, pais1);
                }
            }
        }
    }

    /**
     * Atribui uma cor a cada um dos países do mapa tendo em atenção que dois paises conetados não podem ter a mesma cor
     * e usando o menor numero de cores possiveis
     * @param listaPaises LinkedList de Paises do mapa
     * @param corPaises int[] - cores atribuidas a cada pais
     * @param cores boolean[] - array de cores que podem ser utilizadas
     */
    private void colorirMapa(LinkedList<Pais> listaPaises, int[] corPaises, boolean[] cores) {
        for (Pais p : listaPaises) {
            for (Pais pAdj : worldMap.adjVertices(p)) {
                if (corPaises[worldMap.getKey(pAdj)] != -1) {
                    cores[corPaises[worldMap.getKey(pAdj)]] = true;
                }
            }
            int i = 0;
            while (i < cores.length) {
                if (!cores[i]) {
                    corPaises[worldMap.getKey(p)] = i;
                    i = cores.length;
                }
                i++;
            }
            Arrays.fill(cores, false);
        }
    }

    /**
     * Atribui uma cor a cada um dos países do mapa tendo em atenção que dois paises conetados não podem ter a mesma cor
     * e usando o menor numero de cores possiveis
     * @return int max+1
     */
    public int colorirMapa() {
        int corPaises[] = new int[worldMap.allkeyVerts().length];
        boolean[] cores = new boolean[worldMap.allkeyVerts().length];
        Arrays.fill(corPaises, -1);
        LinkedList<Pais> listaPaises = new LinkedList<>();
        for (Pais p : worldMap.allkeyVerts()) {
            listaPaises.add(p);
        }
        getListaOrdenadaPaises(listaPaises);
        colorirMapa(listaPaises, corPaises, cores);
        int max = -1;
        for (int i = 0; i < corPaises.length; i++) {
            paisbyKey(i).setCor(corPaises[i]);
            if (corPaises[i] > max) {
                max = corPaises[i];
            }
        }
        return max + 1;
    }

    /**
     * Caminho mais curto entre dois Paises, usando o algoritmo de Djikstra.
     * @param capitais LinkedList de capitais
     * @param capital1 String capital do Pais origem
     * @param capital2 String capital do Pais destino
     * @return 
     */
    public double shortestPath(LinkedList<String> capitais, String capital1, String capital2) {
        LinkedList<Pais> shortPath = new LinkedList<>();
        Pais p1 = getPaisByCapital(capital1);
        Pais p2 = getPaisByCapital(capital2);
        if (p1 == null || p2 == null) {
            return -1;
        }
        double dist = GraphAlgorithms.shortestPath(worldMap, p1, p2, shortPath);
        for (Pais p : shortPath) {
            capitais.add(p.getCapital());
        }
        return dist;
    }

    /**
     * Retorna a distância do caminho mais curto entre dois Paises que passa por outros Paises especificados, usando o algoritmo de Djikstra e Floyd-Warshall.
     * @param capitais ArrayList de capitais, a primeira sendo origem, a final o destino, e as outras as capitais onde tem de passar-se
     * @param caminho LinkedList de Pais, o caminho
     * @return double distancia
     */
    public double caminhoMaisCurtoNPontos(ArrayList<String> capitais, LinkedList<Pais> caminho) {
        LinkedList<Pais> camRef=new LinkedList<>();
        double[][] trnsClo=floydWarshall(getAdjMatrixGraph());
        double dist=0;
        ArrayList<Pais> paisArray=getPaisesInContinente(getPaisByCapital(capitais.get(0)).getContinente());
        if (capitais.size()>0) {
            for (int i=0;i<capitais.size();i++) {
                if (!paisArray.contains(getPaisByCapital(capitais.get(i)))) {
                    return -1D;
                }
            }
        } else return -1D;
        int posF=worldMap.getKey(getPaisByCapital(capitais.get(capitais.size()-1)));
        for (int i=0;i<capitais.size()-1;i++) {
            Pais p=getPaisByCapital(capitais.get(i));
            if (trnsClo[worldMap.getKey(p)][posF]==999999) return -1D;
        }
        String temp;
        for (int i=1;i<capitais.size()-2;i++) {
            int pos1=worldMap.getKey(getPaisByCapital(capitais.get(i)));
            for (int j=i+1;j<capitais.size()-1;j++) {
                int pos2=worldMap.getKey(getPaisByCapital(capitais.get(j)));
                if (trnsClo[worldMap.getKey(getPaisByCapital(capitais.get(0)))][pos1]>trnsClo[worldMap.getKey(getPaisByCapital(capitais.get(0)))][pos2]) {
                    temp=capitais.get(i);
                    capitais.set(i,capitais.get(j));
                    capitais.set(j, temp);
                }
            }
        }
        LinkedList<Pais> ll2=new LinkedList<>();
        if (capitais.size()==1) {
            caminho.add(getPaisByCapital(capitais.get(0)));
        } else {
            for (int i = 0; i < capitais.size() - 1; i++) {
                dist += GraphAlgorithms.shortestPath(worldMap, getPaisByCapital(capitais.get(i)), getPaisByCapital(capitais.get(i + 1)), ll2);
                if (i != 0) {
                    ll2.removeFirst();
                }
                caminho.addAll(ll2);
                ll2.clear();
            }
        }
        return dist;
    }
    
     /**
     * Transforma um grafo numa matriz de adjacências
     * @return AdjacencyMatrixGraph amg
     */
    public AdjacencyMatrixGraph<Pais, Double> getAdjMatrixGraph() {
        AdjacencyMatrixGraph<Pais, Double> amg = new AdjacencyMatrixGraph<>(worldMap.numVertices());
        for (Pais vert : worldMap.allkeyVerts()) {
            amg.insertVertex(vert);
        }
        for(Edge<Pais,Integer> e: worldMap.edges()) {
            amg.insertEdge(e.getVOrig(), e.getVDest(),e.getWeight());
        }
        return amg;
    }

    /**
     * Transforma uma matriz de adjacência no seu fecho transitivo, usando o algoritmo Floyd-Warshall.
     *
     * @param matrix AdjacencyMatrixGraph matrix
     * @return double[][] dist
     */
    double[][] floydWarshall(AdjacencyMatrixGraph matrix) {
        int V = matrix.numVertices();
        double dist[][] = new double[V][V];
        int i, j, k;

        for (i = 0; i < V; i++) {
            for (j = 0; j < V; j++) {
                if (matrix.privateGet(i, j) == null) {
                    if (i==j) {
                        dist[i][j] = 0;
                    } else {
                        dist[i][j] = 999999;
                    }
                } else {
                    dist[i][j] = (double) matrix.privateGet(i, j);
                }
            }
        }
        for (k = 0; k < V; k++) {
            for (i = 0; i < V; i++) {
                for (j = 0; j < V; j++) {
                    if ((dist[i][k] + dist[k][j] < dist[i][j]) && (i!=k && i!=j)) {
                        double distancia = dist[i][k] + dist[k][j];
                        BigDecimal rounder = new BigDecimal(distancia).setScale(2, RoundingMode.HALF_UP);
                        dist[i][j] = rounder.doubleValue();
                    }
                }
            }
        }
        return dist;
    }
    
    /**
     * Calcula e apresenta o circuito de menor comprimento começando em diferentes capitais, utilizando a heuristica do vizinho mais proximo
     * @param capitalOrig String capital origem
     * @param capitaisVisitadas LinkedList de String, capitais visitadas
     * @param mapaAdj matriz de adjacencias
     * @return int numPaises
     */
    private int circuitoHeuristicaVizinhoMProx(String capitalOrig, LinkedList<String> capitaisVisitadas,AdjacencyMatrixGraph<Pais, Double> mapaAdj,boolean[] visited) {
        Pais paisBase = getPaisByCapital(capitalOrig);
        Pais paisOrig = paisBase;
        Pais paisDest = null;
        int numPaises = 0;
        if (paisBase == null) {
            return -1;
        }
        if(mapaAdj.outDegree(paisOrig)<=0 ) {
            return 1;
        }
        capitaisVisitadas.add(capitalOrig);
        double min;
        while (paisDest != paisBase) {
            paisDest=null;
            min = Double.MAX_VALUE;
            for (Pais p : mapaAdj.directConnections(paisOrig)) {
                if (mapaAdj.getEdge(paisOrig, p) < min && !visited[worldMap.getKey(p)]) {
                    min = mapaAdj.getEdge(paisOrig, p);
                    paisDest = p;
                }
            }
            if(paisDest==null) {
                capitaisVisitadas.clear();
                return -1;
            }
            capitaisVisitadas.add(paisDest.getCapital());
            numPaises++;
            if (paisOrig != paisBase) {
                visited[worldMap.getKey(paisOrig)]=true;
            }
            paisOrig = paisDest;
        }
        return numPaises;
    }

    /**
     * Calcula e apresenta o circuito de menor comprimento começando em diferentes capitais, utilizando a heuristica do vizinho mais proximo
     * e apresentando o maior numero de capitais que é possivel percorrer utilizando esta heuristica 
     * @param capitaisMax
     * @return int max 
     */
    public int numeroMaximoCidadesHeuristicaVizinhoMProx(LinkedList<String> capitaisMax) {
        int max = Integer.MIN_VALUE;
        int num;
        LinkedList<String> capitaisVisitadas = new LinkedList<>();
        AdjacencyMatrixGraph<Pais,Double> adjMap= getAdjMatrixGraph();
        boolean[] visited=new boolean[adjMap.numVertices];
        for (Pais p : worldMap.allkeyVerts()) {
            capitaisVisitadas.clear();
            Arrays.fill(visited, false);
            num=circuitoHeuristicaVizinhoMProx(p.getCapital(), capitaisVisitadas, adjMap,visited);
            if (num > max) {
                capitaisMax.clear();
                max=num;
                for(String s: capitaisVisitadas) {
                    capitaisMax.add(s);
                }
            }
        }
        return max;
    }
}
