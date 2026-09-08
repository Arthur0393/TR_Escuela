package com.carlos.escuela.mappers;

import com.carlos.escuela.dto.maestros.MaestroRequest;
import com.carlos.escuela.dto.maestros.MaestroResponse;
import com.carlos.escuela.entities.Maestro;
import org.springframework.stereotype.Component;

@Component
public class MaestroMapper {

    public MaestroResponse maestroToMaestroResponse(Maestro maestro) {
        if (maestro == null) return null;

        return new MaestroResponse(
                maestro.getId(),
                maestro.getNombre(),
                maestro.getApellidoPaterno(),
                maestro.getApellidoMaterno(),
                maestro.getEmail(),
                maestro.getTelefono()
        );
    }

    public Maestro maestroRequestToMaestro(MaestroRequest maestroRequest) {
        if (maestroRequest == null) return null;

        return Maestro.builder()
                .nombre(maestroRequest.nombre())
                .apellidoPaterno(maestroRequest.apellidoPaterno())
                .apellidoMaterno(maestroRequest.apellidoMaterno())
                .email(maestroRequest.email())
                .telefono(maestroRequest.telefono())
                .build();
    }
}