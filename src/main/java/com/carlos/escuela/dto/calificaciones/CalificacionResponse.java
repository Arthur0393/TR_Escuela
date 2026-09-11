package com.carlos.escuela.dto.calificaciones;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Información de una calificación")
public record CalificacionResponse(

        @Schema(
                description = "ID de la calificación",
                example = "2"
        )
        Long id,

        @Schema(description = "Información de la inscripción")
        InscripcionCalificacionResponse inscripcion,

        @Schema(
                description = "Calificación obtenida",
                example = "9.0"
        )
        BigDecimal calificacion,

        @Schema(
                description = "Fecha de registro de la calificación",
                example = "11/02/2026"
        )
        String fechaRegistro

) {

    public record InscripcionCalificacionResponse(

            @Schema(description = "Información del alumno")
            AlumnoCalificacionResponse alumno,

            @Schema(description = "Información del grupo")
            GrupoCalificacionResponse grupo,

            @Schema(
                    description = "Fecha de inscripción",
                    example = "11/02/2026"
            )
            String fechaInscripcion
    ) {
    }

    public record AlumnoCalificacionResponse(

            @Schema(
                    description = "Nombre completo del alumno",
                    example = "María Gómez Ramos"
            )
            String nombre,

            @Schema(
                    description = "Matrícula del alumno",
                    example = "A2025002"
            )
            String matricula,

            @Schema(
                    description = "Email del alumno",
                    example = "maria.gomez@alumnos.com"
            )
            String email,

            @Schema(
                    description = "Fecha de ingreso del alumno",
                    example = "11/01/2025"
            )
            String fechaIngreso
    ) {
    }

    public record GrupoCalificacionResponse(

            @Schema(
                    description = "Curso",
                    example = "Matemáticas I"
            )
            String curso,

            @Schema(
                    description = "Maestro",
                    example = "Laura Martínez Martínez"
            )
            String maestro,

            @Schema(
                    description = "Aula",
                    example = "Aula 101"
            )
            String aula,

            @Schema(
                    description = "Periodo",
                    example = "2025-1"
            )
            String periodo
    ) {
    }
}