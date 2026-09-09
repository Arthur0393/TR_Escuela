package com.carlos.escuela.dto.datos;

import io.swagger.v3.oas.annotations.media.Schema;

import java.math.BigDecimal;

@Schema(description = "Datos de una calificacion")
public record DatosCalificacion(

        @Schema(description = "Nombre del curso", example = "Matematicas I")
        String curso,

        @Schema(description = "Descripcion del curso", example = "Curso de calculo integral")
        String descripcion,

        @Schema(description = "Calificaion del curso", example = "5")
        BigDecimal calificaion
) {
}
