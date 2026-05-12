package Inventario;

import java.io.Serializable;

public class Objeto implements Serializable {
    private String nombre;
    private String descripcion;
    private Rareza rareza;
    private float peso;
    private int efecto;
    private tipoObjeto tipo;

    public Objeto(String nombre, String descripcion,  Rareza rareza, float peso, int efecto, tipoObjeto tipo) {
        this.nombre = nombre;
        this.descripcion = descripcion; 
        this.rareza = rareza;
        this.peso = peso;
        this.efecto = efecto;
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public tipoObjeto getTipo() {
        return tipo;
    }

    public void setTipo(tipoObjeto tipo) {
        this.tipo = tipo;
    }

    public Rareza getRareza() {
        return rareza;
    }

    public void setRareza(Rareza rareza) {
        this.rareza = rareza;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getEfecto() {
        return efecto;
    }

    public void setEfecto(int efecto) {
        this.efecto = efecto;
    }
    
    
}