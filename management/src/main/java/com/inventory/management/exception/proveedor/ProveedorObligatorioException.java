package com.inventory.management.exception.proveedor;

public class ProveedorObligatorioException extends IllegalArgumentException {
    
    public ProveedorObligatorioException(String mensaje){
        super(mensaje);
    }
}
