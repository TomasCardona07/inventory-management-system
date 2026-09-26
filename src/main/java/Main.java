
import repository.*;
import service.*;
import controller.RegistroController;

// SQL:
import config.ConexionDB;
import java.sql.Connection;
import java.sql.SQLException;

// VERSION 4.0 POSTGRESQL DE MANERA LOCAL

public class Main {
    public static void main(String[] args) {

        try {
            ConexionDB conexionDB = new ConexionDB();
            Connection connection = conexionDB.conectar();
            ProductoRepository productoRepository = new ProductoRepository(connection);
            ProveedorRepository proveedorRepository = new ProveedorRepository(connection);
            MovimientoRepository movimientoRepository = new MovimientoRepository(connection);
            ProductoService productoServices = new ProductoService(productoRepository);
            ProveedorService proveedorServices = new ProveedorService(proveedorRepository);
            MovimientoService movimientoServices = new MovimientoService(movimientoRepository,productoRepository,proveedorRepository);
            RegistroController registroController = new RegistroController();
            registroController.flujoRegistro(productoServices,proveedorServices,movimientoServices);
        } catch (SQLException e) {
            System.err.println("Error al conectar a la base de datos: " + e.getMessage());
        }
    }
}