package com.inventory.management.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

public record MovimientoDtoRequest(
    
    @NotNull(message = "El id del producto es obligatorio")
    Long idProducto,

    Long idProveedor,

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe de ser positiva")
    Integer cantidad

) {}
