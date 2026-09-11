package com.carlos.escuela.mappers;

import com.carlos.escuela.dto.horarios.HorarioRequest;
import com.carlos.escuela.dto.horarios.HorarioResponse;
import com.carlos.escuela.entities.Grupo;
import com.carlos.escuela.entities.Horario;
import com.carlos.escuela.enums.DiaSemana;
import org.springframework.stereotype.Component;

@Component
public class HorarioMapper {

    public Horario requestAEntidad(
            HorarioRequest request,
            Grupo grupo,
            DiaSemana dia,
            String horaInicio,
            String horaFin
    ) {

        if (request == null) return null;

        return Horario.builder()
                .grupo(grupo)
                .diaSemana(dia)
                .horaInicio(horaInicio)
                .horaFin(horaFin)
                .build();
    }


    public HorarioResponse entidadAResponse(Horario entidad) {

        if (entidad == null) return null;

        Grupo grupo = entidad.getGrupo();

        String maestro =
                grupo.getMaestro().getNombre()
                        + " "
                        + grupo.getMaestro().getApellidoPaterno()
                        + " "
                        + grupo.getMaestro().getApellidoMaterno();

        String horario =
                entidad.getDiaSemana().getDescripcion()
                        + " "
                        + entidad.getHoraInicio()
                        + " "
                        + entidad.getHoraFin();

        HorarioResponse.GrupoHorarioResponse grupoResponse =
                new HorarioResponse.GrupoHorarioResponse(
                        grupo.getCurso().getNombre(),
                        maestro,
                        grupo.getAula().getNombre(),
                        grupo.getPeriodo()
                );

        return new HorarioResponse(
                entidad.getId(),
                grupoResponse,
                horario
        );
    }
}