package model;
public class Producto{

    // ========= ATRIBUTOS ==========
    private String codigo;
    private String nombre;
    private String categoria;
    private int cantidad;
    private double precio;

    // ========= CONSTRUCTOR =========
    public Producto (String codigo, String nombre, String categoria, int cantidad, double precio){
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    // ========== GETTERS ============
    public String getCodigo(){return this.codigo;}
    public String getNombre(){return this.nombre;}
    public String getCategoria(){return this.categoria;}
    public int getCantidad(){return this.cantidad;}
    public double getPrecio(){return this.precio;}


    // ======== SETTER ELIMINAR STACK DEL PRODUCTO ========
    public int setDeleteCantidad(int nCantidad){
        this.cantidad -= nCantidad;
        return cantidad;
    }


    // ======== SETTER AGREGAR STACK DEL PRODUCTO ========
    public int setAddCantidad(int nCantidad){
        this.cantidad += nCantidad;
        return cantidad;
    }
}