package com.carlos.escuela.dto.grupos;

import com.carlos.escuela.dto.datos.DatosAula;
import com.carlos.escuela.dto.datos.DatosCurso;
import com.carlos.escuela.dto.datos.DatosMaestro;
import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

public record GrupoResponse(

        @Schema(description = "ID del grupo",
                example = "1")
        Long id,

        @Schema(description = "Datos del curso")
        DatosCurso curso,

        @Schema(description = "Datos del maestro")
        DatosMaestro maestro,

        @Schema(description = "Datos del aula")
        DatosAula aula,

        @Schema(description = "Horarios asignados al grupo",
                example = "[\"Lunes 08:00 - 10:00\", \"Miércoles 08:00 - 10:00\"]")
        List<String> horarios,

        @Schema(description = "Periodo del grupo",
                example = "2026-09")
        String periodo

) {
}
