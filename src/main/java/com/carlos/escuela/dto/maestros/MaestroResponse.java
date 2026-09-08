package com.carlos.escuela.dto.maestros;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos de un maestro")
public record MaestroResponse(

        @Schema(description = "Identificador del maestro", example = "1")
        Long idMaestro,

        @Schema(description = "Nombre del maestro", example = "Miguel")
        String nombre,

        @Schema(description = "Apellido Paterno del maestro", example = "Romero")
        String apellidoPaterno,

        @Schema(description = "Apellido Materno del maestro", example = "Gomez")
        String apellidoMaterno,

        @Schema(description = "Email del maestro", example = "test@test.com")
        String email,

        @Schema(description = "Telefono del maestro", example = "1234567890")
       String telefono
)
{}
