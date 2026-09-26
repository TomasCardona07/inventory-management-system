package service;
import repository.*;


import java.sql.SQLException;
import java.util.HashMap;
import java.util.ArrayList;

import model.Movimiento;
public class MovimientoService {
 
    
    private MovimientoRepository movimientoRepository;
    private ProductoRepository productoRepository;
    private ProveedorRepository proveedorRepository;
    public MovimientoService(MovimientoRepository movimientoRepository,ProductoRepository productoRepository,ProveedorRepository proveedorRepository){
        this.movimientoRepository = movimientoRepository;
        this.productoRepository = productoRepository;
        this.proveedorRepository = proveedorRepository;
    }

    public void registrarMovimiento(Movimiento movimiento) throws SQLException { //No se valida el id de la entrada porque se pondra autoIncrementable en Spring Boot
        Integer idProducto = productoRepository.retornarIdProducto(movimiento.getIdProducto());
        if (idProducto == null) {
            throw new IllegalArgumentException("No hay productos con el id ingresado");
        }
        if (movimiento.getCantidad() <= 0) {
            throw new IllegalArgumentException("La cantidad debe ser positiva");
        }
        if (movimiento.getTipoMovimiento().equals("entrada")) {
            Integer idProveedor = proveedorRepository.retornarIdProveedor(movimiento.getIdProveedor());
            if (idProveedor == null) {
                throw new IllegalArgumentException("No hay proveedores con el id ingresado");
            }
            productoRepository.aumentarStock(idProducto, movimiento.getCantidad());
        }
        else{
            Integer stockActual = productoRepository.stockActualProducto(idProducto);
            if (movimiento.getCantidad() > stockActual) {
                throw new IllegalArgumentException("La cantidad supera el stock actual del producto");
            }
            productoRepository.disminuirStock(idProducto, movimiento.getCantidad());
        }
        movimientoRepository.agregarMovimiento(movimiento);
    }

    public HashMap<Integer,Movimiento> mostrarCantidadTotalEntradas(){
        HashMap<Integer,Movimiento> cantidadTotalEntradas = movimientoRepository.mostrarCantidadTotalEntradas();
        if (cantidadTotalEntradas.isEmpty()) {
            throw new IllegalArgumentException("No hay entradas registradas");
            
        }
        return cantidadTotalEntradas;
    }

    public HashMap<Integer,Movimiento> mostrarCantidadTotalSalidas(){
        HashMap<Integer,Movimiento> cantidadTotalSalidas = movimientoRepository.mostrarCantidadTotalSalidas();
        if (cantidadTotalSalidas.isEmpty()) {
            throw new IllegalArgumentException("No hay salidas registradas");
        }
        return cantidadTotalSalidas;
    }

    public ArrayList<Integer> productosSinEntradas(){
        ArrayList<Integer> productosSinEntradas = movimientoRepository.productosSinEntradas();
        if (productosSinEntradas.isEmpty()) {
            throw new IllegalArgumentException("Todos los productos registrados han tenido entradas.");
        }
        return productosSinEntradas;
    }

    public ArrayList<Integer> productosSinSalidas(){
        ArrayList<Integer> productosSinSalidas = movimientoRepository.productosSinSalidas();
        if (productosSinSalidas.isEmpty()) {
            throw new IllegalArgumentException("Todos los productos registrados han tenido salidas.");
        }
        return productosSinSalidas;
    }

}
