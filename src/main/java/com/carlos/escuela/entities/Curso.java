package com.carlos.escuela.entities;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "CURSOS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CURSO")
    private Integer id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "DESCRIPCION", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "CREDITOS", nullable = false)
    private Integer creditos;
}
