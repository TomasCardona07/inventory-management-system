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
        boolean codigoEncontrado = false;
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                return codigoEncontrado = true;
            }
        }
        return codigoEncontrado;
    } 

    // ======== AGREGAR STACK DEL PRODUCTO =============
    public void agregarStack(int cantidad, String codigo){
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                producto.setAddCantidad(cantidad);
                break;
            }
        }
    } 

    // ======== ELIMINAR STACK DEL PRODUCTO =============
    public void eliminarStack(Scanner scr, String codigo){
        int cantidad;
        eliminarProducto:
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                do {
                    cantidad = InputValidator.validarNegativos(scr, "Ingrese la cantidad que desea retirar");
                    if (cantidad > producto.getCantidad()) {
                        System.err.println("La cantidad supera el stack disponible");
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