package util;
import java.util.Scanner;
import java.math.BigDecimal;

public class InputValidator {

    public Integer solicitarEntero(Scanner entrada, String mensaje) {
        while (true) {
            System.out.println(mensaje);
            String texto = entrada.nextLine();
            if (esTextoValido(texto)) {
                try {
                    return Integer.parseInt(texto.trim());
                } catch (NumberFormatException e) {
                    System.err.println("Error: Debe ser un número entero válido.");
                }
            } else {
                System.err.println("El campo no puede estar vacío.");
            }
        }
    }

    public String solicitarTextoValidado(Scanner entrada, String mensaje) {
        String valor;
        while (true) {
            System.out.println(mensaje);
            valor = entrada.nextLine();
            if (esTextoValido(valor)) {
                break;
            }
            System.err.println("Este campo no puede estar vacío. Inténtalo de nuevo.");
        }
        return valor.trim();
    }
    public BigDecimal solicitarBigDecimalValidado(Scanner entrada, String mensaje) {
        String texto = solicitarTextoValidado(entrada, mensaje);
        BigDecimal numero = convertirABigDecimal(texto);
        return numero;
    }

    public boolean esTextoValido(String texto) {
        return texto != null && !texto.trim().isEmpty();
    }


    //Formatear para precios
    public BigDecimal convertirABigDecimal(String numeroTexto) {
        if (numeroTexto == null || numeroTexto.trim().isEmpty()) {
            return null;
        }
        try {
            String valido = numeroTexto.replace(",", ".");
            return new BigDecimal(valido);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    //se queda por el momento
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



    // ============ VALIDAR INGRESO DE ELECCIÓN DE REPORTE ==============
    //se queda por el momento
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


    // ============= VALIDAR INGRESO DE ELECCIÓN DE MOVIMIENTOS ==============
    // se queda por el momento
    public int elegirMovimiento(Scanner scr){
        int elegirReporte = 0;
        do {
            try{
                System.out.println("¿Qué movimientos deseas consultar?");
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
