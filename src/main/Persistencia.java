package main;

import Usuarios.Usuario;
import Personajes.Personaje;
import Personajes.Guerrero;
import Personajes.Mago;
import Personajes.Clerigo;
import Personajes.estadoPersonaje;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;

public class Persistencia {
    
    // GUARDAR USUARIOS Y SUS PERSONAJES
    public static void guardarUsuarios(HashMap<String, Usuario> usuarios) {
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("usuarios.csv"));
            
            // Por cada usuario
            for (Usuario u : usuarios.values()) {
                // Guardar datos del usuario
                pw.print(u.getNombre() + ",");
                pw.print(u.getApellido() + ",");
                pw.print(u.getApodo() + ",");
                pw.print(u.getCorreo() + ",");
                pw.print(u.getClave() + ",");
                pw.print(u.getFnacimiento());
                
                // Guardar los personajes del usuario (separados por ;)
                ArrayList<Personaje> personajes = u.getMisPersonajes();
                for (Personaje p : personajes) {
                    pw.print(";" + p.getClase() + ",");
                    pw.print(p.getFuerza() + ",");
                    pw.print(p.getMana() + ",");
                    pw.print(p.getDefensa() + ",");
                    pw.print(p.getAgilidad() + ",");
                    pw.print(p.getVidaHP() + ",");
                    pw.print(p.getNombre() + ",");
                    pw.print(p.getNivel() + ",");
                    pw.print(p.getExp() + ",");
                    pw.print(p.getEstado().name());
                }
                
                pw.println(); // Nueva línea para el siguiente usuario
            }
            
            pw.close();
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
    
    // CARGAR USUARIOS Y SUS PERSONAJES
    public static HashMap<String, Usuario> cargarUsuarios() {
        HashMap<String, Usuario> usuarios = new HashMap<>();
        File archivo = new File("usuarios.csv");
        
        if (!archivo.exists()) {
            System.out.println("Archivo no encontrado. Se empieza desde cero.");
            return usuarios;
        }
        
        try {
            BufferedReader br = new BufferedReader(new FileReader("usuarios.csv"));
            String linea;
            
            while ((linea = br.readLine()) != null) {
                // se divide por ; para separar usuario de sus personajes
                String[] partes = linea.split(";");
                
                // la primera parte son los datos del usuario
                String[] datosUsuario = partes[0].split(",");
                String nombre = datosUsuario[0];
                String apellido = datosUsuario[1];
                String apodo = datosUsuario[2];
                String correo = datosUsuario[3];
                String clave = datosUsuario[4];
                String fechaNac = datosUsuario[5];
                
                // se crea el usuario
                Usuario u = new Usuario(nombre, apellido, apodo, correo, fechaNac);
                
                // se cargan sus personajes (partes 1, 2, 3...)
                for (int i = 1; i < partes.length; i++) {
                    String[] datosPersonaje = partes[i].split(",");
                    
                    String clase = datosPersonaje[0];
                    int fuerza = Integer.parseInt(datosPersonaje[1]);
                    int mana = Integer.parseInt(datosPersonaje[2]);
                    int defensa = Integer.parseInt(datosPersonaje[3]);
                    int agilidad = Integer.parseInt(datosPersonaje[4]);
                    int vidaHP = Integer.parseInt(datosPersonaje[5]);
                    String nombreP = datosPersonaje[6];
                    int nivel = Integer.parseInt(datosPersonaje[7]);
                    int exp = Integer.parseInt(datosPersonaje[8]);
                    String estadoStr = datosPersonaje[9];
                    estadoPersonaje estado = estadoPersonaje.valueOf(estadoStr); 
                    
                    // Crear personaje segun su clase
                    Personaje p = null;
                    switch (clase) {
                        case "Guerrero":
                            p = new Guerrero(fuerza, mana, defensa, agilidad, vidaHP, nombreP, nivel, exp, estado);
                            break;
                        case "Mago":
                            p = new Mago(fuerza, mana, defensa, agilidad, vidaHP, nombreP, nivel, exp, estado);
                            break;
                        case "Clerigo":
                            p = new Clerigo(fuerza, mana, defensa, agilidad, vidaHP, nombreP, nivel, exp, estado);
                            break;
                    }
                    
                    // Agregar personaje al usuario
                    if (p != null) {
                        u.agregarPersonaje(p);
                    }
                }
                
                // Guardar usuario en el HashMap
                usuarios.put(correo, u);
            }
            
            br.close();
            System.out.println("Datos cargados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
        
        return usuarios;
    }
}