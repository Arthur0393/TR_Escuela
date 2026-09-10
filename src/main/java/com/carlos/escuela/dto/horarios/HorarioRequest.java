package com.carlos.escuela.dto.horarios;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record HorarioRequest(

        @Schema(description = "ID del Grupo", example = "1")
        @NotNull(message = "El ID del grupo es requerido")
        Long idGrupo,

        @Schema(description = "Dia del curso", example = "Lunes")
        @NotBlank(message = "El dia es requerido")
        @Size(min = 1,max = 15, message = "El dia debe tener entre 1 y 15 caracteres")
        String dia,

        @Schema(description = "Horario Inicial", example = "07:00")
        @NotBlank(message = "El Horario inicial es requerido")
        @Size(min = 5,max = 5, message = "El horario debe tener solo 5 caracteres")
        String horaInicio,

        @Schema(description = "Horario Final", example = "11:00")
        @NotBlank(message = "El Horario Final es requerido")
        @Size(min = 5,max = 5, message = "El horario debe tener solo 5 caracteres")
        String horaFin
) {


}
