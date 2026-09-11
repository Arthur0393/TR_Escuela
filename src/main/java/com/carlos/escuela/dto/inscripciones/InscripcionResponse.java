package com.carlos.escuela.dto.inscripciones;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Información de una inscripción")
public record InscripcionResponse(

        @Schema(description = "ID de la inscripción", example = "1")
        Long id,

        @Schema(description = "Información del alumno")
        AlumnoInscripcionResponse alumno,

        @Schema(description = "Información del grupo")
        GrupoInscripcionResponse grupo,

        @Schema(description = "Calificación del alumno en el grupo",
                example = "9.5",
                nullable = true)
        BigDecimal calificacion,

        @Schema(description = "Fecha de inscripción",
                example = "11/02/2026")
        String fechaInscripcion

) {

    public record AlumnoInscripcionResponse(

            @Schema(description = "Nombre completo del alumno",
                    example = "Juan Pérez López")
            String nombre,

            @Schema(description = "Matrícula del alumno",
                    example = "A2025001")
            String matricula,

            @Schema(description = "Email del alumno",
                    example = "juan.perez@alumnos.com")
            String email,

            @Schema(description = "Fecha de ingreso del alumno",
                    example = "10/01/2025")
            String fechaIngreso
    ) {}

    public record GrupoInscripcionResponse(

            @Schema(description = "Curso",
                    example = "Matemáticas I")
            String curso,

            @Schema(description = "Maestro",
                    example = "Laura Martínez Martínez")
            String maestro,

            @Schema(description = "Aula",
                    example = "Aula 101")
            String aula,

            @Schema(description = "Periodo",
                    example = "2025-1")
            String periodo
    ) {}
}