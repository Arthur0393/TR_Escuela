package com.carlos.escuela.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Table(name = "CALIFICACIONES")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Calificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CALIFICACION")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(
            name = "ID_INSCRIPCION",
            nullable = false
    )
    private Inscripcion inscripcion;

    @Column(
            name = "CALIFICACION",
            nullable = false,
            precision = 3,
            scale = 1
    )
    private BigDecimal calificacion;

    @Builder.Default
    @Column(name = "FECHA_REGISTRO")
    private LocalDate fechaRegistro = LocalDate.now();

    public void actualizar(BigDecimal calificacion) {
        this.calificacion = calificacion;
    }
}