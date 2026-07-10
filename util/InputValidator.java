package util;
import repository.*;
import java.util.Scanner;

import model.Producto;
import model.Proveedor;
public class InputValidator {

    // ============ VALIDAR INGRESO DE ELECCIÓN DE REGISTRO ==============
    public static int elegirRegistro(Scanner scr){
        int elegirRegistro = 0;
        do {
            try{
                System.out.println("¿Qué acción deseas hacer?");
                System.out.println("[1] REGISTRAR PRODUCTO");
                System.out.println("[2] REGISTRAR PROVEEDOR");
                System.out.println("[3] REGISTRAR ENTRADA DE INVENTARIO");
                System.out.println("[4] REGISTRAR SALIDA DE INVENTARIO");
                System.out.println("[5] ELIMINAR PRODUCTO");
                System.out.println("[6] ELIMINAR PROVEEDOR");
                System.out.println("[7] VER MENU DE REPORTES");
                System.out.println("[8] SALIR");
                elegirRegistro = Integer.parseInt(scr.nextLine());
                if (elegirRegistro < 1 || elegirRegistro > 8) {
                    System.err.println("Numero incorrecto");
                }
            } 
            catch (NumberFormatException e) {
                System.err.println("¡Ingresa un numero porfavor!");
            }
        } while (elegirRegistro < 1 || elegirRegistro > 8);
        return elegirRegistro;
    }
    
    // ============ VALIDACIÓN ENTRADAS QUE NO PUEDEN SER NEGATIVAS ==============
public static int validarNegativos(Scanner scr, String mensaje, boolean permiteDecimales) {
    int dato = 0;
    boolean entradaValida = false;
    do {
        try {
            System.out.println(mensaje);
            String entrada = scr.nextLine();
            if (permiteDecimales) {
                double lecturaDecimal = Double.parseDouble(entrada);
                dato = (int)lecturaDecimal;
            } else {
                dato = Integer.parseInt(entrada);
            }
            if (dato < 0) {
                System.err.println("dato no valido");
            } else {
                entradaValida = true;
            }
        } catch (NumberFormatException e) {
            System.err.println("¡Ingresa un numero porfavor!");
        }
    } while (!entradaValida);
    return dato;
}


    // =========== IDENTIFICADOR PROVEEDOR REPETIDO ============
    public static String idProveedorRepetido(Scanner scr, ProveedorRepository proveedor){
        String identificador = scr.nextLine();
        Proveedor proveedorExistente = proveedor.retornarProveedor(identificador);
        while (proveedorExistente != null) {
           System.out.println("EL PROVEEDOR YA EXISTE");
           System.out.println("INGRESE UN NUEVO IDENTIFICADOR");
           identificador = scr.nextLine();
           proveedorExistente = proveedor.retornarProveedor(identificador);
        }
        return identificador;
    }

    // =========== IDENTIFICADOR PRODUCTO REPETIDO ============
    public static String idProductoRepetido(Scanner scr, ProductoRepository producto){
        String codigo = scr.nextLine();
        Producto productoExistente = producto.retornarProducto(codigo);
        while (productoExistente != null) {
           System.out.println("EL PRODUCTO YA EXISTE");
           System.out.println("INGRESE UN NUEVO CODIGO");
           codigo = scr.nextLine();
           productoExistente = producto.retornarProducto(codigo);
        }
        return codigo;
    }

    // ============ VALIDAR INGRESO DE ELECCIÓN DE REPORTE ==============
    public static int elegirReporte(Scanner scr){
        int elegirReporte = 0;
        do {
            try{
                System.out.println("¿Qué reporte deseas hacer?");
                System.out.println("[1] TOTAL PROVEEDORES REGISTRADOS");
                System.out.println("[2] TOTAL PRODUCTOS REGISTRADOS");
                System.out.println("[3] PRODUCTO CON MAYOR STOCK");
                System.out.println("[4] PRODUCTO CON MENOR STOCK");
                System.out.println("[5] VALOR TOTAL DEL INVENTARIO");
                System.out.println("[6] PRODUCTOS AGOTADOS");
                System.out.println("[7] PRODUCTOS CON MENOS DE 5 UNIDADES");
                System.out.println("[8] REGRESAR AL MENU DE REGISTROS");
                elegirReporte = Integer.parseInt(scr.nextLine());
                if (elegirReporte < 1 || elegirReporte > 8) {
                    System.err.println("Numero incorrecto");
                }
            } 
            catch (NumberFormatException e) {
                System.err.println("¡Ingresa un numero porfavor!");
            }
        } while (elegirReporte < 1 || elegirReporte > 8);
        return elegirReporte;
    }
}
