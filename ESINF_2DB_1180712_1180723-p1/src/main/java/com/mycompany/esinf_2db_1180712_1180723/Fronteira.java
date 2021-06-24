/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf_2db_1180712_1180723;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

/**
 *
 * @author Utilizador
 */
public class Fronteira {

    private Map<Pais, Set<Pais>> m;

    /**
     * Construtor do objeto Fronteira, usa o método read() do Reader
     *
     * @throws FileNotFoundException
     */
    public Fronteira() throws FileNotFoundException {
        m = new HashMap<>();
        m = Reader.read();
    }

    /**
     * Contrutor do objeto Fronteira, usa um Map como parâmetro
     *
     * @param Map<Pais, Set<Pais>> m
     */
    public Fronteira(Map<Pais, Set<Pais>> m) {
        this.m = m;
    }

    /**
     * Faz return de uma LinkedList de Paises de um continetne com mais que um
     * número dado de população e ordenada por esse parâmetro (descendente)
     *
     * @param String continente
     * @param float numHabitantes
     * @return LinkedList<Pais> lk
     */
    public LinkedList<Pais> devolverPaisesMaisNHabitantes(String continente, float numHabitantes) {
        LinkedList<Pais> lk = new LinkedList<>();
        for (Pais p : m.keySet()) {
            if (p.getPopulacao() > numHabitantes && p.getContinente().equals(continente)) {
                lk.add(p);
            }
        }
        Collections.sort(lk);
        return lk;
    }

    /**
     * Faz return de um Map, cujas chaves são o número de fronteiras, e os
     * valores são os países que possuem esse número de fronteiras
     *
     * @param String continente
     * @return Map<Integer, Set<Pais>> map
     */
    public Map<Integer, Set<Pais>> devolverPaisesPorNumFronteiras(String continente) {
        Map<Integer, Set<Pais>> map = new TreeMap<>(Collections.reverseOrder());
        for (Pais p : m.keySet()) {
            if (p.getContinente().equalsIgnoreCase(continente)) {
                int numFronteiras = m.get(p).size();
                int loc = localizar(numFronteiras, map.keySet());
                if (loc == -1) {
                    Set<Pais> setPais = new HashSet<>();
                    map.put(numFronteiras, setPais);
                    setPais.add(p);
                } else {
                    Set<Pais> setPais = map.get(loc);
                    setPais.add(p);
                }

            }
        }
        return map;
    }

    /**
     * Faz return do número de fronteiras de um país e da posição no map Caso
     * não houver esse número no set das chaves do map, é criado um novo par de
     * elementos
     *
     * @param int n
     * @param Integer s
     * @return i, se houver no Set das chaves do map, -1 se não houver
     */
    private Integer localizar(int n, Set<Integer> s) {
        for (Integer i : s) {
            if (i == n) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Faz return do número mínimo de países que se tem de atravessar para ir do
     * Pais p1 ao Pais p2
     *
     * @param Pais pais1
     * @param Pais pais2
     * @return int número de fronteiras atravessadas
     */
    public int obterMinFronteiras(Pais pais1, Pais pais2) {
        Map<Pais, Set<Pais>> map = new HashMap<>();
        if (pais1 == pais2) {
            return 0;
        }
        map.put(pais1, m.get(pais1));
        Set<Pais> saiu = new HashSet<>();
        int cont = 0;
        return obterMinFronteirasAux(map, pais2, cont, saiu);
    }

    /**
     * Método recursivo auxiliar ao obterMinFronteiras()
     *
     * @param Map<Pais, Set<Pais>> map
     * @param Pais pais2
     * @param int cont
     * @param Set<Pais> saiu
     * @return int cont ou -1
     */
    private int obterMinFronteirasAux(Map<Pais, Set<Pais>> map, Pais pais2, int cont, Set<Pais> saiu) {
        cont++;
        for (Pais p : map.keySet()) {
            if (map.get(p).contains(pais2)) {
                return cont;
            }
        }
        Map<Pais, Set<Pais>> map2 = new HashMap<>();
        for (Pais p : map.keySet()) {
            saiu.add(p);
        }
        for (Pais p : map.keySet()) {
            for (Pais p2 : m.get(p)) {
                if (!saiu.contains(p2)) {
                    map2.put(p2, m.get(p2));
                }
            }
        }
        if (map2.isEmpty()) {
            return -1;
        }
        return obterMinFronteirasAux(map2, pais2, cont, saiu);
    }
}
