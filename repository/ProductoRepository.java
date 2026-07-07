package repository;
import model.Producto;
import java.util.ArrayList;

public class ProductoRepository {

    // ========== CREAR ARRAY DE PRODUCTOS ==========
    private final ArrayList<Producto> productos = new ArrayList<>();

    // ========= RETORNAR PRODUCTO ===========
    public Producto retornarProducto(String codigo){
        for (Producto producto : productos) {
            if (codigo.equals(producto.getCodigo())) {
                return producto;
            }
        }
        return null;
    }

    // ========== AGREGAR NUEVO PRODUCTO =========
    public void agregarProducto(Producto producto){
        this.productos.add(producto);
    }

    // =========== BUSCAR CÓDIGO =============
    public boolean buscarCodigo(String codigo){
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                return true;
            }
        }
        return false;
    } 

    // ========= AGREGAR STOCK DEL PRODUCTO =============
    public void agregarStock(int cantidad, String codigo){
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                producto.setAddCantidad(cantidad);
                break;
            }
        }
    } 

    // ========= ELIMINAR STOCK DEL PRODUCTO =============
    public boolean eliminarStock(int cantidad, String codigo){
        for (Producto producto : productos) {
            if (codigo.equals(producto.getCodigo())) {
                if (cantidad <= producto.getCantidad()) {
                    producto.setDeleteCantidad(cantidad);
                    return true;
                }
            }
        }
        return false;
    }

    // ============ ELIMINAR PRODUCTO ============
    public void eliminarProducto(String codigo){
        ArrayList<Producto> eliminarProductos = new ArrayList<>();
        for (Producto producto : productos) {
            if (codigo.equals(producto.getCodigo())) {
                eliminarProductos.add(producto);
                break;
            }
        }
        productos.removeAll(eliminarProductos);
    }


    // ========== MOSTRAR PRODUCTOS ==========
    public ArrayList<Producto> getProductos() {
        return productos;
    }
}