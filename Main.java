import java.util.Scanner;
import model.*;
import util.InputValidator;
import java.util.ArrayList;
import services.*;
public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        ArrayList<Producto> productos = new ArrayList<>();
        int elegirRegistro = 0;
        System.out.println("BIENVENIDO AL SISTEMA DE INVENTARIO");
        while (elegirRegistro != 6) {
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
                    // PENDIENTE: optimizar este bloque de código en services
                    InventarioService.case4Entradas(productos, scr);
                    break;
                default:
                    break;
            }
        }
    }
}
