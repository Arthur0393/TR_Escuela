package com.carlos.escuela.controller;


import com.carlos.escuela.dto.aulas.AulaRequest;
import com.carlos.escuela.dto.aulas.AulaResponse;
import com.carlos.escuela.services.aulas.AulaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/aulas")
@Schema(description = "Aulas")
@Tag(
        name = "Aulas",
        description = "Metodos para la gestion de aulas"
)
public class AulaController
        extends CrudController<AulaRequest, AulaResponse, AulaService> {

    public AulaController(AulaService service) {
        super(service);
    }

    @Override
    @Operation(
            summary = "Listar aulas",
            description = "Obtener la lista de todas las aulas registradas."
    )
    public ResponseEntity<List<AulaResponse>> listar() {
        return super.listar();
    }

    @Override
    @Operation(
            summary = "Obtener aula por ID",
            description = "Obtiene la información de una aula mediante su identificador."
    )

    public ResponseEntity<AulaResponse> obtenerPorId(Long id) {

        return super.obtenerPorId(id);
    }

    @Override
    @Operation(
            summary = "Registrar aula",
            description = "Registrar una nueva aula en el sistema"
    )
public ResponseEntity<AulaResponse> registrar(AulaRequest request){

        return super.registrar(request);

}
    @Override
    @Operation(
            summary = "Actualizar aula",
            description = "Actualiza la información de un aula mediante su identificador, respetando la unicidad del nombre."
    )
    public ResponseEntity<AulaResponse> actualizar(
            Long id,
            AulaRequest request
    ){
        return super.actualizar(id, request);
    }

    @Override
    @Operation(
            summary = "Eliminar aula",
            description = "Eliminar un aula utilizando su identificador"
    )
    public ResponseEntity<Void> eliminar(Long id){
        return super.eliminar(id);
    }




}
