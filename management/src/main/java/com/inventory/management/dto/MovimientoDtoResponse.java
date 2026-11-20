package com.inventory.management.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.inventory.management.model.TipoMovimiento;

public record MovimientoDtoResponse(

    Long idMovimiento,
    Long idProducto,
    Long idProveedor,
    Integer cantidad,
    TipoMovimiento tipo,
    
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    LocalDateTime fecha

) {}
