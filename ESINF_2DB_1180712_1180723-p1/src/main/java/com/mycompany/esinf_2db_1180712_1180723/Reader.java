/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.esinf_2db_1180712_1180723;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

/**
 *
 * @author lulu
 */
public class Reader {
    
    private static File paises=new File("paises.txt");
    private static File fronteiras=new File("fronteiras.txt");    
    
    /**
     * Lê os ficheiros paises.txt e fronteiras.txt e faz return de um HashMap com todos os paises e as suas fronteiras
     * @return Map<Pais, Set<Pais>> m
     * @throws FileNotFoundException 
     */
    public static Map<Pais, Set<Pais>> read() throws FileNotFoundException {
        Map<Pais, Set<Pais>> m=new HashMap();
        readFronteiras(m);
        return m;
    }
    
    /**
     * Lê o ficheiro paises.txt e insere todos os países como chaves, com um TreeSet vazio como valor no HashMap 
     * @param Map<Pais, Set<Pais>> m
     * @throws FileNotFoundException 
     */
    private static void readPaises(Map<Pais, Set<Pais>> m) throws FileNotFoundException {
        Scanner scn1=new Scanner(paises);
        while (scn1.hasNextLine()) {
            String line=scn1.nextLine();
            String[] words=line.split(",");
            Pais p=new Pais(words[0].trim(), words[1].trim(), Float.parseFloat(words[2].trim()), words[3].trim(), Float.parseFloat(words[4].trim()), Float.parseFloat(words[5].trim()));
            Set<Pais> paisSet=new TreeSet();
            m.put(p, paisSet);
        }
        scn1.close();
    }
    
    /**
     * Lê o ficheiro fronteiras.txt, inserindo as fronteiras de cada país no TreeSet da chave correspondente do HashMap
     * @param Map<Pais, Set<Pais>> m
     * @throws FileNotFoundException 
     */
    private static void readFronteiras(Map<Pais, Set<Pais>> m) throws FileNotFoundException {
        readPaises(m);
        Scanner scn2=new Scanner(fronteiras);
        int w=1;
        while (scn2.hasNextLine()) {
            String line=scn2.nextLine();
            String[] words=line.split(",");
            Pais p1=findPais(words[0].trim(), m.keySet());           
            Pais p2=findPais(words[1].trim(), m.keySet());
            m.get(p1).add(p2);
            m.get(p2).add(p1);
        }
        scn2.close();
    }
    
    /**
     * Encontra um objeto Pais através de um TreeSet com os países lidos em readPaises()
     * @param String nome
     * @param Set<Pais> paisSet
     * @return Pais p, ou null se não houver valor correspondente
     */
    private static Pais findPais(String nome, Set<Pais> paisSet) {
        Iterator<Pais> i=paisSet.iterator();
        while (i.hasNext()) {
            Pais p=i.next();
            String nomeP=p.getNome();
            if (nome.equals(nomeP)) {
                return p;
            }
        }
        return null;
    }
    
}
