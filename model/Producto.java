public abstract class Producto{

    // ========= ATRIBUTOS ==========
    private String codigo;
    private String nombre;
    private String categoria;
    private int cantidad;

    // ========= CONSTRUCTOR ========
    public Producto (String codigo, String nombre, String categoria, int cantidad){
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
    }

    // ========= GETTERS ============
    public String getCodigo(){return this.codigo;}
    public String getNombre(){return this.nombre;}
    public String getCategoria(){return this.categoria;}
    public int getCantidad(){return this.cantidad;}
}