package com.inventory.management.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "categorias")
@Getter @Setter
@NoArgsConstructor @AllArgsConstructor
public class Categoria {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "categoria_gen")
    @SequenceGenerator(name = "categoria_gen", sequenceName = "categoria_seq", allocationSize = 10) //modificado en base de datos
    @Column(name = "id_categoria")
    private Long id;

    @Column(name = "nombre_categoria", nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(name = "estado_categoria", nullable = false)
    private Boolean estado;
}
