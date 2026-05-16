package Inventario;

import Personajes.Personaje;
import java.io.Serializable;

public class Objeto implements Serializable, Usable{
    private static final long serialVersionUID = 1L; 
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
     @Override
     public void usar(Personaje objetivo) {
        switch (this.tipo) {
            case POCION:
                //si es una pocion, el efecto suma vida
                objetivo.setVidaHP(objetivo.getVidaHP() + this.efecto);
                System.out.println("Usaste " + this.nombre + ". Recuperaste " + this.efecto + " de vida.");
                break;
                
            case ARMA:
                // Si es arma, el efecto suma fuerza
                objetivo.setFuerza(objetivo.getFuerza() + this.efecto);
                System.out.println("Te equipaste " + this.nombre + ". Tu fuerza aumentó en " + this.efecto + ".");
                break;
                
            case ARMADURA:
                // Si es armadura, el efecto suma defensa
                objetivo.setDefensa(objetivo.getDefensa() + this.efecto);
                System.out.println("Te equipaste " + this.nombre + ". Tu defensa aumentó en " + this.efecto + ".");
                break;
                
            case MISCELANEO:
                System.out.println("Has examinado " + this.nombre + ": " + this.descripcion);
                break;
                
            default:
                System.out.println("No puedes usar este objeto.");
        }
    }
    
}