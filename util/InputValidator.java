package util;
import repository.*;
import java.util.Scanner;

import model.Producto;
import model.Proveedor;
public class InputValidator {

    // ============ VALIDAR INGRESO DE ELECCIÓN DE REGISTRO ==============
    public int elegirRegistro(Scanner scr){
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
public int validarNegativos(Scanner scr, String mensaje, boolean permiteDecimales) {
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
    public String idProveedorRepetido(Scanner scr, ProveedorRepository proveedor){
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
    public String idProductoRepetido(Scanner scr, ProductoRepository producto){
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
    public int elegirReporte(Scanner scr){
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
                System.out.println("[8] VER MENU DE HISTORIAL DE MOVIMIENTOS");
                System.out.println("[9] REGRESAR AL MENU DE REGISTROS");
                elegirReporte = Integer.parseInt(scr.nextLine());
                if (elegirReporte < 1 || elegirReporte > 9) {
                    System.err.println("Numero incorrecto");
                }
            } 
            catch (NumberFormatException e) {
                System.err.println("¡Ingresa un numero porfavor!");
            }
        } while (elegirReporte < 1 || elegirReporte > 9);
        return elegirReporte;
    }


    // ============ VALIDAR INGRESO DE ELECCIÓN DE MOVIMIENTOS ==============
    public int verMovimientos(Scanner scr){
        int elegirReporte = 0;
        do {
            try{
                System.out.println("¿Qué movimientos deseas ver?");
                System.out.println("[1] CANTIDAD TOTAL DE ENTRADAS REGISTRADAS");
                System.out.println("[2] CANTIDAD TOTAL DE SALIDAS REGISTRADAS");
                System.out.println("[3] ULTIMOS MOVIMIENTOS REGISTRADOS");
                System.out.println("[4] PRODUCTOS QUE NO HAN RECIBIDO ENTRADAS");
                System.out.println("[5] PRODUCTOS QUE NO HAN TENIDO SALIDA");
                System.out.println("[6] REGRESAR AL MENU DE REPORTES");
                System.out.println("[7] REGRESAR AL MENU DE REGISTROS");
                elegirReporte = Integer.parseInt(scr.nextLine());
                if (elegirReporte < 1 || elegirReporte > 7) {
                    System.err.println("Numero incorrecto");
                }
            } 
            catch (NumberFormatException e) {
                System.err.println("¡Ingresa un numero porfavor!");
            }
        } while (elegirReporte < 1 || elegirReporte >7);
        return elegirReporte;
    }
}
