package com.carlos.escuela.controller;

import com.carlos.escuela.dto.cursos.CursoRequest;
import com.carlos.escuela.dto.cursos.CursoResponse;
import com.carlos.escuela.entities.Curso;
import com.carlos.escuela.services.cursos.CursoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
@Schema(description = "Cursos")
@Tag(
        name = "Cursos",
        description = "Metodos para la gestion de cursos"
)
public class CursoController
        extends CrudController <CursoRequest, CursoResponse, CursoService> {

    public CursoController(CursoService service) {super(service);}

    @Override
    @Operation(
            summary = "Listar cursos",
            description = "Obtener la lista de todas los cursos registrados."
    )
    public ResponseEntity<List<CursoResponse>> listar() {
        return super.listar();
    }

    @Override
    @Operation(
            summary = "Obtener curso por ID",
            description = "Obtiene la información de un curso mediante su identificador."
    )

    public ResponseEntity<CursoResponse> obtenerPorId(Long id) {

        return super.obtenerPorId(id);
    }

    @Override
    @Operation(
            summary = "Registrar curso",
            description = "Registrar un nuevo curso en el sistema"
    )
    public ResponseEntity<CursoResponse> registrar(CursoRequest request){

        return super.registrar(request);

    }
    @Override
    @Operation(
            summary = "Actualizar curso",
            description = "Actualiza la información de un curso mediante su identificador, respetando la unicidad del nombre."
    )
    public ResponseEntity<CursoResponse> actualizar(
            Long id,
            CursoRequest request
    ){
        return super.actualizar(id, request);
    }

    @Override
    @Operation(
            summary = "Eliminar curso",
            description = "Eliminar un curso utilizando su identificador"
    )
    public ResponseEntity<Void> eliminar(Long id){
        return super.eliminar(id);
    }




}
