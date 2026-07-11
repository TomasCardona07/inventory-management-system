package repository;
import model.Producto;
import java.util.*;

public class ProductoRepository {

    // ========== CREAR COLECCIONES DE PRODUCTOS ==========
    private final Map<String, Producto> mapaProductos = new HashMap<>();

    // ========= RETORNAR PRODUCTO ===========
    public Producto retornarProducto(String codigo){
        Producto producto = mapaProductos.get(codigo);
        return producto;
    }

    // ========== AGREGAR NUEVO PRODUCTO =========
    public void agregarProducto(String codigo, Producto producto){
        this.mapaProductos.put(codigo, producto);
    }

    // ============ ELIMINAR PRODUCTO ============
    public void eliminarProducto(String codigo){
        mapaProductos.remove(codigo);
    }


    // ========== MOSTRAR PRODUCTOS ==========
    public Map<String,Producto> getProductos(){
        return this.mapaProductos;
    }


    // ========== VERIFICAR SI EL MAPA ESTA VACIO ==========
    public boolean mapaVacio(Map<?, ?>mapa){
        if (mapa.isEmpty()) {
            return false;
        }
        return true;
    }
}