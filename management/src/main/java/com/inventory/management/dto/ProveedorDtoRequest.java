package com.inventory.management.dto;

import jakarta.validation.constraints.NotBlank;

public record ProveedorDtoRequest(
    @NotBlank (message = "El nombre del proveedor es obligatorio")
    String nombre,

    @NotBlank (message = "El telefono del proveedor es obligatorio")
    String telefono
){}
