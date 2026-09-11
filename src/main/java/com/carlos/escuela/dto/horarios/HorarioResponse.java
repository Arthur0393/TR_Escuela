package com.carlos.escuela.dto.horarios;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Información del horario")
public record HorarioResponse(

        @Schema(description = "ID del horario", example = "1")
        Long id,

        @Schema(description = "Información del grupo")
        GrupoHorarioResponse grupo,

        @Schema(
                description = "Día y horario",
                example = "Lunes 08:00 10:00"
        )
        String horario
) {

        public record GrupoHorarioResponse(

                @Schema(description = "Curso", example = "Matemáticas I")
                String curso,

                @Schema(
                        description = "Maestro",
                        example = "Laura Martínez Martínez"
                )
                String maestro,

                @Schema(description = "Aula", example = "Aula 101")
                String aula,

                @Schema(description = "Periodo", example = "2025-1")
                String periodo
        ) {}
}