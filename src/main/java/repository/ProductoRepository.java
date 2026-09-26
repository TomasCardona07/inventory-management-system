package repository;
import java.util.*;
import model.*;
import java.math.BigDecimal;

// SQL:
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoRepository {



    private Connection connection;
    public ProductoRepository(Connection connection) {
        this.connection = connection;
    }

    public Integer retornarIdProducto(Integer idProducto){
        String sql = "SELECT id_producto FROM productos WHERE id_producto = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idProducto);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_producto");
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return null;
    }

    public void aumentarStock(Integer idProducto, Integer cantidad){
        String sql = """
                UPDATE productos
                SET cantidad_producto = cantidad_producto + ?
                WHERE id_producto = ?
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cantidad);
            preparedStatement.setInt(2, idProducto);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en ejecutar la consulta " + e.getMessage());
        }
    }

    public void disminuirStock(Integer idProducto, Integer cantidad){
        String sql = """
                UPDATE productos
                SET cantidad_producto = cantidad_producto - ?
                WHERE id_producto = ?
                """;
        try(PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cantidad);
            preparedStatement.setInt(2, idProducto);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error en ejecutar la consulta " + e.getMessage());
        }
    }

    public Integer stockActualProducto(Integer idProducto){ 
        Integer stockActual = null;      
        String sql = """
                SELECT cantidad_producto FROM productos WHERE id_producto = ?
                """;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)){
            preparedStatement.setInt(1, idProducto);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                stockActual = resultSet.getInt("cantidad_producto");
            } 
                   
        } catch (SQLException e) {
            System.err.println("Error al ejcutar la consulta " + e.getMessage());
        }
        return stockActual;
    }

    public void agregarProducto(Producto producto){
        String sql = """
            INSERT INTO productos (id_producto, nombre_producto, categoria_producto, cantidad_producto, precio_producto)
            VALUES (?, ?, ?, ?, ?)
                """;
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setInt(1, producto.getIdProducto());
                statement.setString(2, producto.getNombre());
                statement.setString(3, producto.getCategoria());
                statement.setInt(4, producto.getCantidad());
                statement.setBigDecimal(5, producto.getPrecio());
                statement.executeUpdate();
            } catch (SQLException e) {
                System.err.println("Error al ejecutar la consulta: " + e.getMessage());
            }
    }



    public void eliminarProducto(Integer idProducto){
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idProducto);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        
    }

    public HashMap<Integer, Producto> listarProductos(){
        HashMap<Integer,Producto> listarProductos = new HashMap<>();
        String sql = "SELECT * FROM productos";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer idProducto = resultSet.getInt("id_producto");
                String nombre = resultSet.getString("nombre_producto");
                String categoria = resultSet.getString("categoria_producto");
                Integer cantidad = resultSet.getInt("cantidad_producto");
                BigDecimal precio = resultSet.getBigDecimal("precio_producto");

                Producto producto = new Producto(idProducto, nombre, categoria, cantidad, precio);
                listarProductos.put(idProducto, producto);
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return listarProductos;
    }

    public Producto productoMayorStock() {
        String sql = "SELECT * FROM productos ORDER BY cantidad_producto DESC LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                Integer idProducto = resultSet.getInt("id_producto");
                String nombre = resultSet.getString("nombre_producto");
                String categoria = resultSet.getString("categoria_producto");
                Integer cantidad = resultSet.getInt("cantidad_producto");
                BigDecimal precio = resultSet.getBigDecimal("precio_producto");

                Producto producto = new Producto(idProducto, nombre, categoria, cantidad, precio);
                return producto;
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return null;
    }

    public Producto productoMenorStock() {
        String sql = "SELECT * FROM productos ORDER BY cantidad_producto ASC LIMIT 1";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                Integer idProducto = resultSet.getInt("id_producto");
                String nombre = resultSet.getString("nombre_producto");
                String categoria = resultSet.getString("categoria_producto");
                Integer cantidad = resultSet.getInt("cantidad_producto");
                BigDecimal precio = resultSet.getBigDecimal("precio_producto");

                Producto producto = new Producto(idProducto, nombre, categoria, cantidad, precio);
                return producto;
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return null;
    }


    public BigDecimal valorTotalInventario() {
        String sql = "SELECT SUM(cantidad_producto * precio_producto) AS valor_total FROM productos";
        try (PreparedStatement statement = connection.prepareStatement(sql);
            ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                return resultSet.getBigDecimal("valor_total");
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return null;
    }

    public HashMap<Integer, Producto> productosAgotados() {
        HashMap<Integer, Producto> productosAgotados = new HashMap<>();
        String sql = "SELECT * FROM productos WHERE cantidad_producto = 0";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer idProducto = resultSet.getInt("id_producto");
                String nombre = resultSet.getString("nombre_producto");
                String categoria = resultSet.getString("categoria_producto");
                Integer cantidad = resultSet.getInt("cantidad_producto");
                BigDecimal precio = resultSet.getBigDecimal("precio_producto");

                Producto producto = new Producto(idProducto, nombre, categoria, cantidad, precio);
                productosAgotados.put(idProducto, producto);
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return productosAgotados;
    }

    public HashMap<Integer, Producto> productosConMenosDeCincoUnidades() {
        HashMap<Integer, Producto> productosMenosCincoUnidades = new HashMap<>();
        String sql = "SELECT * FROM productos WHERE cantidad_producto < 5";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer idProducto = resultSet.getInt("id_producto");
                String nombre = resultSet.getString("nombre_producto");
                String categoria = resultSet.getString("categoria_producto");
                Integer cantidad = resultSet.getInt("cantidad_producto");
                BigDecimal precio = resultSet.getBigDecimal("precio_producto");

                Producto producto = new Producto(idProducto, nombre, categoria, cantidad, precio);
                productosMenosCincoUnidades.put(idProducto, producto);
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return productosMenosCincoUnidades;
    }
}