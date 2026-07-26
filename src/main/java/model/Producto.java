package model;
public class Producto{

    // ========= ATRIBUTOS ==========
    private String codigo;
    private String nombre;
    private String categoria;
    private int cantidad;
    private double precio;
    private int contadorEntradas;
    private int contadorSalidas;

    // ========= CONSTRUCTOR ==========
    public Producto (String codigo, String nombre, String categoria, int cantidad, double precio, int contadorEntradas, int contadorSalidas){
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
        this.contadorEntradas = contadorEntradas;
        this.contadorSalidas = contadorSalidas;
    }
    //CONSTRUCTOR PARA JSON
    public Producto(){

    }

    // ========== GETTERS ============
    public String getCodigo(){return this.codigo;}
    public String getNombre(){return this.nombre;}
    public String getCategoria(){return this.categoria;} //NOTA: los getters tienen que tener los mismos nombres que los setters para jackson
    public int getCantidad(){return this.cantidad;}
    public double getPrecio(){return this.precio;}
    public int getContadorEntradas(){return this.contadorEntradas;}
    public int getContadorSalidas(){return this.contadorSalidas;}

    // ========== SETTERS ============
    public void setCodigo(String codigo){
        this.codigo = codigo;
    }
    public void setNombre(String nombre){ //NOTA: jackson solo busca set y get para los atributos del objeto
        this.nombre = nombre;
    }
    public void setCategoria(String categoria){
        this.categoria = categoria;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
    public void setPrecio(double precio){
        this.precio = precio;
    }
    public void setContadorEntradas(int contadorEntradas){
        this.contadorEntradas = contadorEntradas;
    }
    public void setContadorSalidas(int contadorSalidas){
        this.contadorSalidas = contadorSalidas;
    }

        // ======== ELIMINAR STACK DEL PRODUCTO ========
    public int disminiurCantidad(int nCantidad){
        this.cantidad -= nCantidad;
        return cantidad;
    }


    //============================================
    //           METODOS DEL OBJETO
    //============================================
    // ======== AGREGAR STACK DEL PRODUCTO ========
    public int aumentarCantidad(int nCantidad){
        this.cantidad += nCantidad;
        return cantidad;
    }

    // ======== AGREGAR ENTRADA ========
    public int agregarEntrada(){return contadorEntradas++;}
    
    // ======== AGREGAR SALIDA ========
    public int agregarSalida(){return contadorSalidas++;}
}