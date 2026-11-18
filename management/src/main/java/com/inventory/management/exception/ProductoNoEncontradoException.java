package com.inventory.management.exception;

public class ProductoNoEncontradoException extends RuntimeException {

    public ProductoNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
