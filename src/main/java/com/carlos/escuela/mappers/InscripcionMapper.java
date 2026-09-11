package com.carlos.escuela.mappers;

import com.carlos.escuela.dto.inscripciones.InscripcionResponse;
import com.carlos.escuela.entities.Alumno;
import com.carlos.escuela.entities.Grupo;
import com.carlos.escuela.entities.Inscripcion;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class InscripcionMapper {

    private static final DateTimeFormatter FORMATO_FECHA =
            DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public Inscripcion requestAEntidad(
            Alumno alumno,
            Grupo grupo
    ) {
        return Inscripcion.builder()
                .alumno(alumno)
                .grupo(grupo)
                .build();
    }

    public InscripcionResponse entidadAResponse(Inscripcion inscripcion) {

        Alumno alumno = inscripcion.getAlumno();
        Grupo grupo = inscripcion.getGrupo();

        InscripcionResponse.AlumnoInscripcionResponse alumnoResponse =
                new InscripcionResponse.AlumnoInscripcionResponse(
                        alumno.getNombre() + " "
                                + alumno.getApellidoPaterno() + " "
                                + alumno.getApellidoMaterno(),
                        alumno.getMatricula(),
                        alumno.getEmail(),
                        alumno.getFechaIngreso()
                                .format(FORMATO_FECHA)
                );

        InscripcionResponse.GrupoInscripcionResponse grupoResponse =
                new InscripcionResponse.GrupoInscripcionResponse(
                        grupo.getCurso().getNombre(),
                        grupo.getMaestro().getNombre() + " "
                                + grupo.getMaestro().getApellidoPaterno() + " "
                                + grupo.getMaestro().getApellidoMaterno(),
                        grupo.getAula().getNombre(),
                        grupo.getPeriodo()
                );

        return new InscripcionResponse(
                inscripcion.getId(),
                alumnoResponse,
                grupoResponse,
                inscripcion.getCalificacion() != null
                        ? inscripcion.getCalificacion().getCalificacion()
                        : null,
                inscripcion.getFechaInscripcion()
                        .format(FORMATO_FECHA)
        );
    }
}
