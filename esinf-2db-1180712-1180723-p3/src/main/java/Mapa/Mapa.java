/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Modulo.Pais;
import Modulo.PaisOrdemAlfabetica;
import Modulo.PaisOrdemFronteirasPopulacao;
import PL.*;
import com.sun.scenario.effect.impl.Renderer;
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
import javafx.scene.Node;
import sun.security.provider.certpath.AdjacencyList;

/**
 *
 * @author Utilizador
 */
public class Mapa {

    private BST_AUX mbst;
    private Mapa2DTree m2d;

    /**
     * Construtor. Inicializa um grafo não orientado.
     */
    public Mapa() {
        this.mbst = new BST_AUX();
        this.m2d = new Mapa2DTree();
    }

    /**
     * Adiciona um Pais à BST
     *
     * @param pais Objeto Pais
     */
    public void adicionarPaisBST(PaisOrdemAlfabetica pais) {
        mbst.insert(pais);
    }

    /**
     * Adiciona um Pais à BST
     *
     * @param pais Objeto Pais
     */
    public void adicionarPais2D(PaisOrdemAlfabetica pais) {
        m2d.insert(pais);
    }

    /**
     * Retorna a arvore BST
     * 
     * @return arvore BST
     */
    public BST<PaisOrdemAlfabetica> getBST() {
        return mbst;
    }

    /**
     * Retorna a arvore 2d-Tree
     * 
     * @return arvore 2d-Tree
     */
    public Mapa2DTree get2D() {
        return m2d;
    }
    
    /**
     * Encontra um país na arvore BST recebendo o seu nome por parametro
     * 
     * @param pais
     * @return pais ou null se não encontrar
     */
    public Pais findPais(String pais) {
        return mbst.findPais(pais);
    }

    /**
     * Retorna uma lista de fronteiras do pais com o nome recebido por parametros, em que os paises fronteira fazem fronteira com outros paises
     * 
     * @param paisStr - nome do pais
     * @return lista de fronteiras de pais
     */
    public LinkedList<Pais> listaFronteirasPais(String paisStr) {
        LinkedList<Pais> listaFronteiras = new LinkedList<>();
        Pais pais = mbst.findPais(paisStr);
        if (pais != null) {
            for (Pais p : pais.getFronteiras()) {
                if (p.getNumFronteiras() > 1) {
                    listaFronteiras.add(p);
                }
            }
        }
        return listaFronteiras;
    }

    /**
     * Retorna uma lista dos paises de um continente ordenados por ordem decrescente de fronteiras e crescente de população
     * 
     * @param continente
     * @return 
     */
    public LinkedList<Pais> listaOrdenadaPaisesContinente(String continente) {
        BST<PaisOrdemFronteirasPopulacao> mapaContinente = new BST<>();
        LinkedList<Pais> listaPaises= new LinkedList<>();
        for (Pais p : mbst.inOrder()) {
            if (p.getContinente().equals(continente)) {
                PaisOrdemFronteirasPopulacao pFront = new PaisOrdemFronteirasPopulacao(p);
                mapaContinente.insert(pFront);
            }
        }
        for(Pais p: mapaContinente.inOrder()) {
            listaPaises.add(p);
        }
        return listaPaises;
    }
    
    /**
     * Cria a 2d-Tree
     */
    public void create_2dTree() {
        for(PaisOrdemAlfabetica p:mbst.inOrder()) {
            m2d.insert(p);
        }
    }

    /**
     * Pesquisa e retorna uma lista dos paises que se encontram no quadrado formado pelas coordendas recebidas
     * 
     * @param latitude
     * @param longitude
     * @return pais
     */
    public Pais pesquisaExata(double latitude, double longitude) {
        return m2d.pesquisaExata(latitude, longitude);
    }

    /**
     * Pesquisa e retorna o pais cuja capital se encontra mais proxima das coordenadas recebidas
     * 
     * @param latitude
     * @param longitude
     * @return 
     */
    public PaisOrdemAlfabetica vizinhoMaisProximo(double latitude, double longitude) {
        return m2d.vizinhoMaisProximo(latitude, longitude);
    }

    /**
     * Pesquisa e retorna o pais cuja capital se encontra mais proxima das coordenadas recebidas
     * 
     * @param latitude1
     * @param longitude1
     * @param latitude2
     * @param longitude2
     * @return 
     */
    public ArrayList<PaisOrdemAlfabetica> pesquisaQuad(double latitude1, double longitude1, double latitude2, double longitude2) {
        ArrayList<PaisOrdemAlfabetica> listaPaises;
        listaPaises = m2d.pesquisaArea(latitude1, longitude1, latitude2, longitude2);
        return listaPaises;

    }
}
