package com.inventory.management.controller;

import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import com.inventory.management.dto.CategoriaDtoRequest;
import com.inventory.management.dto.CategoriaDtoResponse;
import com.inventory.management.service.CategoriaService;

@RestController 
@RequestMapping("/api/categorias")
@Validated 
public class CategoriaController {

    private final CategoriaService categoriaService;

    public CategoriaController(CategoriaService categoriaService){
        this.categoriaService = categoriaService;
    }

    //registrar categoria
    @PostMapping
    public ResponseEntity<CategoriaDtoResponse> registrarCategoria(@Valid @RequestBody CategoriaDtoRequest request){
        CategoriaDtoResponse response = categoriaService.registrarCategoria(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    //MostrarCategorias
    @GetMapping
    public ResponseEntity<List<CategoriaDtoResponse>> mostrarCategorias(){
        List<CategoriaDtoResponse> categorias = categoriaService.consultarCategorias();
        return ResponseEntity.status(HttpStatus.OK).body(categorias);
    }

    //buscar categoria por id
    @GetMapping("/{id}")
    public ResponseEntity<CategoriaDtoResponse> consultarCategoriaPorId(@PathVariable
        @Positive(message = "El id debe de ser positivo") Long id){
        CategoriaDtoResponse response = categoriaService.buscarCategoriaPorId(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Actualizar categoria
    @PutMapping("/{id}")
    public ResponseEntity<CategoriaDtoResponse> actualizarCategoria(@PathVariable
        @Positive(message = "El id debe de ser positivo") Long id, @Valid @RequestBody CategoriaDtoRequest request){
        CategoriaDtoResponse response = categoriaService.actualizarCategoria(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    //Eliminar categoria por id
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarCategoria(@PathVariable
        @Positive(message = "El id debe de ser positivo")  Long id){
        categoriaService.eliminarCategoriaPorId(id);
        return ResponseEntity.noContent().build();
    }

    //Desactivar categoria
    @PutMapping("/{id}/desactivar")
    public ResponseEntity<CategoriaDtoResponse> desactivarCategoria(@PathVariable
        @Positive(message = "El id debe de ser positivo") Long id) {
        CategoriaDtoResponse response = categoriaService.desactivarCategoria(id);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
