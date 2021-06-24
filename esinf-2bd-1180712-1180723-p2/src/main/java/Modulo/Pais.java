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
public class Pais implements Comparable<Pais> {
    private String nome;
    private String continente;
    private float populacao;
    private String capital;
    private float latitude;
    private float longitude;
    private int cor;
    
    /**
     * Construtor do objeto Pais
     * @param nome String - Nome do país
     * @param continente String - Continente do pais
     * @param populacao float - Numero de habitantes
     * @param capital String - Capital do país
     * @param latitude float - Latitude do pais
     * @param longitude float - Longitude do pais
     */
    public Pais(String nome, String continente, float populacao, String capital, float latitude, float longitude) {
        this.nome=nome;
        this.continente=continente;
        this.populacao=populacao;
        this.capital=capital;
        this.latitude=latitude;
        this.longitude=longitude;
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
    public float getLatitude() {
        return latitude;
    }

    /**
     * @return float longitude do pais
     */
    public float getLongitude() {
        return longitude;
    }

    /**
     * @return cor do pais no mapa int
     */
    public int getCor() {
        return cor;
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
     * @param cor do país int
     */
    public void setCor(int cor) {
        this.cor = cor;
    }

    /**
     * HashCode do objeto Pais
     * @return int hash
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Float.floatToIntBits(this.latitude);
        hash = 37 * hash + Float.floatToIntBits(this.longitude);
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
        return ((Float.floatToIntBits(this.latitude) == Float.floatToIntBits(other.latitude)) && (Float.floatToIntBits(this.longitude) == Float.floatToIntBits(other.longitude)));
    }
    
    /**
     * @return String (país, continente, capital, latitude, longitude) 
     */
    @Override
    public String toString() {
        return String.format("País: %s%nContinente: %s%nPopulacao: %.2f%nCapital: %s%nLatitude: %.2f%nLongitude: %.2f%nCor: %d%n", nome,continente,populacao,capital,latitude,longitude,cor);
    }

    /**
     * Compara Pais p1 e Pais p2, tendo em conta a sua população
     * @param Pais o
     * @return 1 se p1 tiver mais população, 0 se for igual e -1 se tiver menos 
     */
    @Override
    public int compareTo(Pais o) {
        if(this.getPopulacao()>o.getPopulacao()) {
            return 1;
        }
        if(this.getPopulacao()<o.getPopulacao()) {
            return -1;
        }
        return 0;
    }
    
    
}
