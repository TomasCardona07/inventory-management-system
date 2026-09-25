package controller;
import util.InputValidator;
import java.util.Scanner;
import service.*;
import java.util.HashMap;
import model.*;
import java.math.BigDecimal;

public class ReporteController {
    InputValidator inputValidator = new InputValidator();
    ReporteMovimientoController movimientoController = new ReporteMovimientoController();

    
    public void flujoReporte(Scanner entrada,ProductoService productoService,ProveedorService proveedorServices,MovimientoService movimientoServices) {
        Boolean continuar = true;
        while(continuar) {
            int elegirReporte = inputValidator.elegirReporte(entrada);
            switch (elegirReporte) {
                case 1:
                    System.out.println("PROVEEDORES REGISTRADOS");
                    try {
                        HashMap<Integer, Proveedor> proveedores = proveedorServices.listarProveedores();
                        for (Proveedor proveedor : proveedores.values()) {
                            System.out.println("ID: " + proveedor.getIdentificador());
                            System.out.println("Nombre: " + proveedor.getNombre());
                            System.out.println("Teléfono: " + proveedor.getTelefono());
                            System.out.println("-------------------------");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }

                    break;
                case 2:
                    System.out.println("PRODUCTOS REGISTRADOS");
                    try {
                        HashMap<Integer, Producto> productos = productoService.listarProductos();
                        for (Producto producto : productos.values()) {
                            System.out.println("Código: " + producto.getIdProducto());
                            System.out.println("Nombre: " + producto.getNombre());
                            System.out.println("Categoría: " + producto.getCategoria());
                            System.out.println("Cantidad: " + producto.getCantidad());
                            System.out.println("Precio: " + producto.getPrecio());
                            System.out.println("-------------------------");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("PRODUCTO CON MAYOR STOCK");
                    try {
                        Producto productoMayorStock = productoService.productoConMayorStock();
                        System.out.println("Código: " + productoMayorStock.getIdProducto());
                        System.out.println("Nombre: " + productoMayorStock.getNombre());
                        System.out.println("Categoría: " + productoMayorStock.getCategoria());
                        System.out.println("Cantidad: " + productoMayorStock.getCantidad());
                        System.out.println("Precio: " + productoMayorStock.getPrecio());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("PRODUCTO CON MENOR STOCK");
                    try {
                        Producto productoMenorStock = productoService.productoConMenorStock();
                        System.out.println("Código: " + productoMenorStock.getIdProducto());
                        System.out.println("Nombre: " + productoMenorStock.getNombre());
                        System.out.println("Categoría: " + productoMenorStock.getCategoria());
                        System.out.println("Cantidad: " + productoMenorStock.getCantidad());
                        System.out.println("Precio: " + productoMenorStock.getPrecio());
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("VALOR DEL INVENTARIO");
                    try {
                        BigDecimal valorTotalInventario = productoService.valorTotalInventario();
                        System.out.println("El valor total del inventario es: " + valorTotalInventario);
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("PRODUCTOS AGOTADOS");
                    try {
                        HashMap<Integer, Producto> productos = productoService.productosAgotados();
                        for (Producto producto : productos.values()) {
                            System.out.println("Código: " + producto.getIdProducto());
                            System.out.println("Nombre: " + producto.getNombre());
                            System.out.println("Categoría: " + producto.getCategoria());
                            System.out.println("Cantidad: " + producto.getCantidad());
                            System.out.println("Precio: " + producto.getPrecio());
                            System.out.println("-------------------------");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 7:
                    System.out.println("PRODUCTOS CON MENOS DE 5 UNIDADES");
                    try {
                        HashMap<Integer, Producto> productos = productoService.productosConMenosDeCincoUnidades();
                        for (Producto producto : productos.values()) {
                            System.out.println("Código: " + producto.getIdProducto());
                            System.out.println("Nombre: " + producto.getNombre());
                            System.out.println("Categoría: " + producto.getCategoria());
                            System.out.println("Cantidad: " + producto.getCantidad());
                            System.out.println("Precio: " + producto.getPrecio());
                            System.out.println("-------------------------");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 8:
                    movimientoController.flujoMovimiento(entrada, movimientoServices, productoService, proveedorServices);
                    break;
                default:
                    continuar = false;
            }
        }
    }
}
