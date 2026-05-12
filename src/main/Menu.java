package main;

import Inventario.Inventario;
import Inventario.Objeto;
import Misiones.Mision;
import Personajes.Guerrero;
import Personajes.Mago;
import Personajes.Clerigo;
import Usuarios.Usuario;
import Personajes.Personaje;
import Personajes.estadoPersonaje;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.HashMap;

public class Menu {
    static String correo; 
    private Usuario usuarioLogeado;
    private Mision mision;
    private ArrayList<Mision> catalogoMisiones;
    
    Scanner sc = new Scanner(System.in);
    HashMap<String, Usuario> usuarios;

    public Menu() {
        cargarTodo();
        this.usuarioLogeado = null; 
        this.catalogoMisiones = Mision.crearCatalogo();
    }
    public void guardarTodo() {
        try {
            ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream("datos.dat"));
            archivo.writeObject(usuarios);
            archivo.close();
            System.out.println("Datos guardados.");
        } catch (IOException e) {
            System.out.println("Error al guardar datos.");
        }
    }
    
    public void cargarTodo() {
        try {
            ObjectInputStream archivo = new ObjectInputStream(new FileInputStream("datos_juego.dat"));
            usuarios = (HashMap<String, Usuario>) archivo.readObject();
            archivo.close();
            System.out.println("Datos cargados.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("No hay datos guardados.");
            usuarios = new HashMap<>();
        }
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
            System.out.println("ERROR: Este correo ya esta registrado");
            return;
        }
        Usuario usuario = new Usuario(nombre, apellido, apodo, correo, FecNacimiento);
        System.out.println("Tu clave es: " + usuario.getClave() + " |||GUARDALA|||");
        usuarios.put(correo, usuario);
        guardarTodo();
    }
    public boolean iniciarSesion() {
        System.out.println("Ingrese correo: "); 
        correo = sc.nextLine();
        System.out.println("Ingrese contraseña: ");
        String clave = sc.nextLine();

        if (usuarios.containsKey(correo)) {
            Usuario usuarioRegistrado = usuarios.get(correo);
            if (usuarioRegistrado.getClave().equals(clave)) {
                this.usuarioLogeado = usuarioRegistrado;
                System.out.println("INICIO DE SESION EXITOSO");
                return true;
            } else {
                return false;
            } 
        } else {
            System.out.println("ERROR: El correo no esta registrado");
            return false;
        }             
    }
    public int menuJuego() {
            System.out.println("\n===BIENVENIDO " + usuarioLogeado.getNombre().toUpperCase() + "===");
            System.out.println("\n===MENU DE JUEGO===\n");
            System.out.println("1. Ver mis personajes e inventarios");
            System.out.println("2. Crear personaje");
            System.out.println("3. Ver perfil y estadisticas");
            System.out.println("4. para ver catalogo de misiones");
            System.out.println("0. Para salir");
        return Integer.parseInt(sc.nextLine());

    }
    public void verPersonajes() {
    System.out.println("\n===MIS PERSONAJES===\n");
    
    ArrayList<Personaje> lista = usuarioLogeado.getMisPersonajes();
    
    if (lista == null || lista.isEmpty()) {
        System.out.println("No tienes personajes creados.");
        return;
        }
    Collections.sort(lista);
    
    // contador posicion personaje
    int i = 1;
    // Mostrar personajes de mayor nivel a menor
    for (Personaje personaje : lista) {
        System.out.printf(i+"- Nombre: "+personaje.getNombre()+", Clase: "+personaje.getClase()+", Nivel: "+personaje.getNivel()+", Estado: "+personaje.getEstado() +"\n");
        i++;
        }
    
    System.out.println("\n[1-" + lista.size() + "] Seleccionar personaje para ver detalle");
    System.out.println("[0] Volver");
    System.out.print("Opción: ");
    
    int seleccion = Integer.parseInt(sc.nextLine());
    
    if (seleccion >= 1 && seleccion <= lista.size()) {
        verPersonajeDetalle(lista.get(seleccion - 1));
        }
    else if (seleccion == 0) {// se devuelve 
        }
   else {
            System.out.println("Opcion invalida");
        }
    }
    
    public void verPersonajeDetalle(Personaje personaje) {
    System.out.println("\n===FICHA DE PERSONAJE===");
    System.out.println(personaje.toString());
    Inventario inventario = personaje.getInventario();
    inventario.verInventarioCompleto();
    System.out.println("\nPresiona ENTER para volver");
    sc.nextLine();
    }
    
    public void crearPersonaje() {
    ArrayList<Personaje> lista = usuarioLogeado.getMisPersonajes();
        
        if (lista.size() >= 5) {
            System.out.println("ERROR: Ya tienes el maximo de 5 personajes activos");
            System.out.println("Debes eliminar o mover a bodega uno antes de crear otro");
            return;
        }
        
        int opcion;
        System.out.println("===CREAR PERSONAJES===\n");
        System.out.println("1. Guerrero");
        System.out.println("2. Mago");
        System.out.println("3. Clerigo");
        opcion = Integer.parseInt(sc.nextLine());

        System.out.println("Elige el nombre de tu personaje: ");
        String nombrePersonaje = sc.nextLine();
        
        // validacion para que el nombre no tenga mas de 20 caracteres
        if (nombrePersonaje.length() > 20) { 
            System.out.println("ERROR: El nombre no puede superar los 20 caracteres.");
            return;
        }

        // validacion para que el nombre no tenga espacios
        if (nombrePersonaje.contains(" ")) {
            System.out.println("ERROR: El nombre no puede contener espacios.");
            return;
        }
        
        switch (opcion){
            case 1:
                Guerrero guerrero = new Guerrero(70, 120, 50, 20, 1001,0, nombrePersonaje, 1, 0, estadoPersonaje.ACTIVO);
                usuarioLogeado.agregarPersonaje(guerrero);
                break;

            case 2:
                Mago mago = new Mago(45, 280, 30, 30, 740,0, nombrePersonaje, 1, 0, estadoPersonaje.ACTIVO);
                usuarioLogeado.agregarPersonaje(mago);
                break;

            case 3: 
                Clerigo clerigo = new Clerigo(30, 250, 33, 28, 780,0, nombrePersonaje, 1, 0, estadoPersonaje.ACTIVO);
                usuarioLogeado.agregarPersonaje(clerigo);
                break;
            default: 
                System.out.println("Clase invalida");
                break;
        }
        guardarTodo();
    }
    public void verCatalogo() {
    System.out.println("\n=== CATALOGO DE MISIONES ===\n");
    
    int i = 1;
    for (Mision m : catalogoMisiones) {
        System.out.println(i + ". " + m.getNombre() + " | Nivel req: " + m.getNivel() + " | " + m.getOro() + " oro, " + m.getExp() + " exp");
        i++;
    }
    
    System.out.println("\n[1-" + catalogoMisiones.size() + "] Seleccionar mision");
    System.out.println("[0] Volver");
    System.out.print("Opcion: ");
    
    int opcion = Integer.parseInt(sc.nextLine());
    
    if (opcion >= 1 && opcion <= catalogoMisiones.size()) {
        Mision misionSeleccionada = catalogoMisiones.get(opcion-1);
        seleccionarPersonajeMision(misionSeleccionada);
    } else if (opcion == 0) {// se devuelve
    } else {
            System.out.println("Opcion invalida.");
        }
    }
    private void seleccionarPersonajeMision(Mision mision) {
        System.out.println("\n=== MISION SELECCIONADA ===");
        System.out.println(mision.toString());

        ArrayList<Personaje> personajes = usuarioLogeado.getMisPersonajes();

        if (personajes.isEmpty()) {
            System.out.println("\nNo tienes personajes. Crea uno primero.");
            System.out.println("Presiona ENTER para volver...");
            sc.nextLine();
            return;
        }

        System.out.println("\n=== ELIGE UN PERSONAJE PARA LA MISION ===");
        int i = 1;
        for (Personaje p : personajes) {
            System.out.println(i + ". " + p.getNombre() + " | Clase: " + p.getClase() + " | Nivel: " + p.getNivel() + " | Estado: " + p.getEstado());
            i++;
        }

        System.out.println("[0] Volver");
        System.out.print("Opcion: ");

        int seleccion = Integer.parseInt(sc.nextLine());

        if (seleccion >= 1 && seleccion <= personajes.size()) {
            Personaje personaje = personajes.get(seleccion - 1);

            if (mision.verificarNivel(personaje)) {
                completarMision(personaje, mision);
            } else {
                System.out.println("\n" + personaje.getNombre() + " es demasiado debil. Necesita nivel " + 
                                 mision.getNivel() + " (nivel actual: " + personaje.getNivel() + ")");
            }

        } else if (seleccion == 0) {
            return;
        } else {
            System.out.println("Opcion invalida.");
        }
    }
    private void completarMision(Personaje personaje, Mision mision) {
        personaje.setExp(personaje.getExp() + mision.getExp());
        personaje.setOro(personaje.getOro() + mision.getOro());
        mision.setEstado("COMPLETADA");
        guardarTodo();

        System.out.println("\nMISION COMPLETADA");
        System.out.println("+ " + mision.getExp() + " exp | + " + mision.getOro() + " oro");
    }
    
    
    public void verPerfil() {
        System.out.println("===PERFIL===");
        //FALTA
    }
}