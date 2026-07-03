package repository;
import java.util.ArrayList;
import model.Proveedor;

public class ProveedorRepository {
    // ========= CREAR ARRAY DE PROVEEDORES ==========
    private final ArrayList<Proveedor> proveedores = new ArrayList<>();


    // ======== AGREGAR NUEVO PROVEEDOR =========
    public void agregarProveedor(Proveedor proveedor){
        this.proveedores.add(proveedor);
    }
}
