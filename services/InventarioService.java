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
}
