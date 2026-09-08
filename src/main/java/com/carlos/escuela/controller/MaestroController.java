package com.carlos.escuela.controller;

import com.carlos.escuela.dto.maestros.MaestroRequest;
import com.carlos.escuela.dto.maestros.MaestroResponse;
import com.carlos.escuela.services.maestros.MaestroService;
import io.swagger.v3.oas.annotations.media.Schema;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/maestros")

@Schema(description = "")
public class MaestroController extends CrudController<MaestroRequest, MaestroResponse, MaestroService>{

    public  MaestroController(MaestroService service) {
        super(service);
    }
}
