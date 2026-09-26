package repository;
import java.util.*;
import model.Proveedor;

// SQL:
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProveedorRepository {
    private Connection connection;

    public ProveedorRepository(Connection connection) {
        this.connection = connection;
    }




    public Integer retornarIdProveedor(Integer identificador){
        String sql = "SELECT id_proveedor FROM proveedores WHERE id_proveedor = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, identificador);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return resultSet.getInt("id_proveedor");
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return null;
    }


    public void agregarProveedor(Proveedor proveedor){
        String sql = """
        INSERT INTO proveedores (id_proveedor, nombre_proveedor, telefono_proveedor)
        VALUES (?, ?, ?)
            """;
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, proveedor.getIdentificador());
            statement.setString(2, proveedor.getNombre());
            statement.setString(3, proveedor.getTelefono());
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }


    public void eliminarProveedor(Integer identificador){
        String sql = "DELETE FROM proveedores WHERE id_proveedor = ?";
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, identificador);
            statement.executeUpdate();
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
    }

    public HashMap<Integer, Proveedor> listarProveedores(){
        HashMap<Integer,Proveedor> listarProveedores = new HashMap<>();
        String sql = "SELECT * FROM proveedores";
        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Integer idProveedor = resultSet.getInt("id_proveedor");
                String nombreProveedor = resultSet.getString("nombre_proveedor");
                String telefonoProveedor = resultSet.getString("telefono_proveedor");
                Proveedor proveedor = new Proveedor(idProveedor, nombreProveedor, telefonoProveedor);
                listarProveedores.put(idProveedor, proveedor);
            }
        } catch (SQLException e) {
            System.err.println("Error al ejecutar la consulta: " + e.getMessage());
        }
        return listarProveedores;
    }
}
