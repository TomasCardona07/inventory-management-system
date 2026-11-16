package com.inventory.management.controller;

import org.springframework.web.bind.annotation.RestController;
import com.inventory.management.dto.ProveedorDtoRequest;
import com.inventory.management.dto.ProveedorDtoResponse;
import com.inventory.management.service.ProveedorService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RestController 
@RequestMapping("/api/proveedores")
public class ProveedorController {
    
    private final ProveedorService proveedorService;

    public ProveedorController(ProveedorService proveedorService){
        this.proveedorService = proveedorService;
    }

    //Registrar proveedor
    @PostMapping
    public ResponseEntity<ProveedorDtoResponse> registrarProveedor(@Valid @RequestBody ProveedorDtoRequest request){
        ProveedorDtoResponse response = proveedorService.registrarProveedor(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //buscar proveedor por id
    @GetMapping("/{id}")
    public ResponseEntity<ProveedorDtoResponse> buscarProveedorPorId(@PathVariable Long id) {
        ProveedorDtoResponse response = proveedorService.buscarProveedorPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Actualizar proveedor
    @PutMapping("/{id}")
    public ResponseEntity<ProveedorDtoResponse> actualizarProveedor(@PathVariable Long id, @Valid @RequestBody ProveedorDtoRequest request) {
        ProveedorDtoResponse response = proveedorService.actualizarProveedor(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Eliminar proveedor por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProveedor(@PathVariable Long id){
        proveedorService.eliminarProveedor(id);
        return ResponseEntity.noContent().build();
    }
}
