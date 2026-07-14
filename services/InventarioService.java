package services;
import util.InputValidator;
import java.util.*;
import model.*;
import repository.*;

public class InventarioService {
    InputValidator inputValidator = new InputValidator();
    // ======== IMPORTANDO COLECCIÓNES DESDE REPOSITORY ==========
    private final MovimientoRepository movimiento = new MovimientoRepository();
    private final ProductoRepository producto = new ProductoRepository();
    private final ProveedorRepository proveedor = new ProveedorRepository();

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
        movimiento.addMovimiento("null", codigo, cantidad,0,0);
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
        System.out.println("Ingresa el identificador del proveedor");
        String identificador = scr.nextLine();
        Proveedor proveedorRepository = proveedor.retornarProveedor(identificador);
        if (proveedorRepository != null) {
            System.out.println("Ingresa el código del producto");
            String codigo = scr.nextLine();
            Producto productoRepository = producto.retornarProducto(codigo);
            if (productoRepository != null) {
                int cantRecibida = inputValidator.validarNegativos(scr, "Ingresa la cantidad recibida", false);
                productoRepository.setAddCantidad(cantRecibida);
                System.out.println("¡ENTRADA REGISTRADA!");
                for (int i = 0; i < movimientos.size(); i++) {
                    if (movimientos.get(i).getTipoMovimiento().equalsIgnoreCase("null") || movimientos.get(i).getTipoMovimiento().equalsIgnoreCase("salida")) {
                        movimiento.addMovimiento("entrada", codigo, cantRecibida, movimientos.get(i).agregarEntrada(), 0);
                        //pendiente: idea pensada: agarrar el codigo del movimiento creado y editaro sin tener que crear otro
                        break;
                    }
                }
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
        System.out.println("Ingrese el codigo del producto");
        String codigo = scr.nextLine();
        int cantidad;
        Producto productoRepository = producto.retornarProducto(codigo);
        if (productoRepository != null){
            do {
                cantidad = inputValidator.validarNegativos(scr, "Ingrese la cantidad que desea retirar", false);
                if (cantidad <= productoRepository.getCantidad()) {
                    productoRepository.setDeleteCantidad(cantidad);
                    System.out.println("SALIDA REGISTRADA");
                    for (int i = 0; i < movimientos.size(); i++) {
                        if (movimientos.get(i).getTipoMovimiento().equalsIgnoreCase("null") || movimientos.get(i).getTipoMovimiento().equalsIgnoreCase("entrada")) {
                            movimiento.addMovimiento("SALIDA", codigo, cantidad, 0,movimientos.get(i).agregarSalida());
                            break;
                        }
                    }
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
        boolean mapaVacio = producto.mapaVacio(mapaProveedores);
        if (mapaVacio) {
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
        boolean mapaVacio = producto.mapaVacio(mapaProductos);
        if (mapaVacio) {
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
        boolean mapaVacio = producto.mapaVacio(mapaProductos);
        if (mapaVacio) {
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
        boolean mapaVacio = producto.mapaVacio(mapaProductos);
        if (mapaVacio) {
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
        boolean mapaVacio = producto.mapaVacio(mapaProductos);
        if (mapaVacio) {
            double sumaValor = 0;
            for (Producto producto : mapaProductos.values()) {
                sumaValor += producto.getPrecio() * producto.getCantidad();
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
        boolean mapaVacio = producto.mapaVacio(mapaProductos);
        int contadorProductos = 0;
        if (mapaVacio) {
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
        boolean mapaVacio = producto.mapaVacio(mapaProductos);
        int contadorProductos = 0;
        if (mapaVacio) {
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
        ArrayList<Movimiento> movimientos = movimiento.getMovimientos();
        int entradasRegistrads = 0;
        for (Movimiento movimiento : movimientos) {
            if (movimiento.getTipoMovimiento().equalsIgnoreCase("ENTRADA")) {
                entradasRegistrads++;
            }
        }
        if (movimiento.arrayVacio()) {
            System.out.println("El total de entradas registradas es: " + entradasRegistrads + " entradas");
        }
        else{
            System.err.println("NO HAY ENTRADAS REGISTRADSS");
        }
    }

    // ============= CASE 2: MOSTRAR TOTAL DE SALIDAS REGISTRADAS  ============ 
    public void salidasRegistradas(){
        ArrayList<Movimiento> movimientos = movimiento.getMovimientos();
        int salidasRegistrads = 0;
        for (Movimiento movimiento : movimientos) {
            if (movimiento.getTipoMovimiento().equalsIgnoreCase("salida")) {
                salidasRegistrads++;
            }
        }
        if (movimiento.arrayVacio()) {
            System.out.println("El total de salidas registradas es: " + salidasRegistrads + " entradas");
        }
        else{
            System.err.println("NO HAY SALIDAS REGISTRADAS");
        }
    }

    // ============= CASE 3: MOSTRAR ULTIMOS MOVIMIENTOS REGISTRADOS ============ 
    public void ultimosMovimientos(){
        ArrayList<Movimiento> movimientos = movimiento.getMovimientos();
        if (movimiento.arrayVacio()) {
            try {
                for (int i = movimientos.size()-1; i >= movimientos.size()-5; i--){
                    if (!movimientos.get(i).getTipoMovimiento().equalsIgnoreCase(null)) {
                        System.out.println("TIPO DE MOVIMIENTO: " + movimientos.get(i).getTipoMovimiento());
                        System.out.println("CODIGO DEL PRODUCTO: " + movimientos.get(i).getCodigoProducto());
                        System.out.println("FECHA Y HORA DEL MOVIMIENTO: " + movimientos.get(i).getFecha());
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
        ArrayList<Movimiento> movimientos = movimiento.getMovimientos();
        if (movimiento.arrayVacio()) {
            System.out.println("PRODUCTOS QUE NUNCA HAN TENIDO ENTRADAS:");
            try {
                for (Movimiento movimiento : movimientos) {
                    if (movimiento.getContEntradas() == 0) {
                        System.out.println("EL PRODUCTO CON CODIGO: " + movimiento.getCodigoProducto());
                    }
                }
            } catch (NullPointerException e) {
                System.err.println("");
            }
        }
        else{
            System.out.println("NO HAY PRODUCTOS REGISTRADOS");
        }
    }
}