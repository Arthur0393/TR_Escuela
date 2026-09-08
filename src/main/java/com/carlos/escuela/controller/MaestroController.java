package com.carlos.escuela.controllers;

import com.carlos.escuela.dto.maestros.MaestroRequest;
import com.carlos.escuela.dto.maestros.MaestroResponse;
import com.carlos.escuela.services.maestro.MaestroServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/maestros")
@RequiredArgsConstructor
@Validated
@Tag(name = "Maestros", description = "Endpoints para la gestión de maestros")
public class MaestroController {

    private final MaestroServices maestroServices;

    @PostMapping
    @Operation(summary = "Crear un nuevo maestro", description = "Registra un maestro en el sistema validando unicidad de correo y teléfono.")
    @ApiResponses({
            @ApiResponse(responseCode = "201", description = "Maestro creado exitosamente"),
            @ApiResponse(responseCode = "400", description = "Datos de entrada inválidos o duplicados")
    })
    public ResponseEntity<MaestroResponse> crear(@Valid @RequestBody MaestroRequest request) {
        MaestroResponse response = maestroServices.crear(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener maestro por ID", description = "Retorna los datos de un maestro según su identificador único.")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Maestro encontrado"),
            @ApiResponse(responseCode = "404", description = "Maestro no encontrado")
    })
    public ResponseEntity<MaestroResponse> buscarPorId(
            @Parameter(description = "ID del maestro a buscar", example = "1")
            @PathVariable @Positive(message = "El id debe ser un número positivo") Long id) {
        return ResponseEntity.ok(maestroServices.buscarPorId(id));
    }

    @GetMapping
    @Operation(summary = "Listar todos los maestros", description = "Obtiene la lista completa de maestros registrados.")
    @ApiResponse(responseCode = "200", description = "Lista recuperada exitosamente")
    public ResponseEntity<List<MaestroResponse>> listarTodos() {
        return ResponseEntity.ok(maestroServices.listarTodos());
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un maestro", description = "Elimina un maestro mediante su identificador único.")
    @ApiResponses({
            @ApiResponse(responseCode = "204", description = "Maestro eliminado exitosamente"),
            @ApiResponse(responseCode = "404", description = "Maestro no encontrado")
    })
    public ResponseEntity<Void> eliminar(
            @Parameter(description = "ID del maestro a eliminar", example = "1")
            @PathVariable @Positive(message = "El id debe ser un número positivo") Long id) {
        maestroServices.eliminar(id);
        return ResponseEntity.noContent().build();
    }
}