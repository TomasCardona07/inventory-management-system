package controller;
import util.InputValidator;
import java.util.Scanner;
import service.*;
import java.util.HashMap;
import model.*;
import java.util.ArrayList;

public class ReporteMovimientoController {
    InputValidator inputValidator = new InputValidator();

    public void flujoMovimiento(Scanner entrada, MovimientoService movimientoService, ProductoService productoService, ProveedorService proveedorService) {

        Boolean continuar = true;
        while (continuar) {
            int elegirMovimiento = inputValidator.elegirMovimiento(entrada);
            switch (elegirMovimiento) {
                case 1:
                    System.out.println("CANTIDAD TOTAL DE ENTRADAS REGISTRADAS");
                    try {
                        HashMap<Integer, Movimiento> cantidadTotalEntradas = movimientoService.mostrarCantidadTotalEntradas();
                        for (Movimiento movimiento : cantidadTotalEntradas.values()) {
                            System.out.println("Código del producto: " + movimiento.getProducto());
                            System.out.println("Id del proveedor: " + movimiento.getProveedor());
                            System.out.println("ID del producto: " + movimiento.getProducto());
                            System.out.println("Cantidad ingresada: " + movimiento.getCantidad());
                            System.out.println("Fecha: " + movimiento.getFechaMovimiento());
                            System.out.println("----------------------------------");
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("CANTIDAD TOTAL DE SALIDAS REGISTRADAS");
                    try {
                        HashMap<Integer, Movimiento> cantidadTotalSalidas = movimientoService.mostrarCantidadTotalSalidas();
                        for (Movimiento movimiento : cantidadTotalSalidas.values()) {
                            System.out.println("Código del producto: " + movimiento.getProducto());
                            System.out.println("Id del proveedor: " + movimiento.getProveedor());
                            System.out.println("ID del producto: " + movimiento.getProducto());
                            System.out.println("Cantidad de salida: " + movimiento.getCantidad());
                            System.out.println("Fecha: " + movimiento.getFechaMovimiento());
                            System.out.println("----------------------------------");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("Esta parte no está disponible por el momento :(");
                    break;
                case 4:
                    System.out.println("PRODUCTOS QUE NUNCA HAN RECIBIDO ENTRADAS:");
                    try {
                        ArrayList<Integer> productosSinEntradas = movimientoService.productosSinEntradas();
                        for (Integer id : productosSinEntradas) {
                            System.out.println("ID del producto: " + id);
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                case 5:
                    System.out.println("PRODUCTOS QUE NUNCA HAN RECIBIDO SALIDAS:");
                    try {
                        ArrayList<Integer> productosSinSalidas = movimientoService.productosSinSalidas();
                        for (Integer id : productosSinSalidas) {
                            System.out.println("ID del producto: " + id);
                        }
                    } catch (IllegalArgumentException e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                    break;
                default:
                    continuar = false;
            }
        }
    }
}
