package service;
import repository.MovimientoRepository;

import java.sql.SQLException;

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

}
