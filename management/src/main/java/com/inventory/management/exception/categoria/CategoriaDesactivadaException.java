package com.inventory.management.exception.categoria;

public class CategoriaDesactivadaException extends RuntimeException {
    
    public CategoriaDesactivadaException(String mensaje){
        super(mensaje);
    }
}
