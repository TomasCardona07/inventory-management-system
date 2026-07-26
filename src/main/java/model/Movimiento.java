package model;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
public class Movimiento {
    // ========= ATRIBUTOS ==========
    private String tipoMovimiento;
    private String codigoProducto;
    private int cantidad;
    private LocalDateTime fecha;


    // ========== CONSTRUCTOR ========== 
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
    public String getFechaFormateada() {
        DateTimeFormatter formateador = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        return this.fecha.format(formateador);
    }
}
