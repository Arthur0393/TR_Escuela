package com.carlos.escuela.controller;

import com.carlos.escuela.dto.calificaciones.CalificacionRequest;
import com.carlos.escuela.dto.calificaciones.CalificacionResponse;
import com.carlos.escuela.services.calificaciones.CalificacionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/calificaciones")
@Schema(description = "Calificaciones")
@Tag(
        name = "Calificaciones",
        description = "Métodos para la gestión de calificaciones"
)
public class CalificacionController
        extends CrudController<
        CalificacionRequest,
        CalificacionResponse,
        CalificacionService> {

    public CalificacionController(CalificacionService service) {
        super(service);
    }

    @Override
    @Operation(
            summary = "Listar calificaciones",
            description = "Obtener la lista de todas las calificaciones registradas."
    )
    public ResponseEntity<List<CalificacionResponse>> listar() {
        return super.listar();
    }

    @Override
    @Operation(
            summary = "Obtener calificación por ID",
            description = "Obtiene la información de una calificación mediante su identificador."
    )
    public ResponseEntity<CalificacionResponse> obtenerPorId(Long id) {
        return super.obtenerPorId(id);
    }

    @Override
    @Operation(
            summary = "Registrar calificación",
            description = "Registra una nueva calificación asociada a una inscripción."
    )
    public ResponseEntity<CalificacionResponse> registrar(
            CalificacionRequest request
    ) {
        return super.registrar(request);
    }

    @Override
    @Operation(
            summary = "Actualizar calificación",
            description = "Actualiza la calificación mediante su identificador."
    )
    public ResponseEntity<CalificacionResponse> actualizar(
            Long id,
            CalificacionRequest request
    ) {
        return super.actualizar(id, request);
    }

    @Override
    @Operation(
            summary = "Eliminar calificación",
            description = "Elimina una calificación utilizando su identificador."
    )
    public ResponseEntity<Void> eliminar(Long id) {
        return super.eliminar(id);
    }
}