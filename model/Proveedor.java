public class Proveedor {
    // ========= ATRIBUTOS ==========
    private String identificador;
    private String nombre;
    private int telefono;

    // ========= CONSTRUCTOR ========
    public Proveedor(String identificador, String nombre, int telefono){
        this.identificador = identificador;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // ========= GETTERS ============
    public String getIdentificador(){return this.identificador;}
    public String getNombre(){return this.nombre;}
    public int getTelefono(){return this.telefono;}
}