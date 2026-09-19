package model;
public class Proveedor {
    // ========= ATRIBUTOS ===========
    private Integer identificador;
    private String nombre;
    private String telefono;

    // ========== CONSTRUCTOR ===========
    public Proveedor(Integer identificador, String nombre, String telefono){
        this.identificador = identificador;
        this.nombre = nombre;
        this.telefono = telefono;
    }
    // CONSTRUCTOR PARA JSON
    public Proveedor(){

    }

    // ========== GETTERS ============
    public Integer getIdentificador(){return this.identificador;}
    public String getNombre(){return this.nombre;}
    public String getTelefono(){return this.telefono;}

    // ========== SETTERS ============
    public void setIdentificador(Integer identificador){
        this.identificador = identificador;
    }
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
    public void setTelefono(String telefono){
        this.telefono = telefono;
    }
}