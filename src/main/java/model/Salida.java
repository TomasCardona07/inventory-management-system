package model;

public class Salida {
    private Integer idSalida;
    private Producto codigoProducto;
    private int cantidad;

    public Salida(Integer idSalida, Producto codigoProducto, int cantidad){
        this.idSalida = idSalida;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
    }

    public Integer getIdSalida(){return this.idSalida;}
    public Producto getCodigoProducto(){return this.codigoProducto;}
    public int getCantidad(){return this.cantidad;}

    public void setCodigoProducto(Producto codigoProducto){
        this.codigoProducto = codigoProducto;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
}
