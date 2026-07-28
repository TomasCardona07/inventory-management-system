package services;

import java.util.ArrayList;
import java.util.Map;
import java.util.Scanner;
import model.*;
import repository.*;
import util.InputValidator;

public class Registros {
    InputValidator inputValidator = new InputValidator();

    // ======== COLECCIÓNES DESDE REPOSITORY ==========


    // ========== CASE 1 DEL BLOQUE DE ENTRADAS: REGISTRAR PRODUCTO ============
    public void registrarProducto( Scanner scr, ProductoRepository producto){
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
    public void registrarProveedor(Scanner scr, ProveedorRepository proveedor){
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
    public void registrarEntrada(Scanner scr,ProductoRepository producto,ProveedorRepository proveedor, MovimientoRepository movimiento){
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
    public void registrarSalida(Scanner scr,ProductoRepository producto, MovimientoRepository movimiento){
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
    public void eliminarProducto(Scanner scr,ProductoRepository producto){
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
    public void eliminarProveedor(Scanner scr,ProveedorRepository proveedor){
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
}