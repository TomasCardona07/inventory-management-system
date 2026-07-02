import java.util.Scanner;
import model.*;
import util.InputValidator;
import java.util.ArrayList;
import services.*;
public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        int elegirRegistro = 0;
        System.out.println("BIENVENIDO AL SISTEMA DE INVENTARIO");
        while (elegirRegistro != 6) {
            elegirRegistro = InputValidator.elegirRegistro(scr);
            switch (elegirRegistro) {
                case 1:
                    System.out.println("Ingresa el codigo del producto");
                    String codigo = scr.nextLine();
                    System.out.println("Ingresa el nombre del producto");
                    String nombreProd = scr.nextLine();
                    System.out.println("Ingresa la categoria en la que se encuentra el producto");
                    String categoria = scr.nextLine();
                    int cantidad = InputValidator.validarNegativos(scr, "Ingresa la cantidad");
                    productos.add(new Producto(codigo, nombreProd, categoria, cantidad));
                    System.out.println("¡PRODUCTO REGISTRADO CON EXITO!");
                    break;
                case 2:
                    System.out.println("Ingresa el identificador del proveedor");
                    String identificadorProv = scr.nextLine();
                    System.out.println("Ingresa el nombre del proveedor");
                    String nombreProv = scr.nextLine();
                    System.out.println("Ingresa el numero del Proveedor");
                    String telefono = scr.nextLine();
                    proveedores.add(new Proveedor(identificadorProv, nombreProv, telefono));
                    System.out.println("¡PROVEEDOR REGISTRADO CON EXITO!");
                    break;

                case 3: 
                    InventarioService.case3Entradas(proveedores, productos, scr);
                    break;
                case 4: //PENDIENTE: verificar y modular este bloque de código
                    System.out.println("Ingrese el codigo del producto");
                    codigo = scr.nextLine();
                    boolean productoEncontrado = false;
                    for (Producto producto : productos) {
                        if (producto.getCodigo().equals(codigo)) {
                            do {
                                cantidad = InputValidator.validarNegativos(scr, "Ingresa la cantidad que desea retirar");
                                if (cantidad <= producto.getCantidad()) {
                                    producto.setDeleteCantidad(cantidad);
                                    break;
                                }
                                else{
                                    System.err.println("la cantidad supera el valor del stack disponible");
                                }
                            } while (cantidad > producto.getCantidad());
                            System.out.println(producto.getCantidad());
                            productoEncontrado = true;
                            break;
                        }
                    }
                    if (productoEncontrado == false) {
                        System.err.println("Producto no encontrado");
                    }
                    break;
                default:
                    break;
            }
        }
    }
}