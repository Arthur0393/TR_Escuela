package com.carlos.escuela.controller;

import com.carlos.escuela.dto.maestros.MaestroRequest;
import com.carlos.escuela.dto.maestros.MaestroResponse;
import com.carlos.escuela.services.maestros.MaestroService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/maestros")
@Tag(
        name = "Maestros",
        description = "Metodos para gestion de maestros")
public class MaestroController
        extends CrudController<MaestroRequest, MaestroResponse, MaestroService> {

    public MaestroController(MaestroService service) {
        super(service);
    }

    @Override
    @Operation(
            summary ="Listar maestros",
            description ="Obtener la lista de todos los maestros registrado"
    )
    public ResponseEntity<List<MaestroResponse>> listar() {
        return super.listar();
    }

    @Override
    @Operation(
            summary = "Obtener maestro por ID",
            description = "Obtiene la informacion de un maestro mediante su identificador"
    )

    public ResponseEntity<MaestroResponse>obtenerPorId (Long id){
        return super.obtenerPorId(id);
    }

    @Override
    @Operation(
            summary = "Registrar maestro",
            description = "Registra un nuevo maestro en sistema"
    )

    public ResponseEntity<MaestroResponse> registrar (MaestroRequest request){
        return super.registrar(request);
    }

    @Override
    @Operation(
            summary = "Actualizar maestro",
            description= "Actualizar la informacion de un maestro existente"
    )
    public ResponseEntity<MaestroResponse> actualizar(
                Long id,
                MaestroRequest request
    ) {
        return super.actualizar(id, request);
    }

    @Override
    @Operation(
            summary = "Eliminar maestro",
            description = "Eliminar un maestro utilizando su identificador."
    )
    public ResponseEntity<Void> eliminar (Long id){
        return super.eliminar(id);
    }

}
