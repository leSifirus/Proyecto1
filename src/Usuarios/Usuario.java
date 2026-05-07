package Usuarios;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Usuario {
    private String nombre;
    private String apellido;
    private String apodo;
    private String correo;
    private String fnacimiento;
    private String clave;
    LocalDateTime ultimoAcceso;
    
    public Usuario(String nombre, String apellido, String apodo, String correo, String fnacimiento) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.apodo = apodo;
        this.correo = correo;
        this.fnacimiento = fnacimiento;
        this.clave = nombre.substring(0, 1).toUpperCase() + apellido.substring(0, 1).toLowerCase() + apodo;
        this.ultimoAcceso = LocalDateTime.now();
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
            
    
    
    
    
}
