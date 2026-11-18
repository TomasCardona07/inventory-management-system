package com.inventory.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice 
public class GlobalExceptionHandler {

    //Producto no encontrado
    @ExceptionHandler(ProductoNoEncontradoException.class)
    public ResponseEntity<String> productoNoEncontrado(ProductoNoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //Proveedor no encontrado
    @ExceptionHandler(ProveedorNoEncontradoException.class)
    public ResponseEntity<String> proveedorNoEncontrado(ProveedorNoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //Movimiento no encontrado
    @ExceptionHandler(MovimientoNoEncontradoException.class)
    public ResponseEntity<String> movimientoNoEncontrado(MovimientoNoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //Categoria no encontrada
    @ExceptionHandler(CategoriaNoEncontradaException.class)
    public ResponseEntity<String> categoriaNoEncontrada(CategoriaNoEncontradaException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }

    //Stock insuficiente en salidas
    @ExceptionHandler(StockInsuficienteException.class)
    public ResponseEntity<String> stockInsuficiente(StockInsuficienteException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
