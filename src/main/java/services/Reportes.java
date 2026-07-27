package services;

import java.util.Map;
import model.*;
import repository.*;
import util.InputValidator;
public class Reportes {
    InputValidator inputValidator = new InputValidator();

    // ======== COLECCIÓNES DESDE REPOSITORY ==========
    private final ProductoRepository producto = new ProductoRepository();
    private final ProveedorRepository proveedor = new ProveedorRepository();

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
}
