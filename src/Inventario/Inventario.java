package Inventario;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventario implements Serializable {
    private static final long serialVersionUID = 1L; 
    private final ArrayList<Objeto> objetos;
    private double limPeso;

    public Inventario(double limPeso) {
        this.objetos = new ArrayList<>();
        this.limPeso = limPeso;
    }

    public double getLimPeso() {
        return limPeso;
    }

    public void setLimPeso(double limPeso) {
        this.limPeso = limPeso;
    }
    
    public void addObjeto(Objeto p){
        objetos.add(p);   
    }
    
    public void verInventarioCompleto() {
        System.out.println("\n===INVENTARIO COMPLETO===");
        if (objetos.isEmpty()) {
            System.out.println("El inventario esta vacio.");
            return;
        }
        
        for (Objeto obj : objetos) {
            System.out.println("- " + obj.getNombre() + " (Tipo: " + obj.getTipo() + ", Peso: " + obj.getPeso() + ")");
        }
    }
}