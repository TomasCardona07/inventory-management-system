package controller;

import java.util.Scanner;
import util.InputValidator;

import java.math.BigDecimal;
import model.*;
import service.*;


public class RegistroController {
    Scanner entrada = new Scanner(System.in);
    InputValidator inputValidator = new InputValidator();
    ReporteController reporteController = new ReporteController();


    public void flujoRegistro(ProductoService productoServices,ProveedorService proveedorServices ,MovimientoService movimientoServices){ 

        Boolean continuar = true;
        while (continuar) {

            int elegirRegistro = inputValidator.elegirRegistro(entrada);
            switch (elegirRegistro) {
                case 1:
                    System.out.println("REGISTRAR PRODUCTO");
                    try {
                        Integer idProducto = inputValidator.solicitarEntero(entrada, "Ingresa el código del producto");
                        String nombre = inputValidator.solicitarTextoValidado(entrada, "Ingresa el nombre del producto");
                        String categoria = inputValidator.solicitarTextoValidado(entrada, "Ingresa la categoría del producto");
                        int cantidad = inputValidator.solicitarEntero(entrada, "Ingresa la cantidad del producto");
                        BigDecimal precio = inputValidator.solicitarBigDecimalValidado(entrada, "Ingresa el precio del producto");
                        Producto producto = new Producto(idProducto, nombre, categoria, cantidad, precio);
                        productoServices.registrarProducto(producto);
                        System.out.println("¡Producto registrado correctamente!");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("REGISTRAR PROVEEDOR");
                    try {
                        Integer idProveedor = inputValidator.solicitarEntero(entrada, "Ingresa el identificador del proveedor");
                        String nombreProveedor = inputValidator.solicitarTextoValidado(entrada, "Ingresa el nombre del proveedor");
                        String telefonoProveedor = inputValidator.solicitarTextoValidado(entrada, "Ingresa el teléfono del proveedor");
                        Proveedor proveedor = new Proveedor(idProveedor, nombreProveedor, telefonoProveedor);
                        proveedorServices.registrarProveedor(proveedor);
                        System.out.println("¡Proveedor registrado correctamente!");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error " + e.getMessage());
                    }

                    break;
                case 3:
                    System.out.println("REGISTRAR ENTRADA");
                    try {
                        Integer idEntrada = inputValidator.solicitarEntero(entrada, "Ingresa el identificador de la entrada");
                        Integer idProveedorEntrada = inputValidator.solicitarEntero(entrada, "Ingresa el identificador del proveedor");
                        Integer idProductoEntrada = inputValidator.solicitarEntero(entrada, "Ingresa el código del producto existente");
                        String tipoMovimiento = "entrada";
                        int cantidadEntrada = inputValidator.solicitarEntero(entrada, "Ingresa la cantidad de entrada");
                        Movimiento movimientoEntrada = new Movimiento(idEntrada, idProveedorEntrada, idProductoEntrada, tipoMovimiento, cantidadEntrada, null);
                        movimientoServices.registrarMovimiento(movimientoEntrada);
                        System.out.println("¡Entrada registrada correctamente!");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error " + e.getMessage());
                    } catch(Exception e) {
                        System.err.println("Error al registrar la entrada: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("REGISTRAR SALIDA");
                    try {
                        Integer idSalida = inputValidator.solicitarEntero(entrada, "Ingresa el identificador de la salida");
                        Integer idProductoSalida = inputValidator.solicitarEntero(entrada, "Ingresa el código del producto existente");
                        String tipoMovimientoSalida = "salida";
                        int cantidadSalida = inputValidator.solicitarEntero(entrada, "Ingresa la cantidad de salida");
                        String fechaMovimientoSalida = inputValidator.solicitarTextoValidado(entrada, "Ingresa la fecha del movimiento (YYYY-MM-DD)");
                        Movimiento movimientoSalida = new Movimiento(idSalida,null,idProductoSalida, tipoMovimientoSalida, cantidadSalida, fechaMovimientoSalida);
                        movimientoServices.registrarMovimiento(movimientoSalida);

                        System.out.println("¡Salida registrada correctamente!");

                    } catch (IllegalArgumentException e) {
                        System.err.println("Error " + e.getMessage());
                    } catch(Exception e) {
                        System.err.println("Error al registrar la salida: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("ELIMINAR PRODUCTO");
                    try {
                        Integer idProductoEliminar = inputValidator.solicitarEntero(entrada, "Ingresa el código del producto a eliminar");
                        productoServices.eliminarProducto(idProductoEliminar);
                        System.out.println("¡Producto eliminado correctamente!");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error " + e.getMessage());
                    }
                    break;
                case 6:
                    System.out.println("ELIMINAR PROVEEDOR");
                    try {
                        Integer idProveedorEliminar = inputValidator.solicitarEntero(entrada, "Ingresa el identificador del proveedor a eliminar");
                        proveedorServices.eliminarProveedor(idProveedorEliminar);
                        System.out.println("¡Proveedor eliminado correctamente!");
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error " + e.getMessage());
                    }
                    break;
                case 7:
                    reporteController.flujoReporte(entrada,productoServices,proveedorServices,movimientoServices);
                    break;
                default:
                    System.out.println("GRACIAS POR USAR EL SISTEMA :)");
                    continuar = false;
            }
        }
        entrada.close();
    }
}