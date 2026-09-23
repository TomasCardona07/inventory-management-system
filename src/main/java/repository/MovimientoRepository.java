package repository;
import java.util.*;

import model.Movimiento;

//SQL
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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

    public HashMap<Integer,Movimiento> mostrarCantidadTotalEntradas(){
        HashMap <Integer,Movimiento> mapaEntradas = new HashMap<>();
        String sql = "SELECT * FROM movimientos WHERE tipo_movimiento = 'entrada'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer idMovimiento = resultSet.getInt("id_movimiento");
                Integer idProducto = resultSet.getInt("id_producto");
                Integer idProveedor = resultSet.getInt("id_proveedor");
                Integer cantidad = resultSet.getInt("cantidad");
                Movimiento movimiento = new Movimiento(idMovimiento,idProveedor,idProducto,"entrada",cantidad,"any");
                mapaEntradas.put(idMovimiento, movimiento);
                return mapaEntradas;
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return null;
    }
    public HashMap<Integer,Movimiento> mostrarCantidadTotalSalidas(){
        HashMap <Integer,Movimiento> mapaSalidas = new HashMap<>();
        String sql = "SELECT * FROM movimientos WHERE tipo_movimiento = 'salidas'";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                Integer idMovimiento = resultSet.getInt("id_movimiento");
                Integer idProducto = resultSet.getInt("id_producto");
                Integer idProveedor = resultSet.getInt("id_proveedor");
                Integer cantidad = resultSet.getInt("cantidad");
                Movimiento movimiento = new Movimiento(idMovimiento,idProveedor,idProducto,"salida",cantidad,"any");
                mapaSalidas.put(idMovimiento, movimiento);
                return mapaSalidas;
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return null;
    }
}
