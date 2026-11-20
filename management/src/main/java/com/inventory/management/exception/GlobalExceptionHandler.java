package com.inventory.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.inventory.management.exception.categoria.CategoriaDesactivadaException;
import com.inventory.management.exception.categoria.CategoriaNoEncontradaException;
import com.inventory.management.exception.movimiento.MovimientoNoEncontradoException;
import com.inventory.management.exception.movimiento.StockInsuficienteException;
import com.inventory.management.exception.producto.ProductoNoEncontradoException;
import com.inventory.management.exception.producto.ProductoDesactivadoException;
import com.inventory.management.exception.proveedor.ProveedorDesactivadoException;
import com.inventory.management.exception.proveedor.ProveedorNoEncontradoException;
import com.inventory.management.exception.proveedor.ProveedorObligatorioException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice 
public class GlobalExceptionHandler {


    //Manejar Validaciones de DTO
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> manejarErroresValidacion(MethodArgumentNotValidException ex) {
        Map<String, String> errores = new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error ->
            errores.put(error.getField(), error.getDefaultMessage())
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errores);
    }

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

    //Proveedor obligatorio
    @ExceptionHandler(ProveedorObligatorioException.class)
    public ResponseEntity<String> proveedorObligatorio(ProveedorObligatorioException ex){
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    //producto desactivado
    @ExceptionHandler(ProductoDesactivadoException.class)
    public ResponseEntity<String> productoDesactivado(ProductoDesactivadoException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    //proveedor desactivado
    @ExceptionHandler(ProveedorDesactivadoException.class)
    public ResponseEntity<String> proveedorDesactivado(ProveedorDesactivadoException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }

    //Categoria desactivada
    @ExceptionHandler(CategoriaDesactivadaException.class)
    public ResponseEntity<String> categoriaDesactivada(CategoriaDesactivadaException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
    }
}
