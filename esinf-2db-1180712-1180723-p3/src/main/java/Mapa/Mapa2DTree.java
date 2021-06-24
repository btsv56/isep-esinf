/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Mapa;

import Modulo.Pais;
import Modulo.PaisOrdemAlfabetica;
import PL.BST;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;

/**
 *
 * @author Utilizador
 */
public class Mapa2DTree extends BST<PaisOrdemAlfabetica> {

    /**
     * Insere um elemento na 2d-Tree
     * @param pais 
     */
    @Override
    public void insert(PaisOrdemAlfabetica pais) {
        root = insert(pais, root, 0);
    }

    /**
     * Insere um elemento na 2d-Tree
     * 
     * @param pais - pais a inserir
     * @param node - node a analisar
     * @param i - nivel do node
     * @return 
     */
    private Node<PaisOrdemAlfabetica> insert(PaisOrdemAlfabetica pais, Node<PaisOrdemAlfabetica> node, int i) {
        if (node == null) {
            return new Node(pais, null, null);
        }
        if (node.getElement() == pais) {
            node.setElement(pais);
        } else {
            if (i % 2 == 0) {
                if (node.getElement().getLatitude() > pais.getLatitude()) {
                    node.setLeft(insert(pais, node.getLeft(), ++i));
                } else {
                    node.setRight(insert(pais, node.getRight(), ++i));
                }
            } else {
                if (node.getElement().getLongitude() > pais.getLongitude()) {
                    node.setLeft(insert(pais, node.getLeft(), ++i));
                } else {
                    node.setRight(insert(pais, node.getRight(), ++i));
                }
            }
        }
        return node;
    }

    /**
     * Pesquisa e retorna um pais cuja capital tem as coordendas pretendidas se existir
     * 
     * @param latitude
     * @param longitude
     * @return 
     */
    public PaisOrdemAlfabetica pesquisaExata(double latitude, double longitude) {
        return pesquisaExata(latitude, longitude, root, 0);
    }

    /**
     * Pesquisa e retorna um pais cuja capital tem as coordendas pretendidas se existir
     * 
     * @param latitude
     * @param longitude
     * @param node - node atual
     * @param i - nivel do node
     * @return 
     */
    private PaisOrdemAlfabetica pesquisaExata(double latitude, double longitude, Node<PaisOrdemAlfabetica> node, int i) {
        if (node == null) {
            return null;
        }
        if (node.getElement().getLatitude() == latitude && node.getElement().getLongitude() == longitude) {
            return node.getElement();
        }
        if (i % 2 == 0) {
            return pesquisaExata(latitude, longitude, node.getElement().getLatitude() < latitude ? node.getRight() : node.getLeft(), ++i);
        } else {
            return pesquisaExata(latitude, longitude, node.getElement().getLongitude() < longitude ? node.getRight() : node.getLeft(), ++i);
        }
    }

    /**
     * Pesquisa e retorna uma lista dos paises que se encontram no quadrado formado pelas coordendas recebidas
     * 
     * @param latitude1
     * @param longitude1
     * @param latitude2
     * @param longitude2
     * @return 
     */
    public ArrayList<PaisOrdemAlfabetica> pesquisaArea(double latitude1, double longitude1, double latitude2, double longitude2) {
        ArrayList<PaisOrdemAlfabetica> listaPaises = new ArrayList<>();
        if (latitude1 == latitude2 && longitude1 == longitude2) {
            listaPaises.add(pesquisaExata(latitude1, longitude1));
            return listaPaises;
        }
        double lat1;
        double lat2;
        double lon1;
        double lon2;
        if (latitude1 < latitude2) {
            lat1 = latitude1;
            lat2 = latitude2;
        } else {
            lat2 = latitude1;
            lat1 = latitude2;
        }
        if (longitude1 < longitude2) {
            lon1 = longitude1;
            lon2 = longitude2;
        } else {
            lon2 = longitude1;
            lon1 = longitude2;
        }
        int i = 0;
        pesquisaArea(lat1, lon1, lat2, lon2, listaPaises, root(), i);
        return listaPaises;
    }

