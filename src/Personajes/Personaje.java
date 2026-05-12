package Personajes;

import Inventario.Inventario;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Personaje implements Comparable<Personaje>, Serializable {
   private static final long serialVersionUID = 1L; 
   protected int fuerza;
   protected int mana;
   protected int defensa;
   protected int agilidad;
   protected int vidaHP;
   protected int oro;
   
   protected String nombre;
   protected int nivel;
   protected int exp;
   protected estadoPersonaje estado;
   protected String clase;
   protected Inventario inventario;
   LocalDate fechaCreacion;

    public Personaje(int fuerza, int mana, int defensa, int agilidad, int vidaHP, int oro, String nombre, int nivel, int exp, estadoPersonaje estado) {
        this.fuerza = fuerza;
        this.mana = mana;
        this.defensa = defensa;
        this.agilidad = agilidad;
        this.vidaHP = vidaHP;
        this.oro = oro;
        this.nombre = nombre;
        this.nivel = nivel;
        this.exp = exp;
        this.estado = estado;
        this.fechaCreacion = LocalDate.now(); 
        this.inventario = new Inventario(50.0);
    }
    
    // [REQUISITOS]: Fechas
    @Override
    public String toString() {
    DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    return "Nombre: " + this.nombre + "\n" +
           "Clase: " + this.getClase() + "\n" +
           "Nivel: " + this.nivel + "\n" +
           "Experiencia: " + this.exp + "\n" +
           "Estado: " + this.estado + "\n" +
           "Oro: " + this.oro + "\n" +
           "Fecha de creacion: " + this.fechaCreacion.format(format) + "\n" +
           "\n===ESTADISTICAS===\n" +
           "Vida (HP): " + this.vidaHP + "\n" +
           "Mana: " + this.mana + "\n" +
           "Fuerza: " + this.fuerza + "\n" +
           "Defensa: " + this.defensa + "\n" +
           "Agilidad: " + this.agilidad;
}
    // identificador 
    public String getClase() {
        return "Ninguna";
    }
    

    public int getFuerza() {
        return fuerza;
    }

    public int getMana() {
        return mana;
    }

    public int getDefensa() {
        return defensa;
    }

    public int getAgilidad() {
        return agilidad;
    }

    public int getVidaHP() {
        return vidaHP;
    }

    public String getNombre() {
        return nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public int getExp() {
        return exp;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public void setExp(int exp) {
        this.exp = exp;
    }

    public int getOro() {
        return oro;
    }

    public void setOro(int oro) {
        this.oro = oro;
    }
    
    public void setInventario(Inventario inventario) {
        this.inventario = inventario;
    }

    public estadoPersonaje getEstado() {
        return estado;
    }

    @Override
    public int compareTo(Personaje o) {
       return Integer.compare(o.getNivel(), this.getNivel());
    }
    
   
    
   
}