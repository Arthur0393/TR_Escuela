package com.carlos.escuela.services.maestro;

import com.carlos.escuela.dto.maestros.MaestroRequest;
import com.carlos.escuela.dto.maestros.MaestroResponse;

import java.util.List;

public interface MaestroServices {

    MaestroResponse crear(MaestroRequest maestroRequest);

    MaestroResponse buscarPorId(Long idMaestro);

    List<MaestroResponse> listarTodos();

    void eliminar(Long idMaestro);
}