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
                    InventarioService.registrarProducto(scr);
                    break;
                case 2:
                    InventarioService.registrarProveedor(scr);
                    break;
                case 3: 
                    InventarioService.registrarEntrada(scr);    
                    break;
                case 4:
                    InventarioService.registrarSalida(scr);
                    break;
                case 5:
                    InventarioService.eliminarProducto(scr);
                    break;
                case 6:
                    InventarioService.eliminarProveedor(scr);
                    break;
                default:
                    break;
            }
        }
    }
}
