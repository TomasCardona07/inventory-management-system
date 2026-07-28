package services;

import java.util.ArrayList;
import java.util.Map;
import model.*;
import repository.*;
import util.InputValidator;
public class Movimientos {
    InputValidator inputValidator = new InputValidator();

    
    // ============= CASE 1: MOSTRAR TOTAL DE ENTRADAS REGISTRADAS  ============ 
    public void entradasRegistradas(ProductoRepository productoRepository, MovimientoRepository movimiento){
        Map<String,Producto> productos = productoRepository.getProductos();
        int sumaEntradas = 0;
        if (!movimiento.arrayVacio()) {
            for (Producto producto : productos.values()) {
                sumaEntradas += producto.getContadorEntradas();
            }
            System.out.println("ENTRADAS REGISTRADAS: " + sumaEntradas);
        }
        else{
            System.err.println("NO HAY MOVIMIENTOS REGISTRADOS");
        }
    }

    // ============= CASE 2: MOSTRAR TOTAL DE SALIDAS REGISTRADAS  ============ 
    public void salidasRegistradas(ProductoRepository productoRepository, MovimientoRepository movimiento){
        Map<String,Producto> productos = productoRepository.getProductos();
        int sumaSalidas = 0;
        if (!movimiento.arrayVacio()) {
            for (Producto producto : productos.values()) {
                sumaSalidas += producto.getContadorSalidas();
            }
            System.out.println("SALIDAS REGISTRADAS: " + sumaSalidas);
        }
        else{
            System.err.println("NO HAY MOVIMIENTOS REGISTRADOS");
        }
    }

    // ============= CASE 3: MOSTRAR ULTIMOS MOVIMIENTOS REGISTRADOS ============ 
    public void ultimosMovimientos(MovimientoRepository movimiento){
        ArrayList<Movimiento> movimientos = movimiento.getMovimientos();
        if (!movimiento.arrayVacio()) {
            try {
                for (int i = movimientos.size()-1; i >= movimientos.size()-5; i--){
                    if (!movimientos.get(i).getTipoMovimiento().equalsIgnoreCase("null")) {
                        System.out.println("TIPO DE MOVIMIENTO: " + movimientos.get(i).getTipoMovimiento());
                        System.out.println("CODIGO DEL PRODUCTO: " + movimientos.get(i).getCodigoProducto());
                        if (movimientos.get(i).getTipoMovimiento().equalsIgnoreCase("ENTRADA")) {
                            System.out.println("CANTIDAD INGRESADA: " + movimientos.get(i).getCantidad());
                        }
                        else{
                            System.out.println("CANTIDAD EXPORTADA: " + movimientos.get(i).getCantidad());
                        }
                        System.out.println("========================");
                    }
                }
            }catch (IndexOutOfBoundsException e) {
                System.out.println("ESTOS SON LOS ULTIMOS MOVIMIENTOS REGISTRADOS :)");
            }
        }
        else{
            System.err.println("NO HAY MOVIMIENTOS REGISTRADOS");
        }
    }

    // ============= CASE 4: MOSTRAR PRODUCTOS QUE NUNCA HAN TENIDO ENTRADAS ============ 
    public void productosSinEntradas(ProductoRepository productoRepository){
        Map<String,Producto> productos = productoRepository.getProductos();
        int contadorTrue = 0;
        int contadorFalse = 0;
        if (!productoRepository.mapaVacio()) {
            System.out.println("PRODUCTOS QUE NUNCA HAN TENIDO ENTRADAS:");
            for (Producto producto : productos.values()) {
                if (producto.getContadorEntradas() == 0) {
                    System.out.println("EL PRODUCTO CON CODIGO: " + producto.getCodigo());
                    System.out.println("===========================");
                    contadorTrue++;
                }
                else{
                    contadorFalse++;
                }
            }
            if (contadorFalse > 0 && contadorTrue == 0) {
                System.out.println("NO HAY PRODUCTOS QUE NUNCA HAN TENIDO ENTRADAS");
            }
        }
        else{
            System.err.println("NO HAY PRODUCTOS REGISTRADOS");
        }
    }

    // ============== CASE 5: MOSTRAR PRODUCTOS QUE NUNCA HAN TENIDO SALIDAS ============ 
    public void productosSinSalidas(ProductoRepository productoRepository){
        Map<String,Producto> productos = productoRepository.getProductos();
        int contadorTrue = 0;
        int contadorFalse = 0;
        if (!productoRepository.mapaVacio()) {
            System.out.println("PRODUCTOS QUE NUNCA HAN TENIDO SALIDAS:");
            for (Producto producto : productos.values()) {
                if (producto.getContadorSalidas() == 0) {
                    System.out.println("EL PRODUCTO CON CODIGO: " + producto.getCodigo());
                    System.out.println("===========================");
                    contadorTrue++;
                }
                else{
                    contadorFalse++;
                }
            }
            if (contadorFalse > 0 && contadorTrue == 0) {
                System.out.println("NO HAY PRODUCTOS QUE NUNCA HAN TENIDO SALIDAS");
            }
        }
        else{
            System.err.println("NO HAY PRODUCTOS REGISTRADOS");
        }
    }
}
