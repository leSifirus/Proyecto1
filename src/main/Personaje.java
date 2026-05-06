package main;
public class Personaje {
   protected int fuerza;
   protected int mana;
   protected int defensa;
   protected int agilidad;
   
   protected int vidaHP;
   protected int nivel;
   protected int exp;

    public Personaje(int fuerza, int mana, int defensa, int agilidad, int vidaHP, int nivel, int exp) {
        this.fuerza = fuerza;
        this.mana = mana;
        this.defensa = defensa;
        this.agilidad = agilidad;
        this.vidaHP = vidaHP;
        this.nivel = nivel;
        this.exp = exp;
    }
   
   
   
}
