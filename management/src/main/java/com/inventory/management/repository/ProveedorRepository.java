package com.inventory.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.inventory.management.model.Proveedor;

public interface ProveedorRepository extends JpaRepository<Proveedor,Long> {
}
