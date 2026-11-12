package com.inventory.management.dto;

import jakarta.validation.constraints.NotBlank;

public record CategoriaDtoRequest(
    @NotBlank (message = "El nombre de la categoria es obligatorio")
    String nombre
) {}
