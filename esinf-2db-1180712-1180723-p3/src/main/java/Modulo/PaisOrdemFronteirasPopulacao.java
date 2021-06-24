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
public class PaisOrdemFronteirasPopulacao extends Pais implements Comparable<PaisOrdemFronteirasPopulacao> {

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
    public PaisOrdemFronteirasPopulacao(String nome, String continente, float populacao, String capital, double latitude, double longitude) {
        super(nome, continente, populacao, capital, latitude, longitude);
    }
    
    /**
     * Construtor
     * 
     * @param p - pais
     */
    public PaisOrdemFronteirasPopulacao(Pais p) {
        super(p.getNome(), p.getContinente(), p.getPopulacao(), p.getCapital(), p.getLatitude(), p.getLongitude());
        super.setFronteiras(p.getFronteiras());
    }
    
    /**
     * Compara Pais p1 e Pais p2, tendo em conta as suas fronteiras e população
     * @param Pais o
     * @return 1 se p1 tiver mais população, 0 se for igual e -1 se tiver menos 
     */
    @Override
    public int compareTo(PaisOrdemFronteirasPopulacao o) {
        if(super.getNumFronteiras()==o.getNumFronteiras()) {
            if(super.getPopulacao()> o.getPopulacao()) return 1;
            if(super.getPopulacao()<o.getPopulacao()) return -1;
            return 0;
        }
        return o.getNumFronteiras()- super.getNumFronteiras();
    }
}
