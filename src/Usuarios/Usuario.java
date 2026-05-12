package Usuarios;

import Personajes.Personaje;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class Usuario {
    private String nombre;
    private String apellido;
    private String apodo;
    private String correo;
    private String fnacimiento;
    private String clave;
    private ArrayList<Personaje> misPersonajes;
    LocalDateTime ultimoAcceso;
    
    public Usuario(String nombre, String apellido, String apodo, String correo, String fnacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.correo = correo;
        this.fnacimiento = fnacimiento;
        this.clave = nombre.substring(0, 1).toUpperCase() + apellido.substring(0, 1).toLowerCase() + apodo;
        this.ultimoAcceso = LocalDateTime.now();
        this.misPersonajes = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public String getApodo() {
        return apodo;
    }

    public String getCorreo() {
        return correo;
    }

    public String getFnacimiento() {
        return fnacimiento;
    }
    

    public String getClave() {
        return clave;
    }
            
    public ArrayList<Personaje> getMisPersonajes() {
        return misPersonajes;
    }

    public void agregarPersonaje(Personaje nuevoPersonaje) {
        this.misPersonajes.add(nuevoPersonaje);
    }
    
    
    
}
