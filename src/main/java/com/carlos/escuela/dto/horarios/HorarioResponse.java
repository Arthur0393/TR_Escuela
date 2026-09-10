package com.carlos.escuela.dto.horarios;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Informacion del horario")
public record HorarioResponse(

        @Schema(description = "ID del Horario", example = "1")
        long idHorario,

        @Schema(description = "ID del Grupo", example = "1")
        long idGrupo,

        @Schema(description = "Dia del curso", example = "LUNES")
        String dia,

        @Schema(description = "Horario Inicial", example = "7:00")
        String horaInicio,

        @Schema(description = "Horario Final", example = "11:00")
        String horaFin

) {
}
