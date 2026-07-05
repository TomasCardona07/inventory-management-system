package repository;
import model.Producto;
import util.*;
import java.util.ArrayList;
import java.util.Scanner;

public class ProductoRepository {

    // ========= CREAR ARRAY DE PRODUCTOS ==========
    private final ArrayList<Producto> productos = new ArrayList<>();


    // ========= AGREGAR NUEVO PRODUCTO =========
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

    // ======== AGREGAR STOCK DEL PRODUCTO =============
    public void agregarStock(int cantidad, String codigo){
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                producto.setAddCantidad(cantidad);
                break;
            }
        }
    } 

    // ======== ELIMINAR STOCK DEL PRODUCTO =============
    public void eliminarStock(Scanner scr, String codigo){
        int cantidad;
        eliminarProducto:
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                do {
                    cantidad = InputValidator.validarNegativos(scr, "Ingrese la cantidad que desea retirar");
                    if (cantidad > producto.getCantidad()) {
                        System.err.println("La cantidad supera el stock disponible");
                    }
                    else{
                        producto.setDeleteCantidad(cantidad);
                        break eliminarProducto;
                    }
                } while (cantidad > producto.getCantidad());
            }
        }
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
}