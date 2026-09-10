package com.carlos.escuela.dto.grupos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public record GrupoRequest(

        @Schema(description = "ID del Curso", example = "1")
        @NotNull(message = "El ID del curso es requerido")
        @Positive
        Long idCurso,

        @Schema(description = "ID del Maestro", example = "1")
        @NotNull(message = "El ID del maestro es requerido")
        @Positive
        Long idMaestro,

        @Schema(description = "ID del Aula", example = "1")
        @NotNull(message = "El ID del aula es requerido")
        @Positive
        Long idAula,

        @Schema(description = "Periodo del grupo", example = "2026-01")
        @NotBlank(message = "El ID del maestro es requerido")
        @Size(min = 7,max = 10, message = "El grupo debe tener entre 7 y 10 caracteres")
        String periodo
) {

}
