package com.carlos.escuela.controller;

import com.carlos.escuela.dto.alumnos.AlumnoRequest;
import com.carlos.escuela.dto.alumnos.AlumnoResponse;
import com.carlos.escuela.services.alumnos.AlumnoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/alumnos")
@Tag(
        name = "Alumnos",
        description = "Metodos para la gestión de alumnos"
)
public class AlumnoController
        extends CrudController<AlumnoRequest, AlumnoResponse, AlumnoService> {

    public AlumnoController(AlumnoService service) {
        super(service);
    }

    @Override
    @Operation(
            summary = "Listar alumnos",
            description = "Obtiene la lista de todos los alumnos registrados."
    )
    public ResponseEntity<List<AlumnoResponse>> listar() {
        return super.listar();
    }

    @Override
    @Operation(
            summary = "Obtener alumno por ID",
            description = "Obtiene la información de un alumno mediante su identificador."
    )
    public ResponseEntity<AlumnoResponse> obtenerPorId(Long id) {

        return super.obtenerPorId(id);
    }

    @Override
    @Operation(
            summary = "Registrar alumno",
            description = "Registra un nuevo alumno en el sistema."
    )
    public ResponseEntity<AlumnoResponse> registrar(AlumnoRequest request) {

        return super.registrar(request);
    }

    @Override
    @Operation(
            summary = "Actualizar alumno",
            description = "Actualiza la información de un alumno existente."
    )
    public ResponseEntity<AlumnoResponse> actualizar(
            Long id,
            AlumnoRequest request
    ) {
        return super.actualizar(id, request);
    }

    @Override
    @Operation(
            summary = "Eliminar alumno",
            description = "Elimina un alumno utilizando su identificador."
    )
    public ResponseEntity<Void> eliminar(Long id) {
        return super.eliminar(id);
    }
}