package es.santander.ascender.ejerc003.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;


@Entity
@Table(name = "Ordenador")
public class Ordenador {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private int color;

    @Min(value = 0)
    private int numeroDeTeclas;

    private boolean isIntel;

    @Min(value=0)
    @Max(value=200)
    private float peso;

    public Ordenador(){

    }

    public Ordenador(Long id, int red, int green, int blue, int numeroDeTeclas, boolean isIntel, float peso) {
        this.id = id;
        setColorFromRGB(red, green, blue); 
        this.numeroDeTeclas= numeroDeTeclas;
        this.isIntel = isIntel;
        this.peso = peso;
        
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getColor() {
        return color;
    }

    // Método para configurar el color desde RGB
    public void setColorFromRGB(int red, int green, int blue) {
        if (red < 0 || red > 255 || green < 0 || green > 255 || blue < 0 || blue > 255) {
            throw new IllegalArgumentException("Los valores RGB deben estar entre 0 y 255");
        }

        /*
         * red << 16 => desplaza el valor de red 16 posiciones a la izquierda para que ocupe los primeros 8 bits del número de 24 bits
         * 
         * green << 8 => desplaza el valor de green 8 posiciones a la izquierda para que ocupe los bits del medio
         * 
         * blue => el valor de blue no se desplaza porque ya ocupa los últimos 8 bits
         */
        this.color = (red << 16) | (green << 8) | blue;
    }

    public int getNumeroDeTeclas() {
        return numeroDeTeclas;
    }

    public void setNumeroDeTeclas(int numeroDeTeclas) {
        this.numeroDeTeclas = numeroDeTeclas;
    }

    public boolean isIntel() {
        return isIntel;
    }

    public void setIntel(boolean isIntel) {
        this.isIntel = isIntel;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

}