
import java.util.Scanner;
import services.*;
import util.InputValidator;
public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        InputValidator inputValidator = new InputValidator();
        InventarioService inventarioService = new InventarioService();
        int elegirRegistro = 0;
        int salidaRegistro = 9;
        System.out.println("BIENVENIDO AL SISTEMA DE INVENTARIO");
        // ======= MENU DE REGISTROS =======
        while (elegirRegistro != salidaRegistro) {
            System.out.println("MENU DE REGISTROS");
            elegirRegistro = inputValidator.elegirRegistro(scr);
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
                    int salidaReporte = 9;
                    // ======= MENU DE REPORTES =======
                    while (elegirReporte != salidaReporte) {
                        System.out.println("MENU DE REPORTES");
                        elegirReporte = inputValidator.elegirReporte(scr);
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
                            case 8:
                                int elegirMovimiento = 0;
                                int salidaMovimientos = 7;
                                // ======= MENU DE MOVIMIENTOS =======
                                while (salidaMovimientos != elegirMovimiento ) {
                                    System.out.println("MENU DE MOVIMIENTOS");
                                    elegirMovimiento = inputValidator.verMovimientos(scr);
                                    switch (elegirMovimiento) {
                                        case 1:
                                            inventarioService.entradasRegistradas();
                                            break;
                                        case 2:
                                            inventarioService.salidasRegistradas();
                                            break;
                                        case 3:
                                            inventarioService.ultimosMovimientos();
                                            break;
                                        case 4:
                                            inventarioService.productosSinEntradas();
                                            break;
                                        case 5:
                                            inventarioService.productosSinSalidas();
                                            break;
                                        case 6:
                                            elegirMovimiento = salidaMovimientos;
                                            break;
                                        default:
                                            salidaReporte = elegirReporte;
                                            break;
                                    }
                                }
                                break;
                            default:
                                break;
                        }
                    }
                    break;
                default:
                    elegirRegistro = salidaRegistro;
                    System.out.println("GRACIAS POR USAR NUESTRO SISTEMA :)");
                    break;
            }
        }
        inventarioService.guardarJson(); //Se guardan los objetos en el Json
        scr.close();
    }
}