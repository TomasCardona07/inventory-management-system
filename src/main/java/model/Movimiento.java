package model;
public class Movimiento {
    private Salida salida;
    private Entrada entrada;
    private String codigoProducto;
    private int cantidad;

    public Movimiento(Salida salida, Entrada entrada, String codigoProducto, int cantidad){
        this.salida = salida;
        this.entrada = entrada;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
    }
    public Movimiento(){

    }

    public Salida getSalida(){return this.salida;}
    public Entrada getEntrada(){return this.entrada;}
    public String getCodigoProducto(){return this.codigoProducto;}
    public int getCantidad(){return this.cantidad;}

    // =========== SETTERS =============
    public void setCodigoProducto(String codigoProducto){
        this.codigoProducto = codigoProducto;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
}
