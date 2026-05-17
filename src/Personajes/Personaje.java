package Personajes;

import Inventario.Inventario;
import Inventario.Objeto;
import Misiones.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public abstract class Personaje implements Comparable<Personaje>, Serializable, Rankeable {
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
   protected ArrayList<Mision> historialMisiones;

 
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
        this.historialMisiones = new ArrayList<>();
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
    public ArrayList<Mision> getHistorialMisiones() {
        return historialMisiones;
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

    public void setFuerza(int fuerza) {
        this.fuerza = fuerza;
    }

    public void setDefensa(int defensa) {
        this.defensa = defensa;
    }

    public void setVidaHP(int vidaHP) {
        this.vidaHP = vidaHP;
    }
    
   @Override
    public void ganarXP(int xp) {
        this.exp += xp;
        
        // se verifica si se alcanza la exp para subir de nivel
        int xpRequerida = this.nivel * 1000;
        
        // Se usa un while por si gana tanta XP de golpe que sube 2 niveles seguidos
        while (this.exp >= xpRequerida) {
            subirNivel();
            xpRequerida = this.nivel * 1000; // Recalcula para el siguiente nivel y asi sucesivamente
        }
    }
    @Override
    public void subirNivel() {
        int xpRequerida = this.nivel * 1000;
        
        // Restamos la xp usada para subir de nivel, manteniendo el sobrante
        this.exp -= xpRequerida; 
        this.nivel++;
        
        // Se aumentan la estadisticas
        this.vidaHP += 50;
        this.mana += 20;
        this.fuerza += 10;
        this.defensa += 10;
        this.agilidad += 5;
        
        System.out.println("Haz subido de nivel\n Tus estadisticas han sido aumentadas");
    }
    public void ganarOro(int oro) {
       this.oro += oro;
    }

    public void setEstado(estadoPersonaje estado) {
        this.estado = estado;
    }

   
}