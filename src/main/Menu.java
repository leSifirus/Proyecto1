package main;

import Inventario.Inventario;
import java.util.concurrent.ThreadLocalRandom;
import Inventario.Objeto;
import Misiones.Mision;
import Personajes.*;
import Usuarios.Usuario;
import Personajes.Personaje;
import Personajes.estadoPersonaje;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import java.util.HashMap;

public class Menu {
    
    private Usuario usuarioLogeado;
    private final ArrayList<Mision> catalogoMisiones;
    private final Objeto[] recompensas;
    
    Scanner sc = new Scanner(System.in);
    HashMap<String, Usuario> usuarios;
    public Menu() {
        cargarTodo();
        this.catalogoMisiones = Mision.crearCatalogo();
        this.recompensas = Mision.crearRecompensas();
    }
    public void guardarTodo() {
        try {
            ObjectOutputStream archivo = new ObjectOutputStream(new FileOutputStream("datos_juego.dat"));
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
        String correo = sc.nextLine();
        System.out.println("Selecciona un fecha de nacimiento: ");
        String FecNacimiento = sc.nextLine();
        if (!usuarios.isEmpty() && usuarios.containsKey(correo)) {
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
        String correo = sc.nextLine();
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
            System.out.println("4. Para ver catalogo de misiones");
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

            ArrayList<Personaje> activos = new ArrayList<>();
            int i = 1;

            for (Personaje personaje : lista) {
                // Vemos los personajes que estan activos
                if (personaje.getEstado() == estadoPersonaje.ACTIVO) {
                    System.out.printf(i+"- Nombre: "+personaje.getNombre()+", Clase: "+personaje.getClase()+", Nivel: "+personaje.getNivel()+", Estado: "+personaje.getEstado() +"\n");
                    activos.add(personaje); 
                    i++;
                }
            }

            System.out.println("\n[1-" + activos.size() + "]. Seleccionar personaje para ver detalle");
            System.out.println("6. Para ver bodega de personajes descartados");
            System.out.println("7. Para ver bodega de objetos (inventario general)");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");

            int seleccion = Integer.parseInt(sc.nextLine());
            // vemos el personaje seleccionado anteriormente dentro de las opciones validas
            if (seleccion >= 1 && seleccion <= activos.size()) {
                Personaje pElegido = activos.get(seleccion - 1);
                int opcion = verPersonajeDetalle(pElegido);

                if (opcion == -1 ) {
                    // Se cambia el estado a Eliminado
                    pElegido.setEstado(estadoPersonaje.ELIMINADO);
                    System.out.println("Personaje enviado a la bodega.");
                    guardarTodo();
                }
                else if (opcion >= 1) {
                    gestionarInventario(opcion, pElegido);
                }
            }
            else if (seleccion == 6) { verBodegaPersonajes(); }
            else if (seleccion == 7) { gestionarBodega(); }
            else if (seleccion == 0) { }
            else { System.out.println("Opcion invalida"); }
    }
    
    public int verPersonajeDetalle(Personaje personaje) {
        System.out.println("\n===FICHA DE PERSONAJE===");
        System.out.println(personaje.toString());
        Inventario inventario = personaje.getInventario();
        inventario.verInventarioCompleto();
        System.out.println("\n[1-" + inventario.getObjetos().size() + "]. Para usar o descartar un objeto");
        System.out.println("-1. para eliminar este personaje (enviar a bodega)");
        System.out.println("0. para volver");
        return Integer.parseInt(sc.nextLine());
    }
    
    public void gestionarInventario(int opcion, Personaje personaje) {
        ArrayList<Objeto> lista = personaje.getInventario().getObjetos();
        
        if (opcion > lista.size()) {
            System.out.println("Este objeto no existe");
            return;
        }
        Objeto objeto = lista.get(opcion - 1);
        
        System.out.println("Has seleccionado el objeto: " + objeto.getNombre());
        System.out.println("1. para usar");
        System.out.println("2. para eliminar (enviar a bodega)");
        System.out.println("0. para volver");
        System.out.println("opcion: ");
        int seleccion = Integer.parseInt(sc.nextLine());
        
        switch (seleccion) {
            case 1:
                Objeto sacado = personaje.getInventario().sacarObjeto(opcion - 1);
                objeto.usar(personaje);
                System.out.println("\nSe elimino este objeto de tu inventario");
                break;
            
            case 2:
                Objeto descartado = personaje.getInventario().sacarObjeto(opcion - 1);
                usuarioLogeado.getBodega().addObjeto(descartado);
                System.out.println(descartado.getNombre() + " Se ha enviado a bodega");
                break;
            case 3:    
                return;

            default:
                System.out.println("Opcion invalida\n");  
                break;
        }
        guardarTodo();
    }
   
    public void gestionarBodega() {
        if (usuarioLogeado.getBodega().getObjetos().isEmpty()) {
            System.out.println("La bodega esta vacia\n");
            return;
        }

        usuarioLogeado.getBodega().verInventarioCompleto();
        System.out.println("\n[1-" + usuarioLogeado.getBodega().getObjetos().size() + "]. Para recuperar o eliminar un objeto");
        System.out.println("0. para volver");
        System.out.print("Opcion: ");
        int opcion = Integer.parseInt(sc.nextLine());
        
        if (opcion == 0) {
            return;
        }
        
        if (opcion < 1 || opcion > usuarioLogeado.getBodega().getObjetos().size()) {
            System.out.println("ERROR: Elije un objeto valido");
            return;
        }
        
        System.out.println("\n1. Para recuperar a un personaje");
        System.out.println("2. Para eliminar permanentemente");
        System.out.println("0. Para volver");
        System.out.print("Seleccion: ");
        int seleccion = Integer.parseInt(sc.nextLine());
        
        switch (seleccion) {
            case 1:
                Objeto recuperar = usuarioLogeado.getBodega().getObjetos().get(opcion-1);
                
                // Aca se se elije a que personaje va el objeto
                ArrayList<Personaje> activos = new ArrayList<>();
                int i = 1;
                System.out.println("\n===ELIJE EL PERSONAJE QUE RECIBE EL OBJETO===");
                
                for (Personaje p : usuarioLogeado.getMisPersonajes()) {
                    // Filtramos los personajes activos
                    if (p.getEstado() == estadoPersonaje.ACTIVO) {
                        System.out.println(i+". "+p.getNombre()+" | Clase: "+p.getClase());
                        activos.add(p);
                        i++;
                    }
                }
                
                if (activos.isEmpty()) {
                    System.out.println("No tienes personajes activos para recibir el objeto");
                    break;
                }
                
                System.out.print("Elige un personaje (0 para cancelar): ");
                int pjSeleccion = Integer.parseInt(sc.nextLine());
                
                if (pjSeleccion > 0 && pjSeleccion <= activos.size()) {
                    Personaje pElegido = activos.get(pjSeleccion-1);// se saca el personaje de la lista de activos
                    
                    // Se intenta guardar el objeto en el inventario del personaje seleccionado
                    boolean sePudoGuardar = pElegido.getInventario().addObjeto(recuperar);

                    if (sePudoGuardar) { // si se pudo guardar, se saca  de la bodega y se agrega al inventario
                        usuarioLogeado.getBodega().sacarObjeto(opcion-1);
                        System.out.println("\nHas recuperado: " + recuperar.getNombre());
                        System.out.println("Se guardo en el inventario de " + pElegido.getNombre());
                        guardarTodo(); 
                    } else {
                        System.out.println("\nERROR: Espacio insuficiente en el inventario de " + pElegido.getNombre());
                    }
                }
                break;
                
            case 2: 
                usuarioLogeado.getBodega().getObjetos().remove(opcion - 1);
                System.out.println("Se ha eliminado el objeto permanentemente");
                guardarTodo();
                break;
                
            case 0: 
                return;
                
            default:
                System.out.println("Opcion invalida");
        }
    }
    public void verBodegaPersonajes() {
            System.out.println("\n===BODEGA DE PERSONAJES===");
            ArrayList<Personaje> lista = usuarioLogeado.getMisPersonajes();
            ArrayList<Personaje> descartados = new ArrayList<>();

            for (Personaje p : lista) {
                // metemos los personajes eliminados al arraylist descartados para operar en este
                if (p.getEstado() == estadoPersonaje.ELIMINADO) {
                    descartados.add(p);
                }
            }

            if (descartados.isEmpty()) {
                System.out.println("No hay personajes en la bodega");
                return;
            }

            int i = 1;
            for (Personaje p : descartados) {
                System.out.println(i + "- Nombre: " + p.getNombre() + ", Clase: " + p.getClase() + ", Nivel: " + p.getNivel());
                i++;
            }

            System.out.println("\n[1-" + descartados.size() + "]. Seleccionar personaje");
            System.out.println("0. Volver");
            System.out.print("Opcion: ");

            int seleccion = Integer.parseInt(sc.nextLine());

            if (seleccion >= 1 && seleccion <= descartados.size()) {
                Personaje pSeleccionado = descartados.get(seleccion - 1);
                System.out.println("\nHas seleccionado a: " + pSeleccionado.getNombre());
                System.out.println("1. Recuperar personaje");
                System.out.println("2. Eliminar permanentemente");
                System.out.println("0. Cancelar");
                System.out.print("Opcion: ");

                int accion = Integer.parseInt(sc.nextLine());

                if (accion == 1) {
                    int activos = 0;
                    for (Personaje p : lista) {
                        if (p.getEstado() == estadoPersonaje.ACTIVO) {
                            activos++;
                        }
                    }

                    if (activos >= 5) {
                        System.out.println("ERROR: Ya tienes el maximo de personajes activos. Elimina uno primero");
                    } else {
                        // Se cambia su estado a activo
                        pSeleccionado.setEstado(estadoPersonaje.ACTIVO);
                        System.out.println(pSeleccionado.getNombre().toUpperCase() + " ha sido recuperado");
                        guardarTodo();
                    }
                } else if (accion == 2) {
                    lista.remove(pSeleccionado);
                    System.out.println("Personaje eliminado permanentemente");
                    guardarTodo();
                }
            }
    }
    
    
    public void crearPersonaje() {
        ArrayList<Personaje> lista = usuarioLogeado.getMisPersonajes();

        // Validar maximo de personajes
        if (lista.size() >= 5) {
            System.out.println("ERROR: Ya tienes el maximo de 5 personajes activos");
            System.out.println("Debes eliminar o mover a bodega uno antes de crear otro");
            return;
        }

        int opcion;
        System.out.println("\n===CREAR PERSONAJES===\n");
        System.out.println("1. Melee (Cuerpo a cuerpo):");
        System.out.println("    2. Guerrero");
        System.out.println("    3. Clerigo");
        System.out.println("4. A Distancia:");
        System.out.println("    5. Arquero");
        System.out.println("    6. Mago");
        System.out.print("Elige una opcion: ");
        
        opcion = Integer.parseInt(sc.nextLine());

        // Validar que no elijan 1 o 4 ya que son solo titulos
        if (opcion == 1 || opcion == 4) {
            System.out.println("ERROR: Debes elegir una clase especifica (2, 3, 5 o 6)");
            return;
        }

        // Validar que ingrese una opcion valida
        if (opcion < 1 || opcion > 6) {
            System.out.println("Clase invalida");
            return;
        }

        System.out.print("Elige el nombre de tu personaje: ");
        String nombrePersonaje = sc.nextLine();

        // Validacion nombre
        if (nombrePersonaje.length() > 20) {
            System.out.println("ERROR: El nombre no puede superar los 20 caracteres");
            return;
        }

        if (nombrePersonaje.contains(" ")) {
            System.out.println("ERROR: El nombre no puede contener espacios");
            return;
        }

        // Se crean los personajes segun la opcion
        switch (opcion) {
            case 2:
                Guerrero guerrero = new Guerrero(70, 120, 50, 20, 1001, 0, nombrePersonaje, 1, 0, estadoPersonaje.ACTIVO);
                usuarioLogeado.agregarPersonaje(guerrero);
                break;
            case 3:
                Clerigo clerigo = new Clerigo(30, 250, 33, 28, 780, 0, nombrePersonaje, 1, 0, estadoPersonaje.ACTIVO);
                usuarioLogeado.agregarPersonaje(clerigo);
                break;
            case 5:
                Arquero arquero = new Arquero(90, 100, 22, 35, 690, 0, nombrePersonaje, 1, 0, estadoPersonaje.ACTIVO);
                usuarioLogeado.agregarPersonaje(arquero);
                break;
            case 6:
                Mago mago = new Mago(45, 280, 30, 30, 740, 0, nombrePersonaje, 1, 0, estadoPersonaje.ACTIVO);
                usuarioLogeado.agregarPersonaje(mago);
                break;
        }

        System.out.println("Personaje creado exitosamente\n");
        guardarTodo();
    }
    public void verCatalogo() {
    System.out.println("\n===CATALOGO DE MISIONES===\n");
    
    int i = 1;
    for (Mision m : catalogoMisiones) { // se muestran las misiones 
        System.out.println(i+". " + m.getNombre()+" | Nivel req: "+m.getNivel()+" | "+m.getOro()+" oro, "+m.getExp()+" exp");
        i++;
    }
    // se elige la mision
    System.out.println("\n[1-" + catalogoMisiones.size() + "]. Seleccionar mision");
    System.out.println("0. Volver");
    System.out.print("Opcion: ");
    
    int opcion = Integer.parseInt(sc.nextLine());
    
    if (opcion >= 1 && opcion <= catalogoMisiones.size()) { // valida que este dentro del rango y se selecciona el personaje para la mision
        Mision misionSeleccionada = catalogoMisiones.get(opcion-1);
        seleccionarPersonajeMision(misionSeleccionada);
    } else if (opcion == 0) {// se devuelve
    } else {
            System.out.println("Opcion invalida.");
        }
    }
    private void seleccionarPersonajeMision(Mision mision) {
        System.out.println("\n===MISION SELECCIONADA===");
        System.out.println(mision.toString());

        ArrayList<Personaje> personajes = usuarioLogeado.getMisPersonajes();
        ArrayList<Personaje> activos = new ArrayList<>();

        // filtramos solo los personajes activos
        for (Personaje p : personajes) {
            if (p.getEstado() == Personajes.estadoPersonaje.ACTIVO) {
                activos.add(p);
            }
        }

        if (activos.isEmpty()) {
            System.out.println("\nNo tienes personajes activos. Crea uno primero");
            System.out.println("Presiona ENTER para volver");
            sc.nextLine();
            return;
        }

        System.out.println("\n===ELIGE UN PERSONAJE PARA LA MISION===");
        int i = 1;
        for (Personaje p : activos) { // se muestran los personajes disponibles
            System.out.println(i+". "+p.getNombre()+" | Clase: "+p.getClase()+" | Nivel: "+p.getNivel()+" | Estado: "+p.getEstado());
            i++;
        }

        System.out.println("0. Volver");
        System.out.print("Opcion: ");

        int seleccion = Integer.parseInt(sc.nextLine());

        if (seleccion >= 1 && seleccion <= activos.size()) {// seleccionamos dentro del rango valido de personajes
            Personaje personaje = activos.get(seleccion-1);

            // Validamos si ya completo esta mision
            boolean yaCompletada = false;
            for (Mision m : personaje.getHistorialMisiones()) {
                if (m.getNombre().equals(mision.getNombre())) {
                    yaCompletada = true;
                    break;
                }
            }

            // la unica mision que se puede repetir con cualquier personaje es la mision 1, las demas solo una vez por personaje
            if (mision.getNivel() >= 2 && yaCompletada) { // 1caso, no puede hacer la mision el personaje seleccionado
                System.out.println("\nERROR: " + personaje.getNombre() + " ya ha completado esta mision");
                System.out.println("Elige otra mision o usa un personaje distinto");
            } 
            else if (mision.verificarNivel(personaje)) { // 2caso, puede hacer la mision
                completarMision(personaje, mision);
            } 
            else { // 3caso, no puede hacer la mision por falta de nivel
                System.out.println("\nEl personaje " + personaje.getNombre() + " no puede completar la mision");
                System.out.println("Nivel Requerido: " + mision.getNivel() + ", Nivel Actual: " + personaje.getNivel());
            }

        } else if (seleccion == 0) { //se devuelve al menu
        } else {
            System.out.println("Opcion invalida.");
        }
    }
    
    private void completarMision(Personaje personaje, Mision mision) {
        personaje.ganarXP(mision.getExp());
        personaje.ganarOro(mision.getOro());
        
        // agregamos la mision al historial
        personaje.getHistorialMisiones().add(mision);
        
        int numero = ThreadLocalRandom.current().nextInt(0, 10); // se saca un numero random
        Objeto recompensa = recompensas[numero]; // las recompensas se dan de manera aleatoria
            
        System.out.println("\nMISION COMPLETADA");
        System.out.println("+ "+mision.getExp()+" exp | + "+mision.getOro()+" oro");
        
        boolean sePudoGuardar = personaje.getInventario().addObjeto(recompensa);// guardamos el objeto al inventario, true si se pudo, false si no
        if (sePudoGuardar) {
            System.out.println("OBJETO NUEVO OBTENIDO: " + recompensa.getNombre() + "\nRareza: " + recompensa.getRareza());
        } 
        else {
            System.out.println("No tienes espacio. El objeto fue enviado a tu bodega general."); 
            usuarioLogeado.getBodega().addObjeto(recompensa);
        }
        guardarTodo();
    }
    
    
    public void verPerfil() {
        System.out.println("===PERFIL DE JUGADOR===");
        
        //Detalles de la cuenta
        System.out.println("Apodo: "+usuarioLogeado.getApodo());
        System.out.println("Nombre real: "+usuarioLogeado.getNombre()+" "+usuarioLogeado.getApellido());
        System.out.println("Correo: "+usuarioLogeado.getCorreo());
        System.out.println("Fecha de Nacimiento: "+usuarioLogeado.getFnacimiento());

        // Se sacan las estadisticas generales usando los personajes 
        ArrayList<Personaje> lista = usuarioLogeado.getMisPersonajes();
        int cantidadActivos = 0;
        int oroTotal = 0;
        int nivelMaximo = 0;

        for (Personaje p : lista) {
            // Tomamos los personajes activos y sacamos el oro total y el nivel maximo alcanzado
            if (p.getEstado() == estadoPersonaje.ACTIVO) {
                cantidadActivos++;
                oroTotal += p.getOro();
                if (p.getNivel() > nivelMaximo) {
                    nivelMaximo = p.getNivel();
                }
            }
        }
        
        // Mostramos las estadisticas generales
        System.out.println("\n===ESTADISTICAS GENERALES===");
        System.out.println("Personajes activos: " + cantidadActivos + "/5 permitidos");
        System.out.println("Oro total acumulado: " + oroTotal);
        System.out.println("Nivel maximo alcanzado: " + nivelMaximo);
        
        System.out.println("\n===HISTORIAL DE MISIONES===");
        if (cantidadActivos == 0) {
            System.out.println("No tienes personajes activos registrados");
        } else {
            for (Personaje p : lista) {
                if (p.getEstado() == estadoPersonaje.ACTIVO) {
                    System.out.println("\n=== PERSONAJE: " + p.getNombre().toUpperCase() + " (" + p.getClase() + " - Nivel " + p.getNivel() + ")===");
                    
                    if (p.getHistorialMisiones().isEmpty()) {
                        System.out.println("   No ha completado misiones aun");
                    } else {
                        for (Misiones.Mision m : p.getHistorialMisiones()) {
                            System.out.println("   Ha hecho la mision: " + m.getNombre());
                        }
                    }
                }
            }
        }
        System.out.println("\nPresiona ENTER para volver al menu de juego");
        sc.nextLine(); 
    }
}