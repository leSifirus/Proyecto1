package Personajes;

public class aDistancia extends Personaje {

    public aDistancia(int fuerza, int mana, int defensa, int agilidad, int vidaHP, int oro, String nombre, int nivel, int exp, estadoPersonaje estado) {
        super(fuerza, mana, defensa, agilidad, vidaHP, oro, nombre, nivel, exp, estado);
    }
    @Override
    public String getClase(){
        return "aDistancia";
    }
    
}
