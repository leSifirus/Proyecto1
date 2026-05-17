package Personajes;

public abstract class Melee extends Personaje {

    public Melee(int fuerza, int mana, int defensa, int agilidad, int vidaHP, int oro, String nombre, int nivel, int exp, estadoPersonaje estado) {
        super(fuerza, mana, defensa, agilidad, vidaHP, oro, nombre, nivel, exp, estado);
    }
    
    @Override
    public String getClase() {
        return "Melee";
    }
}