package repository;
import java.util.*;

import model.Movimiento;

//SQL
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class MovimientoRepository{ 


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
            statement.setInt(2, movimiento.getIdProducto());
            statement.setInt(3, movimiento.getIdProducto());
            statement.setInt(4, movimiento.getCantidad());
            statement.setString(5, movimiento.getTipoMovimiento());
            statement.setString(6, movimiento.getFechaMovimiento());
            statement.executeUpdate();
        }

    }

    public HashMap<Integer,Movimiento> mostrarCantidadTotalEntradas(){
        HashMap <Integer,Movimiento> mapaEntradas = new HashMap<>();
        String sql = "SELECT * FROM movimientos WHERE tipo_movimiento = 'entrada'";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);) {
            while (resultSet.next()) {
                Integer idMovimiento = resultSet.getInt("id_movimiento");
                Integer idProducto = resultSet.getInt("id_producto");
                Integer idProveedor = resultSet.getInt("id_proveedor");
                Integer cantidad = resultSet.getInt("cantidad");
                Movimiento movimiento = new Movimiento(idMovimiento,idProveedor,idProducto,"entrada",cantidad,"any");
                mapaEntradas.put(idMovimiento, movimiento);
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return mapaEntradas;
    }


    public HashMap<Integer,Movimiento> mostrarCantidadTotalSalidas(){
        HashMap <Integer,Movimiento> mapaSalidas = new HashMap<>();
        String sql = "SELECT * FROM movimientos WHERE tipo_movimiento = 'salida'";
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);) {
            while (resultSet.next()) {
                Integer idMovimiento = resultSet.getInt("id_movimiento");
                Integer idProducto = resultSet.getInt("id_producto");
                Integer idProveedor = resultSet.getInt("id_proveedor");
                Integer cantidad = resultSet.getInt("cantidad");
                Movimiento movimiento = new Movimiento(idMovimiento,idProveedor,idProducto,"salida",cantidad,"any");
                mapaSalidas.put(idMovimiento, movimiento);
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return mapaSalidas;
    }

    public ArrayList<Integer> productosSinEntradas(){
        ArrayList<Integer> mapaProductosSinEntradas = new ArrayList<>();
        String sql = """
                SELECT p.id_producto
                FROM productos p
                WHERE NOT EXISTS (
                    SELECT 1
                    FROM movimientos m
                    WHERE m.id_producto = p.id_producto
                      AND m.tipo_movimiento = 'entrada'
                )
                """;

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_producto");
                mapaProductosSinEntradas.add(id);
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta " + e.getMessage());
        }
        return mapaProductosSinEntradas;
    }

    public ArrayList<Integer> productosSinSalidas(){
        ArrayList<Integer> mapaProductosSinSalidas = new ArrayList<>();
        String sql = """
                SELECT p.id_producto
                FROM productos p
                WHERE NOT EXISTS (
                    SELECT 1
                    FROM movimientos m
                    WHERE m.id_producto = p.id_producto
                      AND m.tipo_movimiento = 'salida'
                )
                """;

        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);) {
            while (resultSet.next()) {
                Integer id = resultSet.getInt("id_producto");
                mapaProductosSinSalidas.add(id);
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta " + e.getMessage());
        }
        return mapaProductosSinSalidas;
    }
}
