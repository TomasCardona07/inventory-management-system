package repository;
import java.util.*;

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
}
