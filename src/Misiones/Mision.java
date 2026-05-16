package Misiones;

import Inventario.Objeto;
import static Inventario.Rareza.*;
import static Inventario.tipoObjeto.*;
import Personajes.Personaje;
import java.io.Serializable;
import java.util.ArrayList;

public class Mision implements Serializable {
    private static final long serialVersionUID = 1L;
    private final String nombre;
    private final String descripcion;
    private final int nivel;
    private final int oro;
    private final int exp;
    private String estado;

    public Mision(String nombre, String descripcion, int nivel, int oro, int exp) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.nivel = nivel;
        this.oro = oro;
        this.exp = exp;
        this.estado = "DISPONIBLE";
    }

    public final static ArrayList<Mision> crearCatalogo() {
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
    public final static Objeto[] crearRecompensas() {
        Objeto[] recompensas = new Objeto[10];
        //nombre, descripcion, rareza, peso, efecto, tipo
        
        recompensas[0] = (new Objeto("Espada de totsuka", "Espada que sella al cortar", LEGENDARIO, 4, 100, ARMA));
        recompensas[1] = (new Objeto("Terrablade", "Energia natural concentrada", EPICO, 3/2, 75, ARMA));
        recompensas[2] = (new Objeto("Palo de madera", "Unico uso... luego se rompe", COMUN, 1/2, 2, ARMA));
        recompensas[3] = (new Objeto("Escudo de cruz ansata", "Escudo anti cualquier debilidad", LEGENDARIO, 5, 45, ARMADURA));
        recompensas[4] = (new Objeto("Gran pocion de vida", "Cura 200HP", RARO, 1, 200, POCION));
        recompensas[5] = (new Objeto("Arco tormentas de Dédalo", "Dispara lluvia de flechas", LEGENDARIO, 3, 95, ARMA));
        recompensas[6] = (new Objeto("La piedra filosofal", "Cura vida al tenerla equipada", EPICO, 3, 15, MISCELANEO));
        recompensas[7] = (new Objeto("Casco de mineria", "Proporciona luz en la oscuridad", COMUN, 2, 20, ARMADURA));
        recompensas[8] = (new Objeto("Pocion reutilizable", "Pocion reutilizable cada 2 minutos", RARO, 1, 30, POCION));
        recompensas[9] = (new Objeto("La sanguinaria", "Cura vida al atacar", LEGENDARIO, 3, 101, ARMA));
        
        
        return recompensas;
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
        return personaje.getNivel() >= this.nivel;
}

    @Override
    public String toString() {
        return "MISION: " + nombre + "\n  Descripcion: " + descripcion + "\n  Nivel requerido: " + nivel + "\n  Recompensas: " + oro + " oro, " + exp + " exp";
    }
}