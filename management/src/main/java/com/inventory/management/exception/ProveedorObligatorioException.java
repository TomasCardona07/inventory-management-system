package com.inventory.management.exception;

public class ProveedorObligatorioException extends IllegalArgumentException {
    
    public ProveedorObligatorioException(String mensaje){
        super(mensaje);
    }
}
