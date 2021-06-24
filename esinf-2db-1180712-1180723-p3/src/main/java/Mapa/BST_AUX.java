/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Modulo.Pais;
import Modulo.PaisOrdemAlfabetica;
import PL.BST;

/**
 *
 * @author Utilizador
 */
public class BST_AUX extends BST<PaisOrdemAlfabetica>{
    
    /**
     * Encontra um pais na arvore BST recebendo o seu nome
     * 
     * @param element
     * @return pais
     */
    public PaisOrdemAlfabetica findPais(String element) {
        return findPais(element,root());
    }
    
    /**
     * Encontra um pais na arvore BST recebendo o seu nome
     * 
     * @param element
     * @param node
     * @return 
     */
     private PaisOrdemAlfabetica findPais(String element, Node<PaisOrdemAlfabetica> node) {
        if (node == null) {
            return null;
        }
        if (node.getElement().getNome().equals(element)) {
            return node.getElement();
        }
        if (node.getElement().getNome().compareTo(element) > 0) {
            return findPais(element, node.getLeft());
        }
        return findPais(element, node.getRight());
    }
}
