package main;
import java.util.Scanner;
public class Main {
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int opcion;
        do {
         System.out.println("\n=== MENÚ ===");
         System.out.println("1. Para registrarse");
         System.out.println("2. Para iniciar sesion");
         System.out.println("0. Salir");
         System.out.print("Elige una opción: ");
         opcion = Integer.parseInt(sc.nextLine());
         switch (opcion) {
            case 1: 
                    System.out.println("Selecciona un apodo");
                    String apodo = sc.nextLine();
                    System.out.println("Selecciona un nombre");
                    String nombre = sc.nextLine();
                    System.out.println("Selecciona un apellido");
                    String apellido = sc.nextLine();
                    System.out.println("Selecciona un correo");
                    String correo = sc.nextLine();
                    System.out.println("Selecciona un fecha de nacimiento");
                    String FecNacimiento = sc.nextLine();
                    Usuario usuario = new Usuario(apodo, nombre, apellido, correo, FecNacimiento, clave);
            case 2: System.out.println("Elegiste B"); break;
            case 0: System.out.println("Hasta luego"); break;
            default: System.out.println("Opción inválida");
         }
        } while (opcion != 0);
        sc.close();
            }
    
}
