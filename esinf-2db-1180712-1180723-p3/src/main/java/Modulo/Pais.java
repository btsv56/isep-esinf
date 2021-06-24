/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modulo;

import java.util.LinkedList;

/**
 *
 * @author Utilizador
 */
public class Pais {
    private String nome;
    private String continente;
    private float populacao;
    private String capital;
    private double latitude;
    private double longitude;
    private LinkedList<Pais> fronteiras;
    private int numFronteiras;
    
    /**
     * Construtor do objeto Pais
     * @param nome String - Nome do país
     * @param continente String - Continente do pais
     * @param populacao float - Numero de habitantes
     * @param capital String - Capital do país
     * @param latitude float - Latitude do pais
     * @param longitude float - Longitude do pais
     */
    public Pais(String nome, String continente, float populacao, String capital, double latitude, double longitude) {
        this.nome=nome;
        this.continente=continente;
        this.populacao=populacao;
        this.capital=capital;
        this.latitude=latitude;
        this.longitude=longitude;
        this.fronteiras= new LinkedList<>();
    }

    /**
     * @return String nome do País
     */
    public String getNome() {
        return nome;
    }

    /**
     * @return String continente do país
     */
    public String getContinente() {
        return continente;
    }

    /**
     * @return float populacao do país
     */
    public float getPopulacao() {
        return populacao;
    }

    /**
     * @return String capital do país
     */
    public String getCapital() {
        return capital;
    }

    /**
     * @return float latitude do pais
     */
    public double getLatitude() {
        return latitude;
    }

    /**
     * Retorna as fronteiras do pais
     * 
     * @return fronteiras do pais
     */
    public LinkedList<Pais> getFronteiras() {
        return new LinkedList<>(fronteiras);
    }

    /**
     * Retorna o num de fronteiras do pais
     * 
     * @return num de fronteiras
     */
    public int getNumFronteiras() {
        return numFronteiras;
    }

    /**
     * @return float longitude do pais
     */
    public double getLongitude() {
        return longitude;
    }

    /**
     * Set nome
     * @param nome do pais String  
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Set continente
     * @param  continente do país String
     */
    public void setContinente(String continente) {
        this.continente = continente;
    }

    /**
     * Set populacao
     * @param populacao do país float 
     */
    public void setPopulacao(int populacao) {
        this.populacao = populacao;
    }

    /**
     * Set capital
     * @param capital do país String 
     */
    public void setCapital(String capital) {
        this.capital = capital;
    }

    /**
     * Set latitude
     * @param latitude do país float 
     */
    public void setLatitude(float latitude) {
        this.latitude = latitude;
    }

    /**
     * Set longitude
     * @param longitude do pais float 
     */
    public void setLongitude(float longitude) {
        this.longitude = longitude;
    }
    
    /**
     * Set fronteiras
     * 
     * @param Fronteiras 
     */
    public void setFronteiras(LinkedList<Pais> Fronteiras) {
        this.fronteiras = new LinkedList<>(Fronteiras);
        this.numFronteiras= Fronteiras.size();
    }
    
    /**
     * Adiciona uma fronteira
     * 
     * @param pais 
     */
    public void adicionarFronteira(Pais pais) {
        this.fronteiras.add(pais);
        numFronteiras++;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.latitude) ^ (Double.doubleToLongBits(this.latitude) >>> 32));
        hash = 71 * hash + (int) (Double.doubleToLongBits(this.longitude) ^ (Double.doubleToLongBits(this.longitude) >>> 32));
        return hash;
    }

    /**
     * Compara dois objetos Pais (longitude e latitude)
     * @param obj Object 
     * @return true se obj for Pais com a mesma latitude e longitude, false caso contrário
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Pais other = (Pais) obj;
        return (((this.latitude == other.latitude) && (this.longitude) == other.longitude));
    }
    
    /**
     * @return String (país, continente, capital, latitude, longitude) 
     */
    public String toStringWoW() {
        String fronts="";
        for (Pais p : fronteiras) {
            fronts+=p.getNome()+" ";
        }
        return String.format("País: %s; Fronteiras: %d%n", nome, fronteiras.size());
    }
    
    /**
     * @return String (país, continente, capital, latitude, longitude) 
     */
    @Override
    public String toString() {
        String fronts="";
        for (Pais p : fronteiras) {
            fronts+=p.getNome()+" ";
        }
        return String.format("País: %s; Latitude: %f; Longitude: %f%n", nome, latitude, longitude);
    }
    
    
}
