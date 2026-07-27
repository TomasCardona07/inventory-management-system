package services;
import util.*;
import model.*;
import repository.*;
import java.util.*;

public class InventarioService {
    Registros registros = new Registros();
    Reportes reportes = new Reportes();
    InputValidator inputValidator = new InputValidator();

    // ======== IMPORTANDO COLECCIÓNES DESDE REPOSITORY ==========
    private final MovimientoRepository movimiento = new MovimientoRepository();
    private final ProductoRepository producto = new ProductoRepository();
    private final ProveedorRepository proveedor = new ProveedorRepository();

    // ======== CARGAR JSON (Constructor) =========
    public InventarioService(){
        producto.cargarJson();
        movimiento.cargarJson();
        proveedor.cargarJson();
    }

    // ======== GUARDAR JSON (para el Main) =========
    public void guardarJson(){
        producto.guardarJson();
        movimiento.guardarJson();
        proveedor.guardarJson();
    }

    /*=======================================================
      ============= MENU PRINCIPAL: REGISTROS ===============
      =======================================================*/
    
    // ============ REGISTRAR PRODUCTO =============
    public void registrarProducto(Scanner scr){
        registros.registrarProducto(scr);
    }

    // ============ REGISTRAR PROVEEDOR =============
    public void registrarProveedor(Scanner scr){
        registros.registrarProveedor(scr);
    }

    // ============ REGISTRAR ENTRADA =============
    public void registrarEntrada(Scanner scr){
        registros.registrarEntrada(scr);
    }

    // ============ REGISTRAR SALIDA =============
    public void registrarSalida(Scanner scr){
        registros.registrarSalida(scr);
    }

    // ============ ELIMINAR PRODUCTO =============
    public void eliminarProducto(Scanner scr){
        registros.eliminarProducto(scr);
    }

    // ============ ELIMINAR PORVEEDOR =============
    public void eliminarProveedor(Scanner scr){
        registros.eliminarProveedor(scr);
    }

    /*=======================================================
      ================= MENU DE REPORTES ====================
      =======================================================*/
    
    // ========== CASE 1: MOSTRAR TODOS LOS PROVEEDORES REGISTRADOS =============
    public void proveedoresRegistrados(){
        reportes.proveedoresRegistrados();
    }


    // ========== CASE 2: MOSTRAR TODOS LOS PRODUCTOS REGISTRADOS =============
    public void productosRegistrados(){
        reportes.productosRegistrados();
    }

    // ========== CASE 3: MOSTRAR PRODUCTO CON MAYOR STOCK =============
    public void mayorStock(){
        reportes.mayorStock();
    }

    // ========== CASE 4: MOSTRAR PRODUCTO CON MENOR STOCK =============
    public void menorStock(){
        reportes.menorStock();
    }


    // ============= CASE 5: VALOR DEL INVENTARIO ============
    public void valorInventario(){
        reportes.valorInventario();
    }

   // ============= CASE 6: MOSTRAR PRODUCTOS AGOTADOS ============ 
    public void productosAgotados(){
        reportes.productosAgotados();
    }

   // ============= CASE 7: MOSTRAR PRODUCTOS CON MENOS DE 5 UNIDADES  ============ 
    public void productosEscasos(){
        reportes.productosEscasos();
    }

    /*=======================================================
      ================ MENU DE MOVIMIENTOS ==================
      =======================================================*/
    
    // ============= CASE 1: MOSTRAR TOTAL DE ENTRADAS REGISTRADAS  ============ 
    public void entradasRegistradas(){
        Map<String,Producto> productos = producto.getProductos();
        int sumaEntradas = 0;
        if (!movimiento.arrayVacio()) {
            for (Producto producto : productos.values()) {
                sumaEntradas += producto.getContadorEntradas();
            }
            System.out.println("ENTRADAS REGISTRADAS: " + sumaEntradas);
        }
        else{
            System.err.println("NO HAY MOVIMIENTOS REGISTRADOS");
        }
    }

