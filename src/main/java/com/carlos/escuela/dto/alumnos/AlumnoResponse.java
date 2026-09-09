package com.carlos.escuela.dto.alumnos;

import com.carlos.escuela.dto.datos.DatosCalificacion;
import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;
import java.util.List;

@Schema(description = "Datos de un alumno")
public record AlumnoResponse(
        @Schema(description = "ID del maestro", example = "1")
        Long id,

        @Schema(description = "Nombre completo del maestro", example = "Miguel")
        String nombre,

        @Schema(description = "Email del maestro", example = "test@test.com")
        String email,

        @Schema(description = "Matricula del maestro", example = "ROALCA2601")
        String matricula,

        @Schema(description = "Fecha de ingreso del maestro", example = "12/09/2026")
        String fechaIngreso,

        @Schema(description = "Datos de las calificaciones del alumno")
        List<DatosCalificacion> calificaciones,

        @Schema(description = "Promedio del alumno", example = "9.9")
        BigDecimal promedio

) {}
