package com.carlos.escuela.mappers;


import com.carlos.escuela.dto.aulas.AulaRequest;
import com.carlos.escuela.dto.aulas.AulaResponse;
import com.carlos.escuela.entities.Aula;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AulaMapper implements CommonMapper<AulaRequest, AulaResponse, Aula> {

    @Override
    public Aula requestAEntidad(AulaRequest request){
        if(request == null) return null;

        return Aula.builder()
                .nombre(request.nombre().trim())
                .capacidad(request.capacidad())
                .build();
    }

    @Override
    public AulaResponse entidadAResponse(Aula entidad){
        if(entidad == null) return null;

        return new AulaResponse(
                entidad.getId(),
                entidad.getNombre(),
                entidad.getCapacidad()
        );

    }
}
