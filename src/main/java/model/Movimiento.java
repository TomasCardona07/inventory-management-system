package model;
public class Movimiento {
    // ========= ATRIBUTOS ==========
    private String tipoMovimiento;
    private String codigoProducto;
    private int cantidad;

    // ========== CONSTRUCTOR ========== 
    public Movimiento(String tipoMovimiento, String codigoProducto, int cantidad){
        this.tipoMovimiento = tipoMovimiento;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
    }
    // CONSTRUCTOR PARA JSON
    public Movimiento(){

    }

    // ========== GETTERS ============
    public String getTipoMovimiento(){return this.tipoMovimiento;}
    public String getCodigoProducto(){return this.codigoProducto;}
    public int getCantidad(){return this.cantidad;}

    // ========== SETTERS ============
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
