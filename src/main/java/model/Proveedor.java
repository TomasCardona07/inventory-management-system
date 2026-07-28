package model;
public class Proveedor {
    // ========= ATRIBUTOS ===========
    private String identificador;
    private String nombre;
    private String telefono;

    // ========== CONSTRUCTOR ===========
    public Proveedor(String identificador, String nombre, String telefono){
        this.identificador = identificador;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    // CONSTRUCTOR PARA JSON
    public Proveedor(){

    }

    // ========== GETTERS ============
    public String getIdentificador(){return this.identificador;}
    public String getNombre(){return this.nombre;}
    public String getTelefono(){return this.telefono;}

    // ========== SETTERS ============
    public void setIdentificador(String identificador){
        this.identificador = identificador;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
}