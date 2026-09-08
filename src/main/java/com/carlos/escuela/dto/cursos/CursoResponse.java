package com.carlos.escuela.dto.cursos;


import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Informacion de un curso")
public record CursoResponse(

        @Schema(description = "ID del curso", example = "1")
        Long id,

        @Schema(description = "Nombre del curso", example = "Matematicas I")
        String nombre,

        @Schema(description = "Descripcion del curso", example = "Curso de calculo integral")
        String descripcion,

        @Schema(description = "Creditos del curso", example = "5")
        Integer creditos
) {}
