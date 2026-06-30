import java.util.Scanner;
import java.util.ArrayList;
import java.util.InputMismatchException;
import util.InputValidator;
public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Proveedor> proveedores = new ArrayList<>();

        System.out.println("BIENVENIDO AL SISTEMA DE INVENTARIO");
        int elegirRegistro = util.InputValidator.elegirRegistro(scr);
    }
}