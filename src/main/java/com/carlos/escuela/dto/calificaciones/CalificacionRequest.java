package com.carlos.escuela.dto.calificaciones;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.math.BigDecimal;

@Schema(description = "Datos necesarios para registrar o actualizar una calificación")
public record CalificacionRequest(

        @Schema(
                description = "ID de la inscripción",
                example = "15"
        )
        @NotNull(message = "El ID de la inscripción es requerido")
        @Positive(message = "El ID de la inscripción debe ser positivo")
        Long idInscripcion,

        @Schema(
                description = "Calificación obtenida por el alumno",
                example = "8.5"
        )
        @NotNull(message = "La calificación es requerida")
        @DecimalMin(
                value = "0.0",
                message = "La calificación no puede ser menor a 0"
        )
        @DecimalMax(
                value = "10.0",
                message = "La calificación no puede ser mayor a 10"
        )
        BigDecimal calificacion

) {
}