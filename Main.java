import java.util.Scanner;
import util.InputValidator;
import services.*;
public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        InventarioService inventarioService = new InventarioService();
        int elegirRegistro = 0;
        int salidaRegistro = 8;
        System.out.println("BIENVENIDO AL SISTEMA DE INVENTARIO");
        while (elegirRegistro != salidaRegistro) {
            System.out.println("MENU DE REGISTROS");
            elegirRegistro = InputValidator.elegirRegistro(scr);
            switch (elegirRegistro) {
                case 1:
                    inventarioService.registrarProducto(scr);
                    break;
                case 2:
                    inventarioService.registrarProveedor(scr);
                    break;
                case 3: 
                    inventarioService.registrarEntrada(scr);    
                    break;
                case 4:
                    inventarioService.registrarSalida(scr);
                    break;
                case 5:
                    inventarioService.eliminarProducto(scr);
                    break;
                case 6:
                    inventarioService.eliminarProveedor(scr);
                    break;
                case 7:
                    int elegirReporte = 0;
                    int salidaReporte = 8;
                    while (elegirReporte != salidaReporte) {
                        System.out.println("MENU DE REPORTES");
                        elegirReporte = InputValidator.elegirReporte(scr);
                        switch (elegirReporte) {
                            case 1:  
                                inventarioService.proveedoresRegistrados();                            
                                break;
                            case 2:
                                inventarioService.productosRegistrados();
                                break;
                            case 3:
                                inventarioService.mayorStock();
                                break;
                            case 4:
                                inventarioService.menorStock();
                                break;
                            case 5:
                                inventarioService.valorInventario();
                                break;
                            case 6:
                                inventarioService.productosAgotados();
                                break;
                            case 7:
                                inventarioService.productosEscasos();
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                default:
                    System.out.println("GRACIAS POR USAR NUESTRO SISTEMA :)");
                    break;
            }
        }
        scr.close();
    }
}