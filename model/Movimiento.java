package model;

public class Movimiento {
    // ========= ATRIBUTOS ==========
    private String tipoMovimiento;
    private String codigoProducto;
    private int cantidad;
    private String fecha;
    private String hora;


    // ========= CONSTRUCTOR ========= 
    public Movimiento(String tipoMovimiento, String codigoProducto, int cantidad, String fecha, String hora){
        this.tipoMovimiento = tipoMovimiento;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.fecha = fecha;
        this.hora = hora;
    }

    // ========== GETTERS ============
    public String getTipoMovimiento(){return this.tipoMovimiento;}
    public String getCodigoProducro(){return this.codigoProducto;}
    public int getCantidad(){return this.cantidad;}
    public String getFecha(){return this.fecha;}
    public String getHora(){return this.hora;}
}
