package services;
import util.*;
import model.*;
import repository.*;
import java.util.*;

public class InventarioService {
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

    // ========== CASE 1 DEL BLOQUE DE ENTRADAS: REGISTRAR PRODUCTO ============
    public void registrarProducto( Scanner scr){
        System.out.println("Ingresa el codigo del producto");
        String codigo = inputValidator.idProductoRepetido(scr, producto);
        System.out.println("Ingresa el nombre del producto");
        String nombreProd = scr.nextLine();
        System.out.println("Ingresa la categoria en la que se encuentra el producto");
        String categoria = scr.nextLine();
        int cantidad = inputValidator.validarNegativos(scr, "Ingresa la cantidad",false);
        double precio = inputValidator.validarNegativos(scr, "Ingresa el precio del producto", true);
        producto.agregarProducto(codigo, new Producto(codigo, nombreProd, categoria, cantidad, precio,0,0));
        System.out.println("¡PRODUCTO REGISTRADO CON EXITO!");
    }

    // ========== CASE 2 DEL BLOQUE DE ENTRADAS: REGISTRAR PROVEEDOR ============
    public void registrarProveedor(Scanner scr){
        System.out.println("Ingresa el identificador del proveedor");
        String identificadorProv = inputValidator.idProveedorRepetido(scr, proveedor);
        System.out.println("Ingresa el nombre del proveedor");
        String nombreProv = scr.nextLine();
        System.out.println("Ingresa el numero del Proveedor");
        String telefono = scr.nextLine();
        proveedor.agregarProveedor(identificadorProv, new Proveedor(identificadorProv, nombreProv, telefono));
        System.out.println("¡PROVEEDOR REGISTRADO CON EXITO!");
    }


