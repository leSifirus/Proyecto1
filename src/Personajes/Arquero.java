package Personajes;
public class Arquero extends aDistancia {
    
    public Arquero(int fuerza, int mana, int defensa, int agilidad, int vidaHP, int oro, String nombre, int nivel, int exp, estadoPersonaje estado) {
        super(fuerza, mana, defensa, agilidad, vidaHP, oro, nombre, nivel, exp, estado);
    }
    @Override
    public String getClase(){
        return "Arquero";
    }
}
