package com.inventory.management.exception.movimiento;

public class StockInsuficienteException extends RuntimeException {
    
    public StockInsuficienteException(String mensaje){
        super(mensaje);
    }
}
