package model;
import java.time.LocalDateTime;
public class Movimiento {
    // ========= ATRIBUTOS ==========
    private String tipoMovimiento;
    private String codigoProducto;
    private int cantidad;
    private LocalDateTime fecha;


    // ========= CONSTRUCTOR ========= 
    public Movimiento(String tipoMovimiento, String codigoProducto, int cantidad){
        this.tipoMovimiento = tipoMovimiento;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
    }

    // ========== GETTERS ============
    public String getTipoMovimiento(){return this.tipoMovimiento;}
    public String getCodigoProducto(){return this.codigoProducto;}
    public int getCantidad(){return this.cantidad;}
    public LocalDateTime getFecha(){return this.fecha;}
}
