package main;

public class Main {
    public static void main(String[] args) {
        Menu menu = new Menu();
        int opcionInicial;
        
        do { // do-while principal que lleva el bucle completo 
            opcionInicial = menu.menuInicial();
            
            switch (opcionInicial) {
                case 1: 
                    menu.registrarUsuario();
                    break;
                case 2:
                    if (menu.iniciarSesion()) {
                        int opcionJuego;
                        do { // 2do do-while que lleva el flujo del juego, en el menu principal
                            opcionJuego = menu.menuJuego();
                            switch (opcionJuego) {
                                case 1:
                                    menu.verPersonajes();
                                    break;
                                case 2:
                                    menu.crearPersonaje();
                                    break;
                                case 3:
                                    menu.verPerfil();
                                    break;
                                case 0:
                                    menu.guardarTodo();
                                    System.out.println("Cerrando sesion");
                                    break;
                                default:
                                    System.out.println("Opción no válida");
                            }
                        } while (opcionJuego != 0);
                    } else {
                        System.out.println("ERROR: Correo o contraseña incorrectos");
                    }
                    break;
                    
                case 0:
                    System.out.println("Hasta luego");
                    break;
                    
                default:
                    System.out.println("Opcion invalida");
            }
        } while (opcionInicial != 0);
    }
}