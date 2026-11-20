package com.inventory.management.exception.producto;

public class ProductoDesactivadoException extends RuntimeException {
    
    public ProductoDesactivadoException(String mensaje){
        super(mensaje);
    }
}