    /**
     * Pesquisa e retorna uma lista dos paises que se encontram no quadrado formado pelas coordendas recebidas
     * 
     * @param latitude1
     * @param longitude1
     * @param latitude2
     * @param longitude2
     * @param listaPaises
     * @param node - node atual
     * @param i - nivel do node
     */
    private void pesquisaArea(double latitude1, double longitude1, double latitude2, double longitude2, ArrayList<PaisOrdemAlfabetica> listaPaises, Node<PaisOrdemAlfabetica> node, int i) {
        if (node == null) {
            return;
        }
        double lat = node.getElement().getLatitude();
        double lon = node.getElement().getLongitude();
        if (lat >= latitude1 && lat <= latitude2 && lon >= longitude1 && lon <= longitude2) {
            listaPaises.add(node.getElement());
        }
        if(i%2==0) {
            if (lat < latitude1) {
                pesquisaArea(latitude1, longitude1, latitude2, longitude2, listaPaises, node.getRight(), i+1);
            }
            else if (lat > latitude2) {
                pesquisaArea(latitude1, longitude1, latitude2, longitude2, listaPaises, node.getLeft(), i+1);
            } else {
                pesquisaArea(latitude1, longitude1, latitude2, longitude2, listaPaises, node.getRight(), i+1);
                pesquisaArea(latitude1, longitude1, latitude2, longitude2, listaPaises, node.getLeft(), i+1);
            }
        } else {
            if (lon < longitude1) {
                pesquisaArea(latitude1, longitude1, latitude2, longitude2, listaPaises, node.getRight(), i+1);
            }
            else if (lon > longitude2) {
                pesquisaArea(latitude1, longitude1, latitude2, longitude2, listaPaises, node.getLeft(), i+1);
            } else {
                pesquisaArea(latitude1, longitude1, latitude2, longitude2, listaPaises, node.getRight(), i+1);
                pesquisaArea(latitude1, longitude1, latitude2, longitude2, listaPaises, node.getLeft(), i+1);
            }
        }
    }

    /**
     * Pesquisa e retorna o pais cuja capital se encontra mais proxima das coordenadas recebidas
     * 
     * @param latitude
     * @param longitude
     * @return 
     */
    public PaisOrdemAlfabetica vizinhoMaisProximo(double latitude, double longitude) {
        PaisOrdemAlfabetica paisMin = root().getElement();
        paisMin=vizinhoMaisProximo(latitude, longitude, root(), paisMin, 0);
        List<PaisOrdemAlfabetica> listaMin; 
        double dist = Math.sqrt(Math.pow((latitude-paisMin.getLatitude()), 2)+Math.pow((longitude-paisMin.getLongitude()), 2));
        listaMin=pesquisaArea(latitude-dist, longitude-dist, latitude+dist, longitude+dist);
        for(PaisOrdemAlfabetica p:listaMin) {
            if(getDistance(p, latitude, longitude) < getDistance(paisMin,latitude,longitude)) {
                paisMin=p;
            }
        }
        return paisMin;
    }

    /**
     * Pesquisa e retorna o pais cuja capital se encontra mais proxima das coordenadas recebidas
     * 
     * @param latitude
     * @param longitude
     * @param node
     * @param paisMin
     * @param i
     * @return 
     */
    private PaisOrdemAlfabetica vizinhoMaisProximo(double latitude, double longitude, Node<PaisOrdemAlfabetica> node, PaisOrdemAlfabetica paisMin, double i) {
        if (getDistance(node.getElement(), latitude, longitude) == 0) {
            return node.getElement();
        }
        if (node.getRight() == null && node.getLeft() == null) {
            paisMin = node.getElement();
            return paisMin;
        }
        if (node.getLeft() != null && node.getRight() != null) {
            if (i % 2 == 0) {
                if (node.getElement().getLatitude() > latitude) {
                    return vizinhoMaisProximo(latitude, longitude, node.getLeft(), paisMin, ++i);
                } else {
                    return vizinhoMaisProximo(latitude, longitude, node.getRight(), paisMin, ++i);
                }
            }else {
                if (node.getElement().getLongitude() > longitude) {
                    return vizinhoMaisProximo(latitude, longitude, node.getLeft(), paisMin, ++i);
                } else {
                    return vizinhoMaisProximo(latitude, longitude, node.getRight(), paisMin, ++i);
                }
            }
        }
        if (node.getLeft() == null) {
            return vizinhoMaisProximo(latitude, longitude, node.getRight(), paisMin, ++i);
        } else {
            return vizinhoMaisProximo(latitude, longitude, node.getLeft(), paisMin, ++i);
        }
    }
    

    /**
     * Calcula a distania entre as coordendas da capital de um pais e outra coordenada recebida por parametro   
     * 
     * @param pais
     * @param lat2
     * @param lon2
     * @return 
     */
    public static double getDistance(PaisOrdemAlfabetica pais, double lat2, double lon2) {
        double lat1= pais.getLatitude();
        double lon1= pais.getLongitude();
        if ((lat1 == lat2) && (lon1 == lon2)) {
            return 0;
        } else {
            double theta = lon1 - lon2;
            double dist = Math.sin(Math.toRadians(lat1)) * Math.sin(Math.toRadians(lat2)) + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2)) * Math.cos(Math.toRadians(theta));
            dist = Math.acos(dist);
            dist = Math.toDegrees(dist);
            dist = dist * 60 * 1.1515;
            dist = dist * 1.609344;
            return (dist);
        }
    }
}
