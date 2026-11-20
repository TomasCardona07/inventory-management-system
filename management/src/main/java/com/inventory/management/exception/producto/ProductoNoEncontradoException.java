package com.inventory.management.exception.producto;

public class ProductoNoEncontradoException extends RuntimeException {

    public ProductoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
