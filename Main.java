import java.util.Scanner;
import java.util.ArrayList;
import util.InputValidator;
public class Main {
    public static void main(String[] args) {
        Scanner scr = new Scanner(System.in);
        ArrayList<Producto> productos = new ArrayList<>();
        ArrayList<Proveedor> proveedores = new ArrayList<>();
        int elegirRegistro = 0;
        System.out.println("BIENVENIDO AL SISTEMA DE INVENTARIO");
        while (elegirRegistro != 6) {
            elegirRegistro = util.InputValidator.elegirRegistro(scr);
            switch (elegirRegistro) {
                case 1:
                    System.out.println("Ingresa el codigo del producto");
                    String codigo = scr.nextLine();
                    System.out.println("Ingresa el nombre del producto");
                    String nombreProd = scr.nextLine();
                    System.out.println("Ingresa la categoria en la que se encuentra el producto");
                    String categoria = scr.nextLine();
                    int cantidad = InputValidator.validarNegativos(scr, "Ingresa la cantidad");
                    productos.add(new Producto(codigo, nombreProd, categoria, cantidad){});
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

                case 3: //PENDIENTE: VALIDAR Y MODULAR ESTE BLOQUE DE CODIGO
                    System.out.println("Ingresa el identificador del proveedor");
                    String identEntradaProv = scr.nextLine();
                    boolean proveedorEncontrado = false;
                    for (Proveedor proveedor : proveedores) {
                        if (identEntradaProv.equals(proveedor.getIdentificador())) {
                            proveedorEncontrado = true;
                            break;
                        }
                    }
                    if (proveedorEncontrado == true) {
                        System.out.println("Ingresa el código del producto");
                        String codEntradaProd = scr.nextLine();
                        boolean productoEncontrado = false;
                        for (Producto producto : productos) {
                            if (codEntradaProd.equals(producto.getCodigo())) {
                                productoEncontrado = true;
                                break;
                            }
                        }
                        if (productoEncontrado == true) {
                            System.out.println("Ingrese la cantidad recibida");
                            int cantRecibida = Integer.parseInt(scr.nextLine());        
                        }
                        else{
                            System.out.println("PRODUCTO NO EXISTENTE");
                        }
                    }
                    else{
                        System.out.println("PROVEEDOR NO ENCONTRADO");
                    }
                    System.out.println("ENTRADA REGISTRADA CON EXITO");
                    break;

                default:
                    break;
            }
        }
    }
}