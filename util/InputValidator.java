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
                elegirRegistro = Integer.parseInt(scr.nextLine());
                if (elegirRegistro < 1 || elegirRegistro > 3) {
                    System.out.println("Numero incorrecto");
                }
            } 
            catch (NumberFormatException e) {
                System.err.println("¡Ingresa un numero porfavor!");
            }
        } while (elegirRegistro < 1 || elegirRegistro > 3);
        return elegirRegistro;
    }
}
