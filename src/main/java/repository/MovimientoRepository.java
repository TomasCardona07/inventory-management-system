package repository;
import java.util.*;

//JACKSON Y EXCEPCIONES
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import model.Movimiento;

//SQL
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
public class MovimientoRepository {
    private ArrayList<Movimiento> historial = new ArrayList<>();


    private Connection connection;
    public MovimientoRepository(Connection connection){
        this.connection = connection;
    }


    public void agregarMovimiento(Movimiento movimiento) throws SQLException {
        String sql = """
            INSERT INTO movimientos (id_movimiento, id_producto, id_proveedor, cantidad, tipo_movimiento, fecha)
            VALUES (?, ?, ?, ?, ?, ?)
            """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, movimiento.getIdMovimiento());
            statement.setInt(2, movimiento.getProducto());
            statement.setInt(3, movimiento.getProveedor());
            statement.setInt(4, movimiento.getCantidad());
            statement.setString(5, movimiento.getTipoMovimiento());
            statement.setString(6, movimiento.getFechaMovimiento());
            statement.executeUpdate();
        }
        this.historial.add(movimiento);
    }

    // ========== MOSTRAR MOVIMIENTOS ==========
    public ArrayList<Movimiento> getMovimientos(){
        return this.historial;
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
