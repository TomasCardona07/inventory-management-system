import java.util.Scanner;
import util.InputValidator;
import services.*;
public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        int elegirRegistro = 0;
        int elegirReporte = 0;
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
                case 7:
                    elegirReporte:
                    while (elegirReporte != 8) {
                        elegirReporte = InputValidator.elegirReporte(scr);
                        switch (elegirReporte) {
                            case 1:  
                                InventarioService.proveedoresRegistrados();                            
                                break;
                            case 2:
                                InventarioService.productosRegistrados();
                                break;
                            case 3:
                                InventarioService.mayorStock();
                                break;
                            case 4:
                                InventarioService.menorStock();
                                break;
                            case 5:
                                InventarioService.valorInventario();
                                break;
                            case 8:
                                elegirRegistro = 0;
                                break elegirReporte;
                            default:
                                break;
                        }
                    }
                    break;
                default:
                    System.out.println("GRACIAS POR USAR NUESTRO SISTEMA");
                    break;
            }
        }
    }
}