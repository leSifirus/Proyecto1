package Inventario;

import java.util.ArrayList;

public class Inventario {
    private ArrayList<Objeto> armas;
    private ArrayList<Objeto> armaduras;
    private ArrayList<Objeto> pociones;
    private ArrayList<Objeto> miscelaneos;
    private double limPeso;

    public Inventario(ArrayList<Objeto> armas, ArrayList<Objeto> armaduras, ArrayList<Objeto> pociones, ArrayList<Objeto> miscelaneos, double limPeso) {
        this.armas = armas;
        this.armaduras = armaduras;
        this.pociones = pociones;
        this.miscelaneos = miscelaneos;
        this.limPeso = limPeso;
        
    }

    public ArrayList<Objeto> getArmas() {
        return armas;
    }

    public void setArmas(ArrayList<Objeto> armas) {
        this.armas = armas;
    }

    public ArrayList<Objeto> getArmaduras() {
        return armaduras;
    }

    public void setArmaduras(ArrayList<Objeto> armaduras) {
        this.armaduras = armaduras;
    }

    public ArrayList<Objeto> getPociones() {
        return pociones;
    }

    public void setPociones(ArrayList<Objeto> pociones) {
        this.pociones = pociones;
    }

    public ArrayList<Objeto> getMiscelaneos() {
        return miscelaneos;
    }

    public void setMiscelaneos(ArrayList<Objeto> miscelaneos) {
        this.miscelaneos = miscelaneos;
    }

    public double getLimPeso() {
        return limPeso;
    }

    public void setLimPeso(double limPeso) {
        this.limPeso = limPeso;
    }
    
    public ArrayList<Objeto> getInventario() {
        ArrayList<Objeto> completo = new ArrayList<>();
        
        completo.addAll(this.armas);
        completo.addAll(this.armaduras);
        completo.addAll(this.pociones);
        completo.addAll(this.miscelaneos);
        
        return completo;
        
    } 
}
