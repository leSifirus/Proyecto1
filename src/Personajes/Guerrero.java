package Personajes;
import Personajes.Personaje;

public class Guerrero extends Personaje {

    public Guerrero(int fuerza, int mana, int defensa, int agilidad, int vidaHP, String nombre, int nivel, int exp, estadoPersonaje estado) {
        super(fuerza, mana, defensa, agilidad, vidaHP, nombre, nivel, exp, estado);
    }
    @Override
    public String getClase(){
        return "Guerrero";
    }
    
    
   
    
}
