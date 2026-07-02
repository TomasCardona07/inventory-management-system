package util;


import java.util.Scanner;

public class InputValidator {

    // ============ VALIDAR INGRESO DE ELECCIÓN DE REGISTRO ==============
    public static int elegirRegistro(Scanner scr){
        int elegirRegistro = 0;
        do {
            try{
                System.out.println("Ingresa que deseas hacer");
                System.out.println("[1] REGISTRAR PRODUCTO");
                System.out.println("[2] REGISTRAR PROVEEDOR");
                System.out.println("[3] REGISTRAR ENTRADA DE INVENTARIO");
                System.out.println("[4] REGISTRAR SALIDA DE INVENTARIO");
                System.out.println("[5] VER MENU INTERACTIVO");
                System.out.println("[6] SALIR");
                elegirRegistro = Integer.parseInt(scr.nextLine());
                if (elegirRegistro < 1 || elegirRegistro > 6) {
                    System.err.println("Numero incorrecto");
                }
            } 
            catch (NumberFormatException e) {
                System.err.println("¡Ingresa un numero porfavor!");
            }
        } while (elegirRegistro < 1 || elegirRegistro > 6);
        return elegirRegistro;
    }
    
    // ============ VALIDACIÓN ENTRADAS QUE NO PUEDEN SER NEGATIVAS ==============
    public static int validarNegativos(Scanner scr, String mensaje){
        int dato = 0;
        boolean entradaValida = false;
        do {
            try {
                System.out.println(mensaje);
                dato = Integer.parseInt(scr.nextLine());
                if (dato < 0) {
                    System.err.println("dato no valido");
                }
                else{
                    entradaValida = true;
                }
            } 
            catch (NumberFormatException e) {
                System.err.println("¡Ingresa un numero porfavor!");
            }
        } while (entradaValida == false );
        return dato;
    }
}