    // ============= CASE 2: MOSTRAR TOTAL DE SALIDAS REGISTRADAS  ============ 
    public void salidasRegistradas(){
        Map<String,Producto> productos = producto.getProductos();
        int sumaSalidas = 0;
        if (!movimiento.arrayVacio()) {
            for (Producto producto : productos.values()) {
                sumaSalidas += producto.getContadorSalidas();
            }
            System.out.println("SALIDAS REGISTRADAS: " + sumaSalidas);
        }
        else{
            System.err.println("NO HAY MOVIMIENTOS REGISTRADOS");
        }
    }

    // ============= CASE 3: MOSTRAR ULTIMOS MOVIMIENTOS REGISTRADOS ============ 
    public void ultimosMovimientos(){
        ArrayList<Movimiento> movimientos = movimiento.getMovimientos();
        if (!movimiento.arrayVacio()) {
            try {
                for (int i = movimientos.size()-1; i >= movimientos.size()-5; i--){
                    if (!movimientos.get(i).getTipoMovimiento().equalsIgnoreCase("null")) {
                        System.out.println("TIPO DE MOVIMIENTO: " + movimientos.get(i).getTipoMovimiento());
                        System.out.println("CODIGO DEL PRODUCTO: " + movimientos.get(i).getCodigoProducto());
                        if (movimientos.get(i).getTipoMovimiento().equalsIgnoreCase("ENTRADA")) {
                            System.out.println("CANTIDAD INGRESADA: " + movimientos.get(i).getCantidad());
                        }
                        else{
                            System.out.println("CANTIDAD EXPORTADA: " + movimientos.get(i).getCantidad());
                        }
                        System.out.println("========================");
                    }
                }
            }catch (IndexOutOfBoundsException e) {
                System.out.println("ESTOS SON LOS ULTIMOS MOVIMIENTOS REGISTRADOS :)");
            }
        }
        else{
            System.err.println("NO HAY MOVIMIENTOS REGISTRADOS");
        }
    }

    // ============= CASE 4: MOSTRAR PRODUCTOS QUE NUNCA HAN TENIDO ENTRADAS ============ 
    public void productosSinEntradas(){
        Map<String,Producto> productos = producto.getProductos();
        int contadorTrue = 0;
        int contadorFalse = 0;
        if (!producto.mapaVacio()) {
            System.out.println("PRODUCTOS QUE NUNCA HAN TENIDO ENTRADAS:");
            for (Producto producto : productos.values()) {
                if (producto.getContadorEntradas() == 0) {
                    System.out.println("EL PRODUCTO CON CODIGO: " + producto.getCodigo());
                    System.out.println("===========================");
                    contadorTrue++;
                }
                else{
                    contadorFalse++;
                }
            }
            if (contadorFalse > 0 && contadorTrue == 0) {
                System.out.println("NO HAY PRODUCTOS QUE NUNCA HAN TENIDO ENTRADAS");
            }
        }
        else{
            System.err.println("NO HAY PRODUCTOS REGISTRADOS");
        }
    }

    // ============= CASE 5: MOSTRAR PRODUCTOS QUE NUNCA HAN TENIDO SALIDAS ============ 
    public void productosSinSalidas(){
        Map<String,Producto> productos = producto.getProductos();
        int contadorTrue = 0;
        int contadorFalse = 0;
        if (!producto.mapaVacio()) {
            System.out.println("PRODUCTOS QUE NUNCA HAN TENIDO SALIDAS:");
            for (Producto producto : productos.values()) {
                if (producto.getContadorSalidas() == 0) {
                    System.out.println("EL PRODUCTO CON CODIGO: " + producto.getCodigo());
                    System.out.println("===========================");
                    contadorTrue++;
                }
                else{
                    contadorFalse++;
                }
            }
            if (contadorFalse > 0 && contadorTrue == 0) {
                System.out.println("NO HAY PRODUCTOS QUE NUNCA HAN TENIDO SALIDAS");
            }
        }
        else{
            System.err.println("NO HAY PRODUCTOS REGISTRADOS");
        }
    }
}