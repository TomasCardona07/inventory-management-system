package com.inventory.management.exception;

public class MovimientoNoEncontradoException extends RuntimeException {

    public MovimientoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
