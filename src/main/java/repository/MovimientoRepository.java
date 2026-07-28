package repository;
import java.util.*;

//JACKSON Y EXCEPCIONES
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Movimiento;
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

    // ========== ELIMINAR MOVIMIENTO ==========
    public void eliminarMovimiento(String codigo){
        for (int i = 0; i < historial.size(); i++){
            if (historial.get(i).getCodigoProducto().equalsIgnoreCase(codigo)) {
                historial.remove(i);
                break;
            }
        }
    }

    // ========== VERIFICAR SI EL ARRAY ESTA VACIO =========
    public boolean arrayVacio (){
        return this.historial.isEmpty();
    }

    // ========= GUARDAR JSON =========
    public void guardarJson(){
        File movimiento = new File("data/movimiento.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper
            .writerWithDefaultPrettyPrinter()
            .writeValue(movimiento, historial);
        } catch (IOException e) {
            System.err.println("Error en guardar los movimientos");
        }
    }
    // ========= CARGAR JSON ==========
    public void cargarJson(){
        File movimiento = new File("data/movimiento.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            ArrayList<Movimiento> movimientos =
                mapper.readValue(movimiento,
                    new TypeReference <ArrayList<Movimiento>>() {} 
                );
            historial.clear();
            historial.addAll(movimientos);
        } catch (IOException e) {
            System.err.println("Error en cargar los movimientos");
        }
    }
}
