package model;

import java.math.BigDecimal;

public class Producto{


    private Integer codigo;
    private String nombre;
    private String categoria;
    private int cantidad;
    private BigDecimal precio;

    public Producto (Integer codigo, String nombre, String categoria, int cantidad, BigDecimal precio){
        this.codigo = codigo;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
    }

    public Producto(){

    }


    public Integer getCodigo(){return this.codigo;}
    public String getNombre(){return this.nombre;}
    public String getCategoria(){return this.categoria;}                                                   //NOTA: los getters tienen que tener los mismos nombres que los setters para jackson
    public int getCantidad(){return this.cantidad;}
    public BigDecimal getPrecio(){return this.precio;}


    public void setCodigo(Integer codigo){
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
    public void setPrecio(BigDecimal precio){
        this.precio = precio;
    }

    public int disminiurCantidad(int nCantidad){
        this.cantidad -= nCantidad;
        return cantidad;
    }


    public int aumentarCantidad(int nCantidad){
        this.cantidad += nCantidad;
        return cantidad;
    }
}