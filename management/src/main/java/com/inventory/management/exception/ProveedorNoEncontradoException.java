package com.inventory.management.exception;

public class ProveedorNoEncontradoException extends RuntimeException {
    
    public ProveedorNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
