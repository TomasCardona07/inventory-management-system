package repository;
import java.util.*;
import model.*;
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
    private final Map<Integer, Producto> mapaProductos = new HashMap<>();

    private Connection connection;
    public ProductoRepository(Connection connection) {
        this.connection = connection;
    }

    public Integer retornarIdProducto(Integer idProducto){
        String sql = "SELECT id_producto FROM productos WHERE id_producto = ?";
        try {
            PreparedStatement statement = connection.prepareStatement(sql);
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
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
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



    // ========= RETORNAR PRODUCTO ===========
    public Producto retornarProducto(Integer idProducto){
        return mapaProductos.get(idProducto);
    }



    // ============ ELIMINAR PRODUCTO ============
    public void eliminarProducto(Integer idProducto){
        mapaProductos.remove(idProducto);
    }


    // ========== MOSTRAR PRODUCTOS ==========
    public Map<Integer,Producto> getProductos(){
        return this.mapaProductos;
    }


    // ========== VERIFICAR SI EL MAPA ESTA VACIO ==========
    public boolean mapaVacio(){
        return this.mapaProductos.isEmpty();
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