package main;

import java.util.Scanner;
import java.util.HashMap;  
public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        int opcion;
        
        switch (menu.menuInicial()) {
            case 1: 
                menu.registrarUsuario();
                break;
            case 2:
                if (menu.iniciarSesion() == true){
            do {
            opcion = menu.menuJuego();
            switch (opcion) {
                case 1:
                    menu.verPersonajes();
                    break;
                case 2:
                    menu.crearPersonaje();
                    break;
                case 3:
                    menu.verBodega();
                    break;
                case 4:
                    menu.verPerfil();
                    break;
                }
            } while (opcion !=1);
            break;
        }
        
        
        
         
        }
        

    
}
}