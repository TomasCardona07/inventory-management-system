package model;
import java.time.LocalDateTime;
public class Movimiento {
    // ========= ATRIBUTOS ==========
    private String tipoMovimiento;
    private String codigoProducto;
    private int cantidad;
    private LocalDateTime fecha;
    private int contadorEntradas;
    private int contadorSalidas;


    // ========= CONSTRUCTOR ========= 
    public Movimiento(String tipoMovimiento, String codigoProducto, int cantidad, int contadorEntradas, int contadorSalidas){
        this.tipoMovimiento = tipoMovimiento;
        this.codigoProducto = codigoProducto;
        this.cantidad = cantidad;
        this.fecha = LocalDateTime.now();
        this.contadorEntradas = contadorEntradas;
        this.contadorSalidas = contadorSalidas;
    }

    // ========== GETTERS ============
    public String getTipoMovimiento(){return this.tipoMovimiento;}
    public String getCodigoProducto(){return this.codigoProducto;}
    public int getCantidad(){return this.cantidad;}
    public LocalDateTime getFecha(){return this.fecha;}
    public int getContEntradas(){return this.contadorEntradas;}
    public int getContSalidas(){return this.contadorSalidas;}

    // ========== SETTERS ============
    public int agregarEntrada(){
        return contadorEntradas++;
    }

    public int agregarSalida(){
        return contadorSalidas++;
    }
}
