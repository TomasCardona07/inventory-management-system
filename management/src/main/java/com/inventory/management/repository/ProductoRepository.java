package com.inventory.management.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventory.management.model.Producto;

public interface ProductoRepository extends JpaRepository<Producto,Long> {
}
