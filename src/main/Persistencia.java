package main;

import Usuarios.Usuario;
import java.io.*;
import java.util.HashMap;

public class Persistencia {
    
    private static final String ARCHIVO = "datos_juego.dat";
    
    // GUARDAR TODO
    public static void guardar(HashMap<String, Usuario> usuarios) {
        try {
            FileOutputStream fileOut = new FileOutputStream(ARCHIVO);
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(usuarios);
            out.close();
            fileOut.close();
            System.out.println("Datos guardados correctamente.");
        } catch (IOException e) {
            System.out.println("Error al guardar: " + e.getMessage());
        }
    }
    
    // CARGAR TODO
    public static HashMap<String, Usuario> cargar() {
        HashMap<String, Usuario> usuarios = new HashMap<>();
        File archivo = new File(ARCHIVO);
        
        if (!archivo.exists()) {
            System.out.println("Primera ejecución. Se crea archivo nuevo.");
            return usuarios;
        }
        
        try {
            FileInputStream fileIn = new FileInputStream(ARCHIVO);
            ObjectInputStream in = new ObjectInputStream(fileIn);
            usuarios = (HashMap<String, Usuario>) in.readObject();
            in.close();
            fileIn.close();
            System.out.println("Datos cargados correctamente.");
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error al cargar: " + e.getMessage());
        }
        
        return usuarios;
    }
}