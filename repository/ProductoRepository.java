package repository;
import model.Producto;

import java.util.ArrayList;

public class ProductoRepository {
    // ========= CREAR ARRAY DE PRODUCTOS ==========
    private final ArrayList<Producto> productos = new ArrayList<>();


    // ========= AGREGAR PRODUCTO AL ARRAY =========
    public void agregarProducto(Producto producto){
        this.productos.add(producto);
    }

    public boolean buscarCodigo(String codigo){
        boolean codigoEncontrado = false;
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                return codigoEncontrado = true;
            }
        }
        return codigoEncontrado;
    } 
}