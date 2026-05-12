package Misiones;

import Inventario.Objeto;
import Personajes.Personaje;
import java.io.Serializable;
import java.util.ArrayList;

public class Mision implements Serializable {
    private static final long serialVersionUID = 1L;
    private String nombre;
    private String descripcion;
    private int nivel;
    private int oro;
    private int exp;
    private Objeto[] recompensas;
    private String estado;

    public Mision(String nombre, String descripcion, int nivel, int oro, int exp) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.oro = oro;
        this.exp = exp;
        this.recompensas = new Objeto[20];
        this.estado = "DISPONIBLE";
    }

    public static ArrayList<Mision> crearCatalogo() {
        ArrayList<Mision> catalogo = new ArrayList<>();
        
        catalogo.add(new Mision("Derrotar slimes", "Derrota 5 slimes en el bosque", 1, 100, 100));
        catalogo.add(new Mision("Recolectar hierbas", "Recolecta 10 hierbas medicinales", 2, 150, 150));
        catalogo.add(new Mision("El lobo solitario", "Derrota al lobo alfa del valle", 3, 200, 250));
        catalogo.add(new Mision("La cueva oscura", "Explora la cueva y encuentra el cristal", 4, 300, 350));
        catalogo.add(new Mision("Proteger la aldea", "Defiende la aldea de los bandidos", 5, 400, 500));
        catalogo.add(new Mision("El dragon dormido", "Despierta y derrota al dragon anciano", 6, 600, 650));
        catalogo.add(new Mision("El tesoro perdido", "Encuentra el tesoro del rey pirata", 7, 500, 700));
        catalogo.add(new Mision("La torre magica", "Llega a la cima de la torre magica", 8, 800, 850));
        catalogo.add(new Mision("El ejercito oscuro", "Derrota al general del ejercito oscuro", 9, 900, 950));
        catalogo.add(new Mision("El rey demonio", "Vence al rey demonio en combate final", 10, 1500, 1500));
        
        return catalogo;
    }

    public String getNombre() { 
        return nombre; 
    }
    public String getDescripcion() { 
        return descripcion; 
    }
    public int getNivel() { 
        return nivel; 
    }
    public int getOro() { 
        return oro; 
    }
    public int getExp() { 
        return exp; 
    }
    public String getEstado() { 
        return estado; 
    }
    
    public void setEstado(String estado) { 
        this.estado = estado; 
    }
    public boolean verificarNivel(Personaje personaje) {
    if (personaje.getNivel() >= this.nivel) {
        return true;
    } else {
        return false;
    }
}

    @Override
    public String toString() {
        return "MISION: " + nombre + "\n  Descripcion: " + descripcion + "\n  Nivel requerido: " + nivel + "\n  Recompensas: " + oro + " oro, " + exp + " exp";
    }
}