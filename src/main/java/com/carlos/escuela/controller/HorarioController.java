package com.carlos.escuela.controller;

import com.carlos.escuela.dto.horarios.HorarioRequest;
import com.carlos.escuela.dto.horarios.HorarioResponse;
import com.carlos.escuela.services.horarios.HorarioService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/horarios")
@Schema(description = "Horarios")
@Tag(
        name = "Horarios",
        description = "Métodos para la gestión de horarios"
)
public class HorarioController
        extends CrudController<HorarioRequest, HorarioResponse, HorarioService> {

    public HorarioController(HorarioService service) {
        super(service);
    }

    @Override
    @Operation(
            summary = "Listar horarios",
            description = "Obtener la lista de todos los horarios registrados."
    )
    public ResponseEntity<List<HorarioResponse>> listar() {
        return super.listar();
    }

    @Override
    @Operation(
            summary = "Obtener horario por ID",
            description = "Obtiene la información de un horario mediante su identificador."
    )
    public ResponseEntity<HorarioResponse> obtenerPorId(Long id) {
        return super.obtenerPorId(id);
    }

    @Override
    @Operation(
            summary = "Registrar horario",
            description = "Registrar un nuevo horario en el sistema, validando el formato de las horas y evitando traslapes de grupo y aula."
    )
    public ResponseEntity<HorarioResponse> registrar(HorarioRequest request) {
        return super.registrar(request);
    }

    @Override
    @Operation(
            summary = "Actualizar horario",
            description = "Actualiza la información de un horario mediante su identificador, respetando las validaciones de horario, grupo y aula."
    )
    public ResponseEntity<HorarioResponse> actualizar(
            Long id,
            HorarioRequest request
    ) {
        return super.actualizar(id, request);
    }

    @Override
    @Operation(
            summary = "Eliminar horario",
            description = "Eliminar un horario utilizando su identificador."
    )
    public ResponseEntity<Void> eliminar(Long id) {
        return super.eliminar(id);
    }
}