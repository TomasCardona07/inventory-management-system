package services;
import java.util.ArrayList;
import util.InputValidator;
import java.util.Scanner;
import model.*;

public class InventarioService {

    // ========== CASE 3 DEL BLOQUE DE ENTRADAS ============
    public static void case3Entradas(ArrayList<Proveedor> proveedores, ArrayList<Producto> productos, Scanner scr ){
        System.out.println("Ingresa el identificador del proveedor");
        String identEntradaProv = scr.nextLine();
        boolean proveedorEncontrado = false;
        for (Proveedor proveedor : proveedores) {
            if (identEntradaProv.equals(proveedor.getIdentificador())) {
                proveedorEncontrado = true;
                break;
            }
        }
        if (proveedorEncontrado == true) {
            System.out.println("Ingresa el código del producto");
            String codEntradaProd = scr.nextLine();
            boolean productoEncontrado = false;
            for (Producto producto : productos) {
                if (codEntradaProd.equals(producto.getCodigo())) {
                    productoEncontrado = true;
                    int cantRecibida = InputValidator.validarNegativos(scr, "Ingresa la cantidad recibida");
                    producto.setAddCantidad(cantRecibida);
                    System.out.println("¡ENTRADA REGISTRADA!");
                    break;
                }
            }
            if (productoEncontrado == false) {
                 System.err.println("PRODUCTO NO ENCONTRADO");
            }
        }
        else{
            System.err.println("PROVEEDOR NO ENCONTRADO");
        }
    }

    // ========== CASE 4 DEL BLOQUE DE ENTRADAS ============
    public static void case4Entradas(ArrayList<Producto> productos, Scanner scr){
        String codigo = null;
        int cantidad = 0;
        System.out.println("Ingrese el codigo del producto");
        codigo = scr.nextLine();
        boolean productoEncontrado = false;
        for (Producto producto : productos) {
            if (producto.getCodigo().equals(codigo)) {
                do {
                    cantidad = InputValidator.validarNegativos(scr, "Ingresa la cantidad que desea retirar");
                    if (cantidad <= producto.getCantidad()) {
                        producto.setDeleteCantidad(cantidad);
                        System.out.println("SALIDA REGISTRADA CON EXITO");
                        break;
                    }
                    else{
                        System.err.println("la cantidad supera el valor del stack disponible");
                    }
                } while (cantidad > producto.getCantidad());
                productoEncontrado = true;
                break;
            }
        }
        if (productoEncontrado == false) {
            System.err.println("Producto no encontrado");
        } 
    }
}
