package main;
import java.util.Scanner;
import java.util.HashMap;  
public class Main {
     
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        HashMap<String, Usuario> usuarios = new HashMap<>();
        int opcion;
        String correo;
        String nombre = "";
        do {
         System.out.println("\n=== MENU ===");
         System.out.println("1. Para registrarse");
         System.out.println("2. Para iniciar sesion");
         System.out.println("0. Salir");
         System.out.print("Elige una opciOn: ");
         opcion = Integer.parseInt(sc.nextLine());
         switch (opcion) {
            case 1: 
                    //REGISTRO
                    System.out.println("Selecciona un apodo: ");
                    String apodo = sc.nextLine();
                    System.out.println("Selecciona un nombre: ");
                    nombre = sc.nextLine();
                    System.out.println("Selecciona un apellido: ");
                    String apellido = sc.nextLine();
                    System.out.println("Selecciona un correo: ");
                    correo = sc.nextLine();
                    System.out.println("Selecciona un fecha de nacimiento: ");
                    String FecNacimiento = sc.nextLine();
                    String  clave = nombre.substring(0, 1).toUpperCase() + apellido.substring(0, 1).toLowerCase() + apodo;
                    System.out.println("Tu clave es: " + clave + " |||GUARDALA|||");
                    Usuario usuario = new Usuario(apodo, nombre, apellido, correo, FecNacimiento, clave );
                    usuarios.put(correo, usuario);
                    break;
            case 2: 
                    //INICIO DE SESION
                    System.out.println("Ingrese correo: "); 
                    correo = sc.nextLine();
                    System.out.println("Ingrese contraseña: ");
                    clave = sc.nextLine();
                    if (usuarios.containsKey(correo)) {
                        Usuario usuarioRegistrado = usuarios.get(correo);
                        if (usuarioRegistrado.getClave().equals(clave)) {
                            System.out.println("INICIO DE SESION CORRECTO");
                            opcion = 0;
                        }
                        else {
                            System.out.println("Clave incorrecta");
                        }  
                    }
                    else {
                        System.out.println("Inicio de sesion incorrecto");
                    }
                    break;
            case 0: System.out.println("Hasta luego"); break;
            default: System.out.println("Opción inválida");
         }
        } while (opcion != 0);
        do {
            System.out.println("\n===BIENVENIDO " + nombre + "===");
            System.out.println("\n===MENU DE JUEGO===\n");
            System.out.println("1. Ver mis personajes");
            System.out.println("2. Crear personaje");
            System.out.println("3. Ver bodega de personajes");
            System.out.println("4. Ver perfil y estadisticas");
            System.out.println("0. Para salir");
            opcion = Integer.parseInt(sc.nextLine());
            
            
            
        } while (opcion != 0);
        
        sc.close();
            }
    
}
