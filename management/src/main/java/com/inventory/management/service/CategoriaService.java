package com.inventory.management.service;

import com.inventory.management.model.Categoria;
import org.springframework.stereotype.Service;
import com.inventory.management.dto.CategoriaDtoRequest;
import com.inventory.management.dto.CategoriaDtoResponse;
import com.inventory.management.repository.CategoriaRepository;

@Service 
public class CategoriaService {

    private final CategoriaRepository categoriaRepository;

    public CategoriaService(CategoriaRepository categoriaRepository){
        this.categoriaRepository = categoriaRepository;
    }

    //Registrar Categoria
    public CategoriaDtoResponse registrarCategoria(CategoriaDtoRequest categoriaDtoRequest){
        Categoria categoria = new Categoria();
        categoria.setNombre(categoriaDtoRequest.nombre());
        Categoria categoriaRegistrada = categoriaRepository.save(categoria);
        
        return new CategoriaDtoResponse(categoriaRegistrada.getId(), categoriaRegistrada.getNombre());
    }

    //consultar todas las categorias
    //cambiar nombre a categoria
    //eliminar categoria por id

    //buscar categoria por id
    public Categoria buscarCategoriaPorId(Long id){
        return categoriaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Categoria no encontrada"));
    }
}
