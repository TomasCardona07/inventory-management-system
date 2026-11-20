package com.inventory.management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.inventory.management.dto.ProductoDtoRequest;
import com.inventory.management.dto.ProductoDtoResponse;
import com.inventory.management.service.ProductoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.PutMapping;



@RestController
@RequestMapping("/api/productos")
@Validated 
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    //registrar producto
    @PostMapping
    public ResponseEntity<ProductoDtoResponse> registrarProducto(@Valid @RequestBody ProductoDtoRequest request) {
        ProductoDtoResponse response = productoService.registrarProducto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //buscar por id
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDtoResponse> buscarProductoPorId(@PathVariable
        @Positive(message = "El id debe de ser positivo") Long id){
        ProductoDtoResponse response = productoService.buscarProductoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Actualizar producto
    @PutMapping("/{id}")
    public ResponseEntity<ProductoDtoResponse> actualizarProducto( @PathVariable
        @Positive(message = "El id debe de ser positivo") Long id, @Valid @RequestBody ProductoDtoRequest request) {
        ProductoDtoResponse response = productoService.actualizarProducto(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //eliminar producto por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable
        @Positive(message = "El id debe de ser positivo") Long id){
        productoService.eliminarProducto(id);
        return ResponseEntity.noContent().build(); //204
    }

    //Desactivar Producto
    @PutMapping("/{id}/desactivar")
    public ResponseEntity<ProductoDtoResponse> desactivarProducto(@PathVariable
        @Positive(message = "El id debe de ser positivo") Long id) {
        ProductoDtoResponse response = productoService.desactivarProducto(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
