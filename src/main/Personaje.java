package main;
public class Personaje {
   protected int fuerza;
   protected int mana;
   protected int defensa;
   protected int agilidad;
   protected int vidaHP;
   
   protected String nombre;
   protected int nivel;
   protected int exp;
   protected String estado;

    public Personaje(int fuerza, int mana, int defensa, int agilidad, int vidaHP, String nombre, int nivel, int exp, String estado) {
        this.fuerza = fuerza;
        this.mana = mana;
        this.defensa = defensa;
        this.agilidad = agilidad;
        this.vidaHP = vidaHP;
        this.nombre = nombre;
        this.nivel = nivel;
        this.exp = exp;
        this.estado = estado;
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

    public String getEstado() {
        return estado;
    }
    
   
    
   
}
