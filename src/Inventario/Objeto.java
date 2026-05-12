package Inventario;

public class Objeto {
    private String nombre;
    private String descripcion;
    private String tipo;
    private Rareza rareza;
    private float peso;
    private int efecto;

    public Objeto(String nombre, String descripcion, String tipo, Rareza rareza, float peso, int efecto) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.tipo = tipo;
        this.rareza = rareza;
        this.peso = peso;
        this.efecto = efecto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Rareza getRareza() {
        return rareza;
    }

    public void setRareza(Rareza rareza) {
        this.rareza = rareza;
    }

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public int getEfecto() {
        return efecto;
    }

    public void setEfecto(int efecto) {
        this.efecto = efecto;
    }
    
    
}
