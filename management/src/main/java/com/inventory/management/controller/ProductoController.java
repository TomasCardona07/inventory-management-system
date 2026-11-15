package com.inventory.management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import com.inventory.management.dto.ProductoDtoRequest;
import com.inventory.management.dto.ProductoDtoResponse;
import com.inventory.management.service.ProductoService;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api/productos")
public class ProductoController {

    private final ProductoService productoService;

    public ProductoController(ProductoService productoService){
        this.productoService = productoService;
    }

    @PostMapping //Pendiente: Crear excepciones globales y personalizadas
    public ResponseEntity<ProductoDtoResponse> registrarProducto(@RequestBody @Valid ProductoDtoRequest request) {
        ProductoDtoResponse response = productoService.registrarProducto(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
