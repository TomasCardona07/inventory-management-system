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
                    int elegirReporte = 0;
                    int salidaReporte = 8;
                    while (elegirReporte != salidaReporte) {
                        System.out.println("MENU DE REPORTES");
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
                            case 6:
                                InventarioService.productosAgotados();
                                break;
                            case 7:
                                InventarioService.productosEscasos();
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