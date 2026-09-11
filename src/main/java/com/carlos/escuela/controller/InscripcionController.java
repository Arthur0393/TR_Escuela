package com.carlos.escuela.controller;

import com.carlos.escuela.dto.inscripciones.InscripcionRequest;
import com.carlos.escuela.dto.inscripciones.InscripcionResponse;
import com.carlos.escuela.services.inscripciones.InscripcionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
@Tag(
        name = "Inscripciones",
        description = "Métodos para la gestión de inscripciones"
)
public class InscripcionController
        extends CrudController<
        InscripcionRequest,
        InscripcionResponse,
        InscripcionService> {

    public InscripcionController(InscripcionService service) {
        super(service);
    }

    @Override
    @Operation(
            summary = "Listar inscripciones",
            description = "Obtiene la lista de todas las inscripciones registradas"
    )
    public ResponseEntity<List<InscripcionResponse>> listar() {
        return super.listar();
    }

    @Override
    @Operation(
            summary = "Obtener inscripción por ID",
            description = "Obtiene la información de una inscripción mediante su identificador"
    )
    public ResponseEntity<InscripcionResponse> obtenerPorId(Long id) {
        return super.obtenerPorId(id);
    }

    @Override
    @Operation(
            summary = "Registrar inscripción",
            description = "Registra una nueva inscripción de un alumno en un grupo"
    )
    public ResponseEntity<InscripcionResponse> registrar(
            InscripcionRequest request
    ) {
        return super.registrar(request);
    }

    @Override
    @Operation(
            summary = "Actualizar inscripción",
            description = "Actualiza la información de una inscripción existente"
    )
    public ResponseEntity<InscripcionResponse> actualizar(
            Long id,
            InscripcionRequest request
    ) {
        return super.actualizar(id, request);
    }

    @Override
    @Operation(
            summary = "Eliminar inscripción",
            description = "Elimina una inscripción utilizando su identificador"
    )
    public ResponseEntity<Void> eliminar(Long id) {
        return super.eliminar(id);
    }
}