package model;
public class Producto{


    private String codigo;
    private String nombre;
    private String categoria;
    private int cantidad;
    private double precio;
    private int entradas;
    private int salidas;

    public Producto (String codigo, String nombre, String categoria, int cantidad, double precio, int entradas, int salidas){
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
        this.entradas = entradas;
        this.salidas = salidas;
    }
    //CONSTRUCTOR PARA JSON (SIEMPRE)
    public Producto(){

    }


    public String getCodigo(){return this.codigo;}
    public String getNombre(){return this.nombre;}
    public String getCategoria(){return this.categoria;} //NOTA: los getters tienen que tener los mismos nombres que los setters para jackson
    public int getCantidad(){return this.cantidad;}
    public double getPrecio(){return this.precio;}
    public int getContadorEntradas(){return this.entradas;}
    public int getContadorSalidas(){return this.salidas;}


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
    public void setContadorEntradas(int entradas){
        this.entradas = entradas;
    }
    public void setsalidas(int salidas){
        this.salidas = salidas;
    }

    public int disminiurCantidad(int nCantidad){
        this.cantidad -= nCantidad;
        return cantidad;
    }


    public int aumentarCantidad(int nCantidad){
        this.cantidad += nCantidad;
        return cantidad;
    }

    public int agregarEntrada(){return entradas++;}

    
    public int agregarSalida(){return salidas++;}
}