    // ========== CASE 3 DEL BLOQUE DE ENTRADAS: REGISTRAR ENTRADA ============
    public void registrarEntrada(Scanner scr){
        ArrayList<Movimiento> movimientos = movimiento.getMovimientos();
        Map<String,Producto> productos = producto.getProductos();
        System.out.println("Ingresa el identificador del proveedor");
        String identificador = scr.nextLine();
        Proveedor proveedorRepository = proveedor.retornarProveedor(identificador);
        if (proveedorRepository != null) {
            System.out.println("Ingresa el código del producto");
            String codigo = scr.nextLine();
            Producto productoRepository = producto.retornarProducto(codigo);
            if (productoRepository != null) {
                int cantRecibida = inputValidator.validarNegativos(scr, "Ingresa la cantidad recibida", false);
                productoRepository.aumentarCantidad(cantRecibida);
                Producto entradaProducto = productos.get(codigo);
                entradaProducto.agregarEntrada();
                movimientos.add(new Movimiento("ENTRADA", codigo, cantRecibida));
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
    public void registrarSalida(Scanner scr){
        ArrayList<Movimiento> movimientos = movimiento.getMovimientos();
        Map<String,Producto> productos = producto.getProductos();
        System.out.println("Ingrese el codigo del producto");
        String codigo = scr.nextLine();
        int cantidad;
        Producto productoRepository = producto.retornarProducto(codigo);
        if (productoRepository != null){
            do {
                cantidad = inputValidator.validarNegativos(scr, "Ingrese la cantidad que desea retirar", false);
                if (cantidad <= productoRepository.getCantidad()) {
                    productoRepository.disminiurCantidad(cantidad);
                    Producto salidaProducto = productos.get(codigo);
                    salidaProducto.agregarSalida();
                    movimientos.add(new Movimiento("SALIDA", codigo, cantidad));
                    System.out.println("SALIDA REGISTRADA");
                    break;
                }
                else{
                    System.err.println("LA CANTIDAD INGRESADA SOBREPASA EL STOCK DISPONIBLE");
                }
            } while (cantidad > productoRepository.getCantidad());
        }
        else{
            System.err.println("PRODUCTO INEXISTENTE");
        }
    }
    
    // ========== CASE 5 DEL BLOQUE DE ENTRADAS: ELIMINAR PRODUCTO ============
    public void eliminarProducto(Scanner scr){
        System.out.println("Ingrese el codigo del producto que dese eliminar");
        String codigo = scr.nextLine();
        Producto productoExistente = producto.retornarProducto(codigo);
        if (productoExistente != null) {
            producto.eliminarProducto(codigo);
            System.out.println("PRODUCTO ELIMINADO CON EXITO");
        }
        else{
            System.err.println("PRODUCTO NO EXISTENTE");
        }
    }

    // ========== CASE 6 DEL BLOQUE DE ENTRADAS: ELIMINAR PROVEEDOR ============
    public void eliminarProveedor(Scanner scr){
        System.out.println("Ingrese el identificador del proveedor que desee eliminar");
        String identificador = scr.nextLine();
        Proveedor proveedorExistente = proveedor.retornarProveedor(identificador);
        if (proveedorExistente != null) {
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
    public void proveedoresRegistrados(){
        Map<String,Proveedor> mapaProveedores = proveedor.getProveedores();
        boolean mapaVacio = producto.mapaVacio();
        if (!mapaVacio) {
            for (Proveedor proveedor : mapaProveedores.values()) {
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
    public void productosRegistrados(){
        Map<String,Producto> mapaProductos = producto.getProductos();
        boolean mapaVacio = producto.mapaVacio();
        if (!mapaVacio) {
            for (Producto producto : mapaProductos.values()) {
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
    public void mayorStock(){
        Map<String,Producto> mapaProductos = producto.getProductos();
        boolean mapaVacio = producto.mapaVacio();
        if (!mapaVacio) {
            int mayorStock = -1;
            String mayorStockCodigo = null;
            for (Producto producto : mapaProductos.values()) {
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
    public void menorStock(){
        Map<String,Producto> mapaProductos = producto.getProductos();
        boolean mapaVacio = producto.mapaVacio();
        if (!mapaVacio) {
            int menorStock = Integer.MAX_VALUE;
            String menorStockCodigo = null;
            for (Producto producto : mapaProductos.values()) {
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


    // ============= CASE 5: VALOR DEL INVENTARIO ============
    public void valorInventario(){
        Map<String,Producto> mapaProductos = producto.getProductos();
        boolean mapaVacio = producto.mapaVacio();
        if (!mapaVacio) {
            double sumaValor = 0;
            for (Producto producto : mapaProductos.values()) {
                sumaValor += (producto.getPrecio() * producto.getCantidad());
            }
            System.out.println("EL VALOR DEL INVENTARIO ES DE: " + sumaValor + " PESOS");
        }
        else{
            System.err.println("NO HAY PRODUCTOS REGISTRADOS");
        }
    }

   // ============= CASE 6: MOSTRAR PRODUCTOS AGOTADOS ============ 
    public void productosAgotados(){
        Map<String,Producto> mapaProductos = producto.getProductos();
        boolean mapaVacio = producto.mapaVacio();
        int contadorProductos = 0;
        if (!mapaVacio) {
            for (Producto producto : mapaProductos.values()) {
                if (producto.getCantidad() == 0) {
                    System.out.println("El producto con codigo: " + producto.getCodigo()+ " Esta agotado");
                    System.out.println("----------------------------------");
                    contadorProductos++;
                }
            }
            if (contadorProductos == 0) {
                System.out.println("NO HAY PRODUCTOS CON UNIDADES AGOTADAS");
            }
        }
        else{
            System.err.println("NO HAY PRODUCTOS REGISTRADOS");
        }
    }


   // ============= CASE 7: MOSTRAR PRODUCTOS CON MENOS DE 5 UNIDADES  ============ 
    public void productosEscasos(){
        Map<String,Producto> mapaProductos = producto.getProductos();
        boolean mapaVacio = producto.mapaVacio();
        int contadorProductos = 0;
        if (!mapaVacio) {
            for (Producto producto : mapaProductos.values()) {
                if (producto.getCantidad() < 5) {
                    System.out.println("El producto con código: " + producto.getCodigo());
                    System.out.println("Tiene " + producto.getCantidad() + " unidades disponibles");
                    System.out.println("===============================");
                    contadorProductos++;
                }
            }
            if (contadorProductos == 0) {
                System.out.println("NO HAY PRODUCTOS CON MENOS DE 5 UNIDADES");
            }
        }
        else{
            System.err.println("NO HAY PRODUCTOS REGISTRADOS");
        }
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