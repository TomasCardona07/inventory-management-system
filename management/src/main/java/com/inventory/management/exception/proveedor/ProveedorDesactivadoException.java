package com.inventory.management.exception.proveedor;

public class ProveedorDesactivadoException extends RuntimeException {
    
    public ProveedorDesactivadoException(String mensaje){
        super(mensaje);
    }
}
