package repository;
import java.util.*;
import model.Proveedor;

public class ProveedorRepository {
    // ========= CREAR COLECCIONES DE PROVEEDORES ==========
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
    public Map<String,Proveedor> getProveedores(){
        return this.mapaProveedores;
    }

    // ========== VERIFICAR SI EL MAPA ESTA VACIO ==========
    public boolean mapaVacio(){
        return this.mapaProveedores.isEmpty();
    }
}
