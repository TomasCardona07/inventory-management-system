package repository;
import model.Movimiento;
import java.util.*;
public class MovimientoRepository {
    private ArrayList<Movimiento> historial = new ArrayList<>();

    // ========== AÑADIR MOVIMIENTO ============
    public void addMovimiento(String tipoMovimiento, String codigo, int cantidad ){
        historial.add(new Movimiento(tipoMovimiento, codigo, cantidad));
    }

    // ========== MOSTRAR MOVIMIENTOS ==========
    public ArrayList<Movimiento> getMovimientos(){
        return this.historial;
    }

    // ========== MOSTRAR PRODUCTOS ==========
    public void eliminarMovimiento(String codigo){
        for (int i = 0; i < historial.size(); i++){
            if (historial.get(i).getCodigoProducto().equalsIgnoreCase(codigo)) {
                historial.remove(i);
                break;
            }
        }
    }

    // ========== VERIFICAR SI EL ARRAY ESTA VACIO =========
    public boolean arrayVacio(){
        if (historial.isEmpty()) {
            return true;
        }
        return false;
    }
}
