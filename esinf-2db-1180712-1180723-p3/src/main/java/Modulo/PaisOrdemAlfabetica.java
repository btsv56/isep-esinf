/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo;

/**
 *
 * @author Utilizador
 */
public class PaisOrdemAlfabetica extends Pais implements Comparable<PaisOrdemAlfabetica>{

    /**
     * Construtor
     * 
     * @param nome
     * @param continente
     * @param populacao
     * @param capital
     * @param latitude
     * @param longitude 
     */
    public PaisOrdemAlfabetica(String nome, String continente, float populacao, String capital, double latitude, double longitude) {
        super(nome, continente, populacao, capital, latitude, longitude);
    }
    
    /**
     * Construtor
     * 
     * @param p - pais
     */
    public PaisOrdemAlfabetica(Pais p){
       super(p.getNome(), p.getContinente(), p.getPopulacao(), p.getCapital(),p.getLatitude(), p.getLongitude());
       super.setFronteiras(p.getFronteiras());
    }
    
    /**
     * Compara dois paises por ordem alfabetica do nome
     * 
     * @param o
     * @return 
     */
    @Override
    public int compareTo(PaisOrdemAlfabetica o) {
        return super.getNome().compareTo(o.getNome());
    }
}
