package model;
public class Movimiento {
    private String tipoMovimiento;
    private String codigoProducto;
    private int cantidad;

    public Movimiento(String tipoMovimiento, String codigoProducto, int cantidad){
        this.tipoMovimiento = tipoMovimiento;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
    }
    public Movimiento(){

    }

    public String getTipoMovimiento(){return this.tipoMovimiento;}
    public String getCodigoProducto(){return this.codigoProducto;}
    public int getCantidad(){return this.cantidad;}

    // =========== SETTERS =============
    public void setTipoMovimiento(String tipoMovimiento){
        this.tipoMovimiento = tipoMovimiento;
    }
    public void setCodigoProducto(String codigoProducto){
        this.codigoProducto = codigoProducto;
    }
    public void setCantidad(int cantidad){
        this.cantidad = cantidad;
    }
}
