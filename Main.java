import java.util.Scanner;
import util.InputValidator;
import services.*;
public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        int elegirRegistro = 0;
        System.out.println("BIENVENIDO AL SISTEMA DE INVENTARIO");
        while (elegirRegistro != 8) {
            elegirRegistro = InputValidator.elegirRegistro(scr);
            switch (elegirRegistro) {
                case 1:
                    InventarioService.case1Entradas(scr);
                    break;
                case 2:
                    InventarioService.case2Entradas(scr);
                    break;
                case 3: 
                    InventarioService.case3Entradas(scr);
                    break;
                case 4:
                    InventarioService.case4Entradas(scr);
                    break;
                case 5:
                    InventarioService.case5Entradas(scr);
                    break;
                case 6:
                    InventarioService.case6Entradas(scr);
                default:
                    break;
            }
        }
    }
}
