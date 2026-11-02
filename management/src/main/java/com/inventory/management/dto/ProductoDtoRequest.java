package com.inventory.management.dto;
import com.inventory.management.model.Categoria;
import java.math.BigDecimal;

public record ProductoDtoRequest(Long id, String nombre,Categoria categoria, Integer cantidad, BigDecimal precio){
    
}
