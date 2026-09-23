package controller;
import util.InputValidator;
import java.util.Scanner;
import service.*;
import java.util.HashMap;
import model.*;

public class MovimientoController {
    InputValidator inputValidator = new InputValidator();

    public void flujoMovimiento(Scanner entrada, MovimientoService movimientoServices, ProductoService productoService, ProveedorService proveedorServices) {

        Boolean continuar = true;
        while (continuar) {
            int elegirMovimiento = inputValidator.elegirMovimiento(entrada);
            switch (elegirMovimiento) {
                case 1:
                    System.out.println("CANTIDAD TOTAL DE ENTRADAS REGISTRADAS");
                    try {
                        HashMap<Integer, Movimiento> cantidadTotalEntradas = movimientoServices.mostrarCantidadTotalEntradas();
                        if (!cantidadTotalEntradas.isEmpty()) {
                            for (Movimiento movimiento : cantidadTotalEntradas.values()) {
                                System.out.println("Código del producto: " + movimiento.getProducto());
                                System.out.println("Id del proveedor: " + movimiento.getProveedor());
                                System.out.println("ID del producto: " + movimiento.getProducto());
                                System.out.println("Cantidad ingresada: " + movimiento.getCantidad());
                                System.out.println("Fecha: " + movimiento.getFechaMovimiento());
                                System.out.println("----------------------------------");
                            }
                        }
                        else {
                            System.out.println("No hay entradas registradas.");
                        }

                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("CANTIDAD TOTAL DE SALIDAS REGISTRADAS");
                    try {
                        HashMap<Integer, Movimiento> cantidadTotalSalidas = movimientoServices.mostrarCantidadTotalSalidas();
                        if (!cantidadTotalSalidas.isEmpty()) {
                            for (Movimiento movimiento : cantidadTotalSalidas.values()) {
                                System.out.println("Código del producto: " + movimiento.getProducto());
                                System.out.println("Id del proveedor: " + movimiento.getProveedor());
                                System.out.println("ID del producto: " + movimiento.getProducto());
                                System.out.println("Cantidad de salida: " + movimiento.getCantidad());
                                System.out.println("Fecha: " + movimiento.getFechaMovimiento());
                                System.out.println("----------------------------------");
                            }
                        }
                        else {
                            System.out.println("No hay salidas registradas.");
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 3:
                    System.out.println("ULTIMOS MOVIMIENTOS REGISTRADOS");
                    try {

                    } catch (IllegalArgumentException e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                default:
                    continuar = false;
            }
        }
    }
}
