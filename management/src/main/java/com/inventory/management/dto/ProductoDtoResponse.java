package com.inventory.management.dto;

import java.math.BigDecimal;

public record ProductoDtoResponse(
    Long id,
    String nombreProducto,
    Long idCategoria,
    Integer cantidad,
    BigDecimal precio

){}