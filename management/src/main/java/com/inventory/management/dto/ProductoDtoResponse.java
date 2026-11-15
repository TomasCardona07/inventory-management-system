package com.inventory.management.dto;

import java.math.BigDecimal;

public record ProductoDtoResponse(
    Long id,
    String nombre,
    Long idCategoria,
    Integer cantidad,
    BigDecimal precio

){}