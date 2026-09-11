package com.carlos.escuela.mappers;

import com.carlos.escuela.dto.calificaciones.CalificacionResponse;
import com.carlos.escuela.entities.Alumno;
import com.carlos.escuela.entities.Calificacion;
import com.carlos.escuela.entities.Grupo;
import com.carlos.escuela.entities.Inscripcion;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class CalificacionMapper {

    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public CalificacionResponse entidadAResponse(
            Calificacion calificacion
    ) {

        Inscripcion inscripcion = calificacion.getInscripcion();
        Alumno alumno = inscripcion.getAlumno();
        Grupo grupo = inscripcion.getGrupo();

        CalificacionResponse.AlumnoCalificacionResponse alumnoResponse =
                new CalificacionResponse.AlumnoCalificacionResponse(
                        alumno.getNombre() + " "
                                + alumno.getApellidoPaterno() + " "
                                + alumno.getApellidoMaterno(),
                        alumno.getMatricula(),
                        alumno.getEmail(),
                        alumno.getFechaIngreso()
                                .format(FORMATO_FECHA)
                );

        CalificacionResponse.GrupoCalificacionResponse grupoResponse =
                new CalificacionResponse.GrupoCalificacionResponse(
                        grupo.getCurso().getNombre(),
                        grupo.getMaestro().getNombre() + " "
                                + grupo.getMaestro().getApellidoPaterno() + " "
                                + grupo.getMaestro().getApellidoMaterno(),
                        grupo.getAula().getNombre(),
                        grupo.getPeriodo()
                );

        CalificacionResponse.InscripcionCalificacionResponse inscripcionResponse =
                new CalificacionResponse.InscripcionCalificacionResponse(
                        alumnoResponse,
                        grupoResponse,
                        inscripcion.getFechaInscripcion()
                                .format(FORMATO_FECHA)
                );

        return new CalificacionResponse(
                calificacion.getId(),
                inscripcionResponse,
                calificacion.getCalificacion(),
                calificacion.getFechaRegistro()
                        .format(FORMATO_FECHA)
        );
    }
}