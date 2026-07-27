package services;
import util.*;
import repository.*;
import java.util.*;

public class InventarioService {
    Registros registros = new Registros();
    Reportes reportes = new Reportes();
    Movimientos movimientos = new Movimientos();
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
        movimientos.entradasRegistradas();
    }

    // ============= CASE 2: MOSTRAR TOTAL DE SALIDAS REGISTRADAS  ============ 
    public void salidasRegistradas(){
        movimientos.salidasRegistradas();
    }

    // ============= CASE 3: MOSTRAR ULTIMOS MOVIMIENTOS REGISTRADOS ============ 
    public void ultimosMovimientos(){
        movimientos.ultimosMovimientos();
    }

    // ============= CASE 4: MOSTRAR PRODUCTOS QUE NUNCA HAN TENIDO ENTRADAS ============ 
    public void productosSinEntradas(){
        movimientos.productosSinEntradas();
    }

    // ============= CASE 5: MOSTRAR PRODUCTOS QUE NUNCA HAN TENIDO SALIDAS ============ 
    public void productosSinSalidas(){
        movimientos.productosSinSalidas();
    }
}