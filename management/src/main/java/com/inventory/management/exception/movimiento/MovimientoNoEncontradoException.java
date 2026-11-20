package com.inventory.management.exception.movimiento;

public class MovimientoNoEncontradoException extends RuntimeException {

    public MovimientoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
