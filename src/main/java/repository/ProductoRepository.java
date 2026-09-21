package repository;
import java.util.*;
import model.*;
import java.math.BigDecimal;
//JACKSON Y EXCEPCIONES
import java.io.File;
import java.io.IOException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

// SQL:
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductoRepository {

    // ========== CREAR COLECCIONES DE PRODUCTOS ==========
    private final HashMap<Integer, Producto> mapaProductos = new HashMap<>();

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
        this.mapaProductos.put(producto.getIdProducto(), producto);
    }



    public void eliminarProducto(Integer idProducto){
        String sql = "DELETE FROM productos WHERE id_producto = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, idProducto);
            statement.executeUpdate();

        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        this.mapaProductos.remove(idProducto);
    }

    public HashMap<Integer, Producto> listarProductos(){
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
                this.mapaProductos.put(idProducto, producto);
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return this.mapaProductos;
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

    //=========== GUARDAR MAPA EN JSON ============
    public void guardarJson(){
        File productoJson = new File("data/producto.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            mapper
                .writerWithDefaultPrettyPrinter()
                .writeValue(productoJson,mapaProductos);
        } catch (IOException e) {
            System.err.println("Error al guardar productos en json");
        }
    }

    //============ CARGAR JSON ============
    public void cargarJson(){
        File cargarProductos = new File("data/producto.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            Map<Integer, Producto> datos = mapper.readValue(cargarProductos,
                new TypeReference<Map<Integer, Producto>>() {}
            );
            mapaProductos.clear();
            mapaProductos.putAll(datos);
        } catch (IOException e) {
            System.out.println("Error en cargar Productos");
        }
    }
}