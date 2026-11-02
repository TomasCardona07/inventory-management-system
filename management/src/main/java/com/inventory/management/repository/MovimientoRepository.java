package com.inventory.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.management.model.Movimiento;

public interface MovimientoRepository extends JpaRepository<Movimiento,Long> {
    
}
