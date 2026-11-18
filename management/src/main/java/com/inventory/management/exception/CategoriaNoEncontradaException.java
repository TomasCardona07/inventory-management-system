package com.inventory.management.exception;

public class CategoriaNoEncontradaException extends RuntimeException {
    
    public CategoriaNoEncontradaException(String mensaje){
        super(mensaje);
    }
}
