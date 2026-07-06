package services;
import util.InputValidator;
import java.util.ArrayList;
import java.util.Scanner;
import model.*;
import repository.*;

public class InventarioService {
    private static final ProductoRepository producto = new ProductoRepository();
    private static final ProveedorRepository proveedor = new ProveedorRepository();

    /*=======================================================
      ============= MENU PRINCIPAL: REGISTROS ===============
      =======================================================*/

    // ========== CASE 1 DEL BLOQUE DE ENTRADAS: REGISTRAR PRODUCTO ============
    public static void registrarProducto( Scanner scr){
        System.out.println("Ingresa el codigo del producto");
        String codigo = InputValidator.idProductoRepetido(scr, producto);
        System.out.println("Ingresa el nombre del producto");
        String nombreProd = scr.nextLine();
        System.out.println("Ingresa la categoria en la que se encuentra el producto");
        String categoria = scr.nextLine();
        int cantidad = InputValidator.validarNegativos(scr, "Ingresa la cantidad",false);
        double precio = InputValidator.validarNegativos(scr, "Ingresa el precio del producto", true);
        producto.agregarProducto(new Producto(codigo, nombreProd, categoria, cantidad, precio));
        System.out.println("¡PRODUCTO REGISTRADO CON EXITO!");
    }

    // ========== CASE 2 DEL BLOQUE DE ENTRADAS: REGISTRAR PROVEEDOR ============
    public static void registrarProveedor(Scanner scr){
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
    public static void registrarEntrada(Scanner scr){
        System.out.println("Ingresa el identificador del proveedor");
        String identificador = scr.nextLine();
        Proveedor proveedorA = proveedor.retornarProveedor(identificador);
        if (proveedorA != null) {
            System.out.println("Ingresa el código del producto");
            String codigo = scr.nextLine();
            Producto productoA = producto.retornarProducto(codigo);
            if (productoA != null) {
                int cantRecibida = InputValidator.validarNegativos(scr, "Ingresa la cantidad recibida", false);
                productoA.setAddCantidad(cantRecibida);
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
    public static void registrarSalida(Scanner scr){
        System.out.println("Ingrese el codigo del producto");
        String codigo = scr.nextLine();
        boolean stockEliminado = false;
        boolean productoEncontrado = producto.buscarCodigo(codigo);
        if (productoEncontrado){
            do {
                int cantidad = InputValidator.validarNegativos(scr, "Ingrese la cantidad que desea retirar", false);
                stockEliminado = producto.eliminarStock(cantidad, codigo);
                if (stockEliminado) {
                    System.out.println("SALIDA REGISTRADA CON EXITO");
                }
                else{
                    System.err.println("LA CANTIDAD INGRESADA SOBREPASA EL STOCK DISPONIBLE");
                }
            } while (!stockEliminado);
        }
        else{
            System.err.println("PRODUCTO INEXISTENTE");
        }
    }
    
    // ========== CASE 5 DEL BLOQUE DE ENTRADAS: ELIMINAR PRODUCTO ============
    public static void eliminarProducto(Scanner scr){
        System.out.println("Ingrese el codigo del producto que dese eliminar");
        String codigo = scr.nextLine();
        boolean productoExistente = producto.buscarCodigo(codigo);
        if (productoExistente) {
            producto.eliminarProducto(codigo);
            System.out.println("PRODUCTO ELIMINADO CON EXITO");
        }
        else{
            System.err.println("PRODUCTO NO ENCONTRADO");
        }
    }

    // ========== CASE 6 DEL BLOQUE DE ENTRADAS: ELIMINAR PROVEEDOR ============
    public static void eliminarProveedor(Scanner scr){
        System.out.println("Ingrese el identificador del proveedor que desee eliminar");
        String identificador = scr.nextLine();
        boolean proveedorExistente = proveedor.buscarIdentificador(identificador);
        if (proveedorExistente) {
            proveedor.eliminarProveedor(identificador);
            System.out.println("PROVEEDOR ELIMINADO CON EXITO");
        }
        else{
            System.err.println("PROVEEDOR NO ENCONTRADO");
        }
    }

    /*=======================================================
      ================= MENU DE REPORTES ====================
      =======================================================*/
    
    // ========== CASE 1: MOSTRAR TODOS LOS PROVEEDORES REGISTRADOS =============
    public static void proveedoresRegistrados(){
        ArrayList<Proveedor> proveedores = proveedor.getProveedores();
        if (!proveedores.isEmpty()) {
            for (Proveedor proveedor : proveedores) {
                System.out.println("IDENTIFICADOR: " + proveedor.getIdentificador());
                System.out.println("NOMBRE: " + proveedor.getNombre());
                System.out.println("TELEFONO: " + proveedor.getTelefono());
            }
        }
        else{
            System.err.println("NO HAY PROVEEDORES REGISTRADOS");
        }
    }


    // ========== CASE 2: MOSTRAR TODOS LOS PRODUCTOS REGISTRADOS =============
    public static void productosRegistrados(){
        ArrayList<Producto> productos = producto.getProductos();
        if (!productos.isEmpty()) {
            for (Producto producto : productos) {
                System.out.println("CODIGO: " + producto.getCodigo());
                System.out.println("NOMBRE: " + producto.getNombre());
                System.out.println("CATEGORIA: " + producto.getCategoria());
                System.out.println("PRECIO: " + producto.getPrecio() + " PESOS");
                System.out.println("STOCK DISPONIBLE: " + producto.getCantidad() + " UNIDADES");
                System.out.println("==========================");
            }
        }
        else{
            System.err.println("NO HAY PRODUCTOS REGISTRADOS");
        }
    }

    // ========== CASE 3: MOSTRAR PRODUCTO CON MAYOR STOCK =============
    public static void mayorStock(){
        ArrayList<Producto> productos = producto.getProductos();
        if (!productos.isEmpty()) {
            int mayorStock = -1;
            String mayorStockCodigo = null;
            for (Producto producto : productos) {
                if (producto.getCantidad() > mayorStock) {
                    mayorStock = producto.getCantidad();
                    mayorStockCodigo = producto.getCodigo();
                }
            }
            System.out.println("EL PRODUCTO CON MAYOR STOCK ES: #" + mayorStockCodigo);
            System.out.println("CON " + mayorStock + " UNIDADES");
        }
        else{
            System.err.println("NO HAY PRODUCTOS REGISTRADOS");
        }
    }

    // ========== CASE 4: MOSTRAR PRODUCTO CON MENOR STOCK =============
    public static void menorStock(){
        ArrayList<Producto> productos = producto.getProductos();
        if (!productos.isEmpty()) {
            int menorStock = 999999999;
            String menorStockCodigo = null;
            for (Producto producto : productos) {
                if (producto.getCantidad() < menorStock) {
                    menorStock = producto.getCantidad();
                    menorStockCodigo = producto.getCodigo();
                }
            }
            System.out.println("EL PRODUCTO CON MENOR STOCK ES: #" + menorStockCodigo);
            System.out.println("CON " + menorStock + " UNIDADES");
        }
        else{
            System.err.println("NO HAY PRODUCTOS REGISTRADOS");
        }
    }


    // ============= CASE 5: SUMA DE LOS PRECIOS DE CADA PRODUCTO ============
    public static void valorInventario(){
        ArrayList<Producto> productos = producto.getProductos();
        if (!productos.isEmpty()) {
            double sumaPrecios = 0;
            for (Producto producto : productos) {
                sumaPrecios += producto.getPrecio();
            }
            System.out.println("EL VALOR DEL INVENTARIO ES DE: " + sumaPrecios + " PESOS");
        }
        else{
            System.err.println("NO HAY PRODUCTOS REGISTRADOS");
        }
    }
}
