package com.inventory.management.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.inventory.management.dto.MovimientoDtoRequest;
import com.inventory.management.dto.MovimientoDtoResponse;
import com.inventory.management.service.MovimientoService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;


@RestController 
@RequestMapping("/api/movimientos")
@Validated 
public class MovimientoController {
    
    private final MovimientoService movimientoService;
    
    public MovimientoController(MovimientoService movimientoService){
        this.movimientoService = movimientoService;
    }
    
    //Registrar entrada
    @PostMapping("/entrada")
    public ResponseEntity<MovimientoDtoResponse> registrarEntrada(@Valid @RequestBody MovimientoDtoRequest request) {
        MovimientoDtoResponse response = movimientoService.registrarEntrada(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    //Registrar salida
    @PostMapping("/salida")
    public ResponseEntity<MovimientoDtoResponse> registrarSalida(@Valid @RequestBody MovimientoDtoRequest request) {
        MovimientoDtoResponse response = movimientoService.registrarSalida(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
    
    //Consultar historial
    @GetMapping
    public ResponseEntity<List<MovimientoDtoResponse>> mostrarHistorial() {
        List<MovimientoDtoResponse> historial = movimientoService.mostrarHistorial();
        return ResponseEntity.status(HttpStatus.OK).body(historial);
    }
    
    //Buscar Movimiento por id
    @GetMapping("/{id}")
    public ResponseEntity<MovimientoDtoResponse> buscarMovimiento(@PathVariable
        @Positive(message = "El id debe de ser positivo") Long id){
        MovimientoDtoResponse response = movimientoService.buscarMovimientoPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
    
    //Eliminar movimiento por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarMovimiento(@PathVariable
        @Positive(message = "El id debe de ser positivo") Long id){
        movimientoService.eliminarMovimiento(id);
        return ResponseEntity.noContent().build();
    }
}
