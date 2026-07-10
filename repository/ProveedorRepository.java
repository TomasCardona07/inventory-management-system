package repository;
import java.util.*;
import model.Proveedor;

public class ProveedorRepository {
    // ========= CREAR COLECCIONES DE PROVEEDORES ==========
    private final ArrayList<Proveedor> proveedores = new ArrayList<>();
    private final Map<String, Proveedor> mapaProveedores = new HashMap<>();


    // ========== RETORNAR PROVEEDOR ===========
    public Proveedor retornarProveedor(String identificador){
        Proveedor proveedor = mapaProveedores.get(identificador);
        return proveedor;
    }

    // ========= AGREGAR NUEVO PROVEEDOR =========
    public void agregarProveedor(String identificador, Proveedor proveedor){
        mapaProveedores.put(identificador, proveedor);
    }


    // ============= ELIMINAR PROVEEDOR =============
    public void eliminarProveedor(String identificador){
        mapaProveedores.remove(identificador);
    }

    // ========== MOSTRAR PROVEEDORES ==========
    public ArrayList<Proveedor> getProveedores(){
        return proveedores;
    }
}
