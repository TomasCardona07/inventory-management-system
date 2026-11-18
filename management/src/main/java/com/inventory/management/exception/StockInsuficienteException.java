package com.inventory.management.exception;

public class StockInsuficienteException extends RuntimeException {
    
    public StockInsuficienteException(String mensaje){
        super(mensaje);
    }
}
