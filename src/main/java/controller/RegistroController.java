package controller;

import java.util.Scanner;
import util.InputValidator;

import java.math.BigDecimal;
import model.*;
import service.*;


public class RegistroController {
    Scanner entrada = new Scanner(System.in);
    InputValidator inputValidator = new InputValidator();


    public void flujoRegistro(ProductoService productoServices,ProveedorService proveedorServices) {

        Boolean continuar = true;
        while (continuar) {

            int elegirRegistro = inputValidator.elegirRegistro(entrada);
            switch (elegirRegistro) {
                case 1:
                    try {
                        System.out.println("REGISTRAR PRODUCTO");
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
                    Integer idProveedor = inputValidator.solicitarEntero(entrada, "Ingresa el identificador del proveedor");
                    String nombreProveedor = inputValidator.solicitarTextoValidado(entrada, "Ingresa el nombre del proveedor");
                    String telefonoProveedor = inputValidator.solicitarTextoValidado(entrada, "Ingresa el teléfono del proveedor");
                    Proveedor proveedor = new Proveedor(idProveedor, nombreProveedor, telefonoProveedor);
                    proveedorServices.registrarProveedor(proveedor);
                    System.out.println("¡Proveedor registrado correctamente!");
                    break;
            }
        }

    }


}