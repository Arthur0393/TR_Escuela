package com.carlos.escuela.dto.datos;

import io.swagger.v3.oas.annotations.Hidden;
import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "Datos de un maestro")
public record DatosMaestro(

        @Schema(description = "Nombre del maestro",
                example = "Ana")
        String nombre,
        /*
        @Schema(description = "Apellido paterno del maestro",
                example = "López")
        String apellidoPaterno,

        @Schema(description = "Apellido materno del maestro",
                example = "López")
        String apellidoMaterno,
*/
        @Schema(description = "Email del maestro",
                example = "ana.lopez@escuela.com")
        String email,

        @Schema(description = "Telefono del maestro",
                example = "5553030147")
        String telefono
) {
}
