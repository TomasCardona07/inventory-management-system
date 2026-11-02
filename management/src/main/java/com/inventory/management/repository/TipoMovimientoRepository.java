package com.inventory.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.management.model.TipoMovimiento;

public interface TipoMovimientoRepository extends JpaRepository<TipoMovimiento,Long> {
    
}
