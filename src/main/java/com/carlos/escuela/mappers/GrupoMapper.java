package com.carlos.escuela.mappers;


import com.carlos.escuela.dto.datos.DatosAula;
import com.carlos.escuela.dto.datos.DatosCurso;
import com.carlos.escuela.dto.datos.DatosMaestro;
import com.carlos.escuela.dto.grupos.GrupoRequest;
import com.carlos.escuela.dto.grupos.GrupoResponse;
import com.carlos.escuela.entities.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Comparator;
import java.util.List;

@Component
@RequiredArgsConstructor
public class GrupoMapper implements CommonMapper <GrupoRequest, GrupoResponse, Grupo>{

    @Override
    public Grupo requestAEntidad(GrupoRequest request) {
        if (request == null) return null;

        return null;
    }

    public Grupo requestAEntidad(
            GrupoRequest request,
            Curso curso,
            Maestro maestro,
            Aula aula
    ) {
        if (request == null) return null;

        return Grupo.builder()
                .curso(curso)
                .maestro(maestro)
                .aula(aula)
                .periodo(request.periodo())
                .build();
    }

        public GrupoResponse entidadAResponse(Grupo entidad) {
        if (entidad == null) return null;

        DatosCurso curso = new DatosCurso(
                entidad.getCurso().getNombre(),
                entidad.getCurso().getDescripcion(),
                entidad.getCurso().getCreditos()
        );

        DatosMaestro maestro = new DatosMaestro(
                entidad.getMaestro().getNombre()
                            + " "
                            + entidad.getMaestro().getApellidoPaterno()
                            + " "
                            + entidad.getMaestro().getApellidoMaterno(),
                entidad.getMaestro().getEmail(),
                entidad.getMaestro().getTelefono()
            );

            DatosAula aula = new DatosAula(
                    entidad.getAula().getNombre(),
                    entidad.getAula().getCapacidad()
            );
            List<String> horarios = entidad.getHorarios()
                    .stream()
                    //Ordenar
                    .sorted(
                            Comparator
                                    .comparing((Horario horario) ->
                                            horario.getDiaSemana().ordinal())
                                    .thenComparing(Horario::getHoraInicio)
                    )
                    .map(horario ->
                            horario.getDiaSemana().getDescripcion()
                                    + " "
                                    + horario.getHoraInicio()
                                    + " - "
                                    + horario.getHoraFin()
                    )
                    .toList();

            return new GrupoResponse(
                    entidad.getId(),
                    curso,
                    maestro,
                    aula,
                    horarios,
                    entidad.getPeriodo()

            );
    }




}
