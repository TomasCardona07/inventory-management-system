package service;
import repository.MovimientoRepository;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.ArrayList;

import model.Movimiento;
public class MovimientoService {
 
    
    private MovimientoRepository movimientoRepository;
    public MovimientoService(MovimientoRepository movimientoRepository){
        this.movimientoRepository = movimientoRepository;
    }

    public void registrarMovimiento(Movimiento movimiento) throws SQLException {
        if(movimiento.getCantidad() < 0){
            throw new IllegalArgumentException("La cantidad no puede ser negativa.");
        }
        
        movimientoRepository.agregarMovimiento(movimiento);
    }

    public HashMap<Integer,Movimiento> mostrarCantidadTotalEntradas(){
        HashMap<Integer,Movimiento> cantidadTotalEntradas = movimientoRepository.mostrarCantidadTotalEntradas();
        if (cantidadTotalEntradas == null) {
            throw new IllegalArgumentException("No hay entradas registradas");
            
        }
        return cantidadTotalEntradas;
    }

    public HashMap<Integer,Movimiento> mostrarCantidadTotalSalidas(){
        HashMap<Integer,Movimiento> cantidadTotalSalidas = movimientoRepository.mostrarCantidadTotalSalidas();
        if (cantidadTotalSalidas == null) {
            throw new IllegalArgumentException("No hay salidas registradas");
        }
        return cantidadTotalSalidas;
    }

    public ArrayList<Integer> productosSinEntradas(){
        ArrayList<Integer> productosSinEntradas = movimientoRepository.productosSinEntradas();
        if (productosSinEntradas == null) {
            throw new IllegalArgumentException("Todos los productos registrados han tenido entradas.");
        }
        return productosSinEntradas;
    }

    public ArrayList<Integer> productosSinSalidas(){
        ArrayList<Integer> productosSinSalidas = movimientoRepository.productosSinSalidas();
        if (productosSinSalidas == null) {
            throw new IllegalArgumentException("Todos los productos registrados han tenido salidas.");
        }
        return productosSinSalidas;
    }

}
