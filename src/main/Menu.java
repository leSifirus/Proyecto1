package main;
import Usuarios.Usuario;
import Personajes.Personaje;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.HashMap; 
public class Menu {
    static String correo; 
    Scanner sc = new Scanner(System.in);
    HashMap<String, Usuario> usuarios;
    HashMap<String, ArrayList <Personaje>> personajes = new HashMap<>();

    public Menu() {
        this.usuarios = new HashMap<>();
        this.personajes = new HashMap<>();
    }
    
    public int menuInicial() {
            System.out.println("\n=== MENU ===");
            System.out.println("1. Para registrarse");
            System.out.println("2. Para iniciar sesion");
            System.out.println("0. Salir");
            System.out.print("Elige una opcion: ");
          
        return  Integer.parseInt(sc.nextLine());
    }
    public void registrarUsuario() {
        System.out.println("Selecciona un nombre: ");
        String nombre = sc.nextLine();
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
            return;
        }
        Usuario usuario = new Usuario(nombre, apellido, apodo, correo, FecNacimiento);
        System.out.println("Tu clave es: " + usuario.getClave() + " |||GUARDALA|||");
        usuarios.put(correo, usuario);
        personajes.put(correo, new ArrayList<>());
        
    }
    public boolean iniciarSesion() {
        //INICIO DE SESION
        System.out.println("Ingrese correo: "); 
        correo = sc.nextLine();
        System.out.println("Ingrese contraseña: ");
        String clave = sc.nextLine();
        if (usuarios.containsKey(correo)) {
            Usuario usuarioRegistrado = usuarios.get(correo);
            if (usuarioRegistrado.getClave().equals(clave)) {
                System.out.println("INICIO DE SESION EXITOSO");
                return true;
            }
            else {
                return false;
            } 
        }
        else {
            return false;
        }             
            
    } 
    public int menuJuego() {
            System.out.println("\n===BIENVENIDO===");
            System.out.println("\n===MENU DE JUEGO===\n");
            System.out.println("1. Ver mis personajes");
            System.out.println("2. Crear personaje");
            System.out.println("3. Ver bodega de personajes");
            System.out.println("4. Ver perfil y estadisticas");
            System.out.println("0. Para salir");
        return Integer.parseInt(sc.nextLine());

}
    public void verPersonajes() {
        System.out.println("===MIS PERSONAJES===");
        ArrayList<Personaje> p = personajes.get(correo); 
        
        if (p == null || p.isEmpty()) {
            System.out.println("No tienes personajes creados.");
            return;
        }
        
        Collections.sort(p);
        for (Personaje personaje : p) {
            System.out.println(personaje.toString());
        }
    }
    public void crearPersonaje() {
        int opcion;
        System.out.println("===CREAR PERSONAJES===\n");
        System.out.println("1. Guerrero");
        System.out.println("2. Mago");
        System.out.println("3. Clerigo");
        opcion = Integer.parseInt(sc.nextLine());

        System.out.println("Elige el nombre de tu personaje: ");
        String nombrePersonaje = sc.nextLine();
        switch (opcion){
            case 1:
                Guerrero guerrero = new Guerrero(70, 120, 50, 20, 1001,nombrePersonaje, 1, 0, "Activo");
                personajes.get(correo).add(guerrero);
                break;

            case 2:
                Mago mago = new Mago(45, 280, 30, 30, 740,nombrePersonaje, 1, 0, "Activo");
                personajes.get(correo).add(mago);
                break;

            case 3: 
                Clerigo clerigo = new Clerigo(30, 250, 33, 28, 780,nombrePersonaje, 1, 0, "Activo");
                personajes.get(correo).add(clerigo);
                break;
            default: 
                System.out.println("clase no valida");
                break;
}
}
    public void verBodega() {
        System.out.println("===BODEGA===");
        //FALTA
    }
    public void verPerfil() {
        System.out.println("===PERFIL===");
        //FALTA
    }
    
}