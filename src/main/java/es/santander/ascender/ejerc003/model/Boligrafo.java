package es.santander.ascender.ejerc003.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Pattern;

@Entity
@Table(name = "Boligrafo")
public class Boligrafo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "Color_RGB", length = 7, nullable = false)
    @Pattern(regexp = "^#[0-9A-Fa-f]{6}$", message = "El color debe estar en formato hexadecimal (#RRGGBB)")
    private String color;

    private boolean escribe;

    private String nombre;

    public Boligrafo(){
   
    }

    public Boligrafo(Long id, String color, boolean escribe, String nombre) {
        this.id = id;
        this.color = color;
        this.escribe = escribe;
        this.nombre = nombre;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        if (color == null || !color.matches("^#[0-9A-Fa-f]{6}$")) {
            throw new IllegalArgumentException("El color debe estar en formato hexadecimal (#RRGGBB)");
        }
        this.color = color;
    }

    public boolean isEscribe() {
        return escribe;
    }

    public void setEscribe(boolean escribe) {
        this.escribe = escribe;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }  

}