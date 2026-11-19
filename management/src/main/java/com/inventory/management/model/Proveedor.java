package com.inventory.management.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Entity
@Table(name = "proveedores")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "proveedor_gen")
    @SequenceGenerator(name = "proveedor_gen", sequenceName = "proveedor_seq", allocationSize = 10) //modificado en base de datos
    @Column(name = "id_proveedor")
    private Long id;

    @Column(name = "nombre_proveedor", nullable = true, length = 100)
    private String nombre;

    @Column(name = "telefono_proveedor", length = 100)
    private String telefono;
}
