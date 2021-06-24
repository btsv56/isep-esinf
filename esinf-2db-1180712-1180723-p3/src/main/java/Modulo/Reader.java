/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo;

import Mapa.Mapa;
import Mapa.Mapa2DTree;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Scanner;
import Modulo.PaisOrdemAlfabetica;

/**
 *
 * @author joaol
 */
public class Reader {
    
    private static File paises=new File("paises.txt");
    private static File fronteiras=new File("fronteiras.txt");
    
    /**
     * Lê os ficheiros .txt e faz return de um Mapa
     * @return Mapa m
     * @throws FileNotFoundException 
     */
    public static Mapa read() throws FileNotFoundException {
        Mapa m=new Mapa();
        readPaises(m);
        readFronteiras(m);
        m.create_2dTree();
        return m;
    }
    
    /**
     * Lê o ficheiro paises.txt e insere todos os países como chaves, com um TreeSet vazio como valor no HashMap 
     * @param m Mapa de países
     * @throws FileNotFoundException 
     */
    private static void readPaises(Mapa m) throws FileNotFoundException {
        Scanner scn1=new Scanner(paises);
        while (scn1.hasNextLine()) {
            String line=scn1.nextLine();
            String[] words=line.split(",");
            PaisOrdemAlfabetica p=new PaisOrdemAlfabetica(words[0].trim(), words[1].trim(), Float.parseFloat(words[2].trim()), words[3].trim(), Double.parseDouble(words[4].trim()), Double.parseDouble(words[5].trim()));
            m.adicionarPaisBST(p);
        }
        scn1.close();
    }
    
    /**
     * Lê o ficheiro fronteiras.txt, inserindo as fronteiras de cada país no TreeSet da chave correspondente do HashMap
     * @param m Mapa de países
     * @throws FileNotFoundException 
     */
    private static void readFronteiras(Mapa m) throws FileNotFoundException {
        Scanner scn2=new Scanner(fronteiras);
        while (scn2.hasNextLine()) {
            String line=scn2.nextLine();
            String[] words=line.split(",");
            String nome1=words[0].trim();
            String nome2=words[1].trim();
            m.findPais(nome1).adicionarFronteira(m.findPais(nome2));
            m.findPais(nome2).adicionarFronteira(m.findPais(nome1));
        }
        scn2.close();
    }
}
