package com.inventory.management.dto;

import java.time.LocalDateTime;

import com.inventory.management.model.TipoMovimiento;

public record MovimientoDtoResponse(

    Long idMovimiento,
    Long idProducto,
    Long idProveedor,
    Integer cantidad,
    TipoMovimiento tipo,
    LocalDateTime fecha

) {}
