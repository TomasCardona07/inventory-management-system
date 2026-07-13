package repository;
import model.Movimiento;
import java.util.*;
public class MovimientoRepository {
    private ArrayList<Movimiento> historial = new ArrayList<>();

    // ========== AÑADIR MOVIMIENTO ============
    public void addMovimiento(String tipoMovimiento, String codigo, int cantidad ){
        historial.add(new Movimiento(tipoMovimiento, codigo, cantidad));
    }

    // ========== MOSTRAR PRODUCTOS ==========
    public ArrayList<Movimiento> getMovimientos(){
        return this.historial;
    }

    // ========== VERIFICAR SI EL ARRAY ESTA VACIO =========
    public boolean arrayVacio(){
        if (historial.isEmpty()) {
            return false;
        }
        return true;
    }
}
