package com.inventory.management.dto;

import java.math.BigDecimal;

public record ProductoDtoResponse(
    String nombreProducto,
    Long idCategoria,
    Integer cantidad,
    BigDecimal precio

){}