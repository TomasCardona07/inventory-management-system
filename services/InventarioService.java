package services;
import util.InputValidator;
import java.util.Scanner;
import model.*;
import repository.*;

public class InventarioService {
    private static final ProductoRepository producto = new ProductoRepository();
    private static final ProveedorRepository proveedor = new ProveedorRepository();

    // ========== CASE 1 DEL BLOQUE DE ENTRADAS: REGISTRAR PRODUCTO ============
    public static void case1Entradas( Scanner scr){
        System.out.println("Ingresa el codigo del producto");
        String codigo = InputValidator.idProductoRepetido(scr, producto);
        System.out.println("Ingresa el nombre del producto");
        String nombreProd = scr.nextLine();
        System.out.println("Ingresa la categoria en la que se encuentra el producto");
        String categoria = scr.nextLine();
        int cantidad = InputValidator.validarNegativos(scr, "Ingresa la cantidad");
        producto.agregarProducto(new Producto(codigo, nombreProd, categoria, cantidad));
        System.out.println("¡PRODUCTO REGISTRADO CON EXITO!");
    }

    // ========== CASE 2 DEL BLOQUE DE ENTRADAS: REGISTRAR PROVEEDOR ============
    public static void case2Entradas(Scanner scr){
        System.out.println("Ingresa el identificador del proveedor");
        String identificadorProv = InputValidator.idProveedorRepetido(scr, proveedor);
        System.out.println("Ingresa el nombre del proveedor");
        String nombreProv = scr.nextLine();
        System.out.println("Ingresa el numero del Proveedor");
        String telefono = scr.nextLine();
        proveedor.agregarProveedor(new Proveedor(identificadorProv, nombreProv, telefono));
        System.out.println("¡PROVEEDOR REGISTRADO CON EXITO!");
    }


    // ========== CASE 3 DEL BLOQUE DE ENTRADAS: REGISTRAR ENTRADA ============
    public static void case3Entradas(Scanner scr){
        System.out.println("Ingresa el identificador del proveedor");
        String identEntradaProv = scr.nextLine();
        boolean proveedorEncontrado = proveedor.buscarIdentificador(identEntradaProv);
        if (proveedorEncontrado == true) {
            System.out.println("Ingresa el código del producto");
            String codEntradaProd = scr.nextLine();
            boolean productoEncontrado = producto.buscarCodigo(codEntradaProd);
            if (productoEncontrado == true) {
                int cantRecibida = InputValidator.validarNegativos(scr, "Ingresa la cantidad recibida");
                producto.agregarStack(cantRecibida, codEntradaProd);
                System.out.println("¡ENTRADA REGISTRADA!");
            }
            else{
                System.err.println("PRODUCTO NO EXISTENTE");
            }
        }
        else{
            System.err.println("PROVEEDOR NO EXISTENTE");
        }
    }


    // ========== CASE 4 DEL BLOQUE DE ENTRADAS: REGISTRAR SALIDA ============
    public static void case4Entradas(Scanner scr){
        System.out.println("Ingrese el codigo del producto");
        String codigo = scr.nextLine();
        boolean productoEncontrado = producto.buscarCodigo(codigo);
        if (productoEncontrado == true) {
            producto.eliminarStack(scr, codigo);
            System.out.println("SALIDA REGISTRADA");
        }
        else{
            System.err.println("PRODUCTO INEXISTENTE");
        }
    }
    
    // ========== CASE 5 DEL BLOQUE DE ENTRADAS: ELIMINAR PRODUCTO ============
    public static void case5Entradas(Scanner scr){
        System.out.println("Ingrese el codigo del producto que dese eliminar");
        String codigo = scr.nextLine();
        boolean productoExistente = producto.buscarCodigo(codigo);
        if (productoExistente == true) {
            producto.eliminarProducto(codigo);
            System.out.println("PRODUCTO ELIMINADO CON EXITO");
        }
        else{
            System.err.println("PRODUCTO NO ENCONTRADO");
        }
    }

    // ========== CASE 6 DEL BLOQUE DE ENTRADAS: ELIMINAR PROVEEDOR ============
    public static void case6Entradas(Scanner scr){
        System.out.println("Ingrese el identificador del proveedor que dese eliminar");
        String identificador = scr.nextLine();
        boolean proveedorExistente = proveedor.buscarIdentificador(identificador);
        if (proveedorExistente == true) {
            proveedor.eliminarProveedor(identificador);
            System.out.println("PROVEEDOR ELIMINADO CON EXITO");
        }
        else{
            System.err.println("PROVEEDOR NO ENCONTRADO");
        }
    }
}
