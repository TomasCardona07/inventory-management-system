package com.inventory.management.dto;

public record ProveedorDtoResponse(
    Long id,
    String nombre,
    String telefono,
    Boolean estado
){}
