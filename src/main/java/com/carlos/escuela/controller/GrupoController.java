package com.carlos.escuela.controller;

import com.carlos.escuela.dto.grupos.GrupoRequest;
import com.carlos.escuela.dto.grupos.GrupoResponse;
import com.carlos.escuela.services.grupos.GrupoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/grupos")
@Tag(
        name = "Grupos",
        description = "Métodos para la gestión de grupos"
)
public class GrupoController
        extends CrudController<GrupoRequest, GrupoResponse, GrupoService> {

    public GrupoController(GrupoService service) {
        super(service);
    }

    @Override
    @Operation(
            summary = "Listar grupos",
            description = "Obtiene la lista de todos los grupos registrados."
    )
    public ResponseEntity<List<GrupoResponse>> listar() {
        return super.listar();
    }

    @Override
    @Operation(
            summary = "Obtener grupo por ID",
            description = "Obtiene la información de un grupo mediante su identificador."
    )
    public ResponseEntity<GrupoResponse> obtenerPorId(Long id) {
        return super.obtenerPorId(id);
    }

    @Override
    @Operation(
            summary = "Registrar grupo",
            description = "Registra un nuevo grupo asociando un curso, maestro y aula."
    )
    public ResponseEntity<GrupoResponse> registrar(GrupoRequest request) {
        return super.registrar(request);
    }

    @Override
    @Operation(
            summary = "Actualizar grupo",
            description = "Actualiza la información de un grupo existente."
    )
    public ResponseEntity<GrupoResponse> actualizar(
            Long id,
            GrupoRequest request
    ) {
        return super.actualizar(id, request);
    }

    @Override
    @Operation(
            summary = "Eliminar grupo",
            description = "Elimina un grupo utilizando su identificador."
    )
    public ResponseEntity<Void> eliminar(Long id) {
        return super.eliminar(id);
    }
}

