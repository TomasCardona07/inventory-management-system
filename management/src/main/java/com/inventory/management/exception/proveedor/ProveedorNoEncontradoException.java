package com.inventory.management.exception.proveedor;

public class ProveedorNoEncontradoException extends RuntimeException {
    
    public ProveedorNoEncontradoException(String mensaje){
        super(mensaje);
    }
}
