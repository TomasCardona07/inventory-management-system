package repository;
import java.util.ArrayList;
import model.Proveedor;

public class ProveedorRepository {
    // ========= CREAR ARRAY DE PROVEEDORES ==========
    private final ArrayList<Proveedor> proveedores = new ArrayList<>();


    // ========== RETORNAR PROVEEDOR ===========
    public Proveedor retornarProveedor(String identificador){
        for (Proveedor proveedor : proveedores) {
            if (identificador.equals(proveedor.getIdentificador())) {
                return proveedor;
            }
        }
        return null;
    }

    // ========= AGREGAR NUEVO PROVEEDOR =========
    public void agregarProveedor(Proveedor proveedor){
        this.proveedores.add(proveedor);
    }


    // =========== BUSCAR IDENTIFICADOR ===========
    public boolean buscarIdentificador(String identificador){
        for (Proveedor proveedor : proveedores) {
            if (proveedor.getIdentificador().equals(identificador)) {
                return true;
            }
        }
        return false;
    }

    // ============= ELIMINAR PROVEEDOR =============
    public void eliminarProveedor(String identificador){
        ArrayList<Proveedor> eliminarProveedor = new ArrayList<>();
        for (Proveedor proveedor : proveedores) {
            if (identificador.equals(proveedor.getIdentificador())) {
                eliminarProveedor.add(proveedor);
                break;
            }
        }
        proveedores.removeAll(eliminarProveedor);
    }

    // ========== MOSTRAR PROVEEDORES ==========
    public ArrayList<Proveedor> getProveedores(){
        return proveedores;
    }
}
