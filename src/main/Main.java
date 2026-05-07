package main;
import java.util.Scanner;
import java.util.HashMap;  
public class Main {
    static String correo;
    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        HashMap<String, Usuario> usuarios = new HashMap<>();
        HashMap<String, Personaje> personajes = new HashMap<>();
        int opcion;
        int opPersonaje;
        String nombre = "";
        
        
        do {
         System.out.println("\n=== MENU ===");
         System.out.println("1. Para registrarse");
         System.out.println("2. Para iniciar sesion");
         System.out.println("0. Salir");
         System.out.print("Elige una opcion: ");
         opcion = Integer.parseInt(sc.nextLine());
         switch (opcion) {
            case 1: 
                    //REGISTRO
                    System.out.println("Selecciona un nombre: ");
                    nombre = sc.nextLine();
                    System.out.println("Selecciona un apellido: ");
                    String apellido = sc.nextLine();
                    System.out.println("Selecciona un apodo: ");
                    String apodo = sc.nextLine();
                    System.out.println("Selecciona un correo: ");
                    correo = sc.nextLine();
                    System.out.println("Selecciona un fecha de nacimiento: ");
                    String FecNacimiento = sc.nextLine();
                    if (usuarios.size() > 0 && usuarios.containsKey(correo)) {
                        System.out.println("ERROR!! Este correo ya esta registrado");
                    }
                    Usuario usuario = new Usuario(nombre, apellido, apodo, correo, FecNacimiento);
                    System.out.println("Tu clave es: " + usuario.getClave() + " |||GUARDALA|||");

                    usuarios.put(correo, usuario);
                    break;
            case 2: 
                    //INICIO DE SESION
                    System.out.println("Ingrese correo: "); 
                    correo = sc.nextLine();
                    System.out.println("Ingrese contraseña: ");
                    String clave = sc.nextLine();
                    if (usuarios.containsKey(correo)) {
                        Usuario usuarioRegistrado = usuarios.get(correo);
                        if (usuarioRegistrado.getClave().equals(clave)) {
                            System.out.println("INICIO DE SESION EXITOSO");
                             do {
                                System.out.println("\n===BIENVENIDO " + nombre + "===");
                                System.out.println("\n===MENU DE JUEGO===\n");
                                System.out.println("1. Ver mis personajes");
                                System.out.println("2. Crear personaje");
                                System.out.println("3. Ver bodega de personajes");
                                System.out.println("4. Ver perfil y estadisticas");
                                System.out.println("0. Para salir");
                                opcion = Integer.parseInt(sc.nextLine());

                                switch (opcion){
                                    case 1: 
                                        System.out.println("===MIS PERSONAJES===");
                                        break;
                                    case 2:
                                        System.out.println("===CREAR PERSONAJES===\n");
                                        System.out.println("1. Guerrero");
                                        System.out.println("2. Mago");
                                        System.out.println("3. Arquero");
                                        opPersonaje = Integer.parseInt(sc.nextLine());

                                        System.out.println("Elige el nombre de tu personaje: ");
                                        String nombrePersonaje = sc.nextLine();
                                        switch (opPersonaje){
                                            case 1: Guerrero guerrero = new Guerrero(70, 120, 50, 20, 1001,nombrePersonaje, 1, 0, "Activo");
                                            personajes.put(correo, guerrero);
                                            break;

                                            case 2: Mago mago = new Mago(45, 280, 30, 30, 740,nombrePersonaje, 1, 0, "Activo");
                                            personajes.put(correo, mago);
                                            break;

                                            case 3: Clerigo clerigo = new Clerigo(30, 250, 33, 28, 780,nombrePersonaje, 1, 0, "Activo");
                                            personajes.put(correo, clerigo);
                                            break;
                                        }   
                                        break;
                                    case 3:
                                        System.out.println("===BODEGA===");
                                        break;
                                    case 4:
                                        System.out.println("===PERFIL===");
                                        break;
                                    case 0:
                                        System.out.println("Adios");

                                }
            
            
            
        } while (opcion != 0);
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
       
        
        sc.close();
            }
    
}
