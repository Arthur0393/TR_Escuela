package com.carlos.escuela.dto.aulas;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos necesarios para registrar o actualizar un Aula ")
public record AulaRequest(
        @Schema(
                description = "Nombre del aula",
                example = "Aula 101",
                maxLength = 100)
        @NotNull(
                message = "El nombre es requerido")
        @Size(
                max = 100,
                message = "El nombre no puede exceder 100 caracteres ")
        String nombre,

        @Schema(description = "Capacidad máxima de alumnos que puede contener el aula",
                example = "30"
        )
        @NotNull(
                message = "La capacidad del aula es requerida")
        @Positive(
                message = "La capacidad debe ser mayor  a 0")
        Integer capacidad
) {

}


