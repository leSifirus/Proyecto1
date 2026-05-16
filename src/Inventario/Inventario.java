package Inventario;

import java.io.Serializable;
import java.util.ArrayList;

public class Inventario implements Serializable {
    private static final long serialVersionUID = 1L; 
    private ArrayList<Objeto> objetos;
    private static double limPeso;
    private double pesoActual;

    public Inventario(double limPeso) {
        this.objetos = new ArrayList<>();
        this.limPeso = limPeso;
        this.pesoActual = 0;
    }

    public double getLimPeso() {
        return limPeso;
    }

    public double getPesoActual() {
        return pesoActual;
    }

    public void setPesoActual(double pesoActual) {
        this.pesoActual = pesoActual;
    }

    public ArrayList<Objeto> getObjetos() {
        return objetos;
    }
   
    public boolean addObjeto(Objeto nuevoObjeto) {
    if (this.pesoActual + nuevoObjeto.getPeso() <= this.limPeso) {
        
        this.objetos.add(nuevoObjeto);
        
        this.pesoActual += nuevoObjeto.getPeso(); 
        
        return true; 
    }
    
    return false; 
}
    
    public void verInventarioCompleto() {
        if (objetos.isEmpty()) {
            System.out.println("El inventario esta vacio.");
            return;
        }
        //contador de objetos
        int i = 0;
        for (Objeto obj : objetos) {
            System.out.println("-" + (i + 1) + ". obj.getNombre()" + " (Tipo: " + obj.getTipo() + ", Peso: " + obj.getPeso() + ")");
            i++;
        }
        System.out.println("Peso actual del inventario: " + pesoActual + " Limite de peso: " + limPeso);
    }
   public Objeto sacarObjeto(int indice) {
        if (indice >= 0 && indice < objetos.size()) {
   
            Objeto objetoSacado = objetos.remove(indice);
            
            return objetoSacado;
        }
        return null; // Si el índice no existe
    }
    
}
    