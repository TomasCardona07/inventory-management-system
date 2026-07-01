public class Proveedor {
    // ========= ATRIBUTOS ==========
    private String identificador;
    private String nombre;
    private String telefono;

    // ========= CONSTRUCTOR ========
    public Proveedor(String identificador, String nombre, String telefono){
        this.identificador = identificador;
        this.nombre = nombre;
        this.telefono = telefono;
    }

    // ========= GETTERS ============
    public String getIdentificador(){return this.identificador;}
    public String getNombre(){return this.nombre;}
    public String gsetTelefono(){return this.telefono;}
}