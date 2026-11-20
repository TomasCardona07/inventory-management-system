package com.inventory.management.exception.categoria;

public class CategoriaNoEncontradaException extends RuntimeException {
    
    public CategoriaNoEncontradaException(String mensaje){
        super(mensaje);
    }
}
