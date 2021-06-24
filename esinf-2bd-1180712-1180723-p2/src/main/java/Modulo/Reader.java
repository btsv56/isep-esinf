/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo;

import Mapa.Mapa;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
        readFronteiras(m);
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
            Pais p=new Pais(words[0].trim(), words[1].trim(), Float.parseFloat(words[2].trim()), words[3].trim(), Float.parseFloat(words[4].trim()), Float.parseFloat(words[5].trim()));
            m.adicionarPais(p);
        }
        scn1.close();
    }
    
    /**
     * Lê o ficheiro fronteiras.txt, inserindo as fronteiras de cada país no TreeSet da chave correspondente do HashMap
     * @param m Mapa de países
     * @throws FileNotFoundException 
     */
    private static void readFronteiras(Mapa m) throws FileNotFoundException {
        readPaises(m);
        Scanner scn2=new Scanner(fronteiras);
        while (scn2.hasNextLine()) {
            String line=scn2.nextLine();
            String[] words=line.split(",");
            Pais p1=findPais(words[0].trim(), m.getPaises());           
            Pais p2=findPais(words[1].trim(), m.getPaises());
            m.adicionarFronteira(p1, p2, getDistance(p1, p2));
            m.adicionarFronteira(p2, p1, getDistance(p1, p2));
        }
        scn2.close();
    }
    
    /**
     * Encontra um objeto Pais através de um TreeSet com os países lidos em readPaises()
     * @param nome Nome do País
     * @param paisSet Set de todos os Países
     * @return Pais p, ou null se não houver valor correspondente
     */
    private static Pais findPais(String nome, ArrayList<Pais> paisSet) {
        for (Pais p : paisSet) {
            if (p.getNome().equalsIgnoreCase(nome)) {
                return p;
            }
        }
        return null;
    }
    
    /**
     * Calcula a distância entre dois países
     * @param p1 País 1
     * @param p2 País 2
     * @return 
     */
    protected static double getDistance(Pais p1, Pais p2) {
        double R = 6371; // kilometres
        double lat1=(double) p1.getLatitude();
        double lat2=(double) p2.getLatitude();
        double lon1=(double) p1.getLongitude();
        double lon2=(double) p2.getLongitude();
        double latr1=Math.toRadians(lat1);
        double latr2=Math.toRadians(lat2);
        double deltaLatr=Math.toRadians(lat2-lat1);
        double deltaLon=Math.toRadians(lon2-lon1);

        double a = Math.sin(deltaLatr/2) * Math.sin(deltaLatr/2) +
                Math.cos(latr1) * Math.cos(latr2) *
                Math.sin(deltaLon/2) * Math.sin(deltaLon/2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

        return R * c;
    }
}
