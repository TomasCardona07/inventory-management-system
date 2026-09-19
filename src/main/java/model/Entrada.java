package model;

public class Entrada {
    private Integer idEntrada;
    private Producto codigoProducto;
    private Proveedor IdProveedor;
    private int cantidad;

    public Entrada(Integer idEntrada, Producto codigoProducto, Proveedor IdProveedor, int cantidad){
        this.idEntrada = idEntrada;
        this.codigoProducto = codigoProducto;
        this.IdProveedor = IdProveedor;
        this.cantidad = cantidad;
    }

    public Producto getCodigoProducto(){return this.codigoProducto;}
    public Proveedor getIdProveedor(){return this.IdProveedor;}
    public int getCantidad(){return this.cantidad;}
    public Integer getIdEntrada(){return this.idEntrada;}

    public void setCodigoProducto(Producto codigoProducto){
        this.codigoProducto = codigoProducto;
    }
    public void setIdProveedor(Proveedor IdProveedor){
        this.IdProveedor = IdProveedor;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
}
