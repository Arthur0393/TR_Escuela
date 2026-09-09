package com.carlos.escuela.dto.alumnos;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Schema(description = "Datos necesarios para registrar o actualizar un alumno")
public record AlumnoRequest(

    @Schema(description = "Nombre del alumno", example = "Miguel")
    @NotBlank(message = "El nombre es requerido")
    @Size(max = 50, message = "El nombre no puede exceder 50 caracteres")
    String nombre,

    @Schema(description = "Apellido Paterno del alumno", example = "Romero")
    @NotBlank(message = "El apellido paterno es requerido")
    @Size(max = 50, message = "El apellido paterno no puede exceder 50 caracteres")
    String apellidoPaterno,

    @Schema(description = "Apellido Materno del alumno", example = "Alcantara")
    @NotBlank(message = "El apellido materno es requerido")
    @Size(max = 50, message = "El apellido materno no puede exceder 50 caracteres")
    String apellidoMaterno

){}
