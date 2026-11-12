package com.inventory.management.dto;
import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

public record ProductoDtoRequest(
    @NotBlank(message = "El nombre es obligatorio")
    String nombre,

    @NotNull(message = "El id de la categoria es obligatorio")
    Long idCategoria,
    
    @PositiveOrZero (message = "La cantidad no debe ser negativa")
    @NotNull (message = "La cantidad es obligatoria")
    Integer cantidad, 

    @NotNull (message = "El precio es obligatorio")
    @PositiveOrZero (message = "El precio no puede ser negativo")
    BigDecimal precio
){}
