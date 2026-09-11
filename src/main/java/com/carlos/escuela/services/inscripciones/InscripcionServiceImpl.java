package com.carlos.escuela.services.inscripciones;

import com.carlos.escuela.dto.inscripciones.InscripcionRequest;
import com.carlos.escuela.dto.inscripciones.InscripcionResponse;
import com.carlos.escuela.entities.Alumno;
import com.carlos.escuela.entities.Grupo;
import com.carlos.escuela.entities.Inscripcion;
import com.carlos.escuela.exceptions.EntidadRelacionadaException;
import com.carlos.escuela.mappers.InscripcionMapper;
import com.carlos.escuela.repositories.AlumnoRepository;
import com.carlos.escuela.repositories.GrupoRepository;
import com.carlos.escuela.repositories.InscripcionRepository;
import com.carlos.escuela.utils.ServiceUtils;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class InscripcionServiceImpl implements InscripcionService {

    private final InscripcionRepository inscripcionRepository;
    private final AlumnoRepository alumnoRepository;
    private final GrupoRepository grupoRepository;
    private final InscripcionMapper inscripcionMapper;


    @Override
    @Transactional(readOnly = true)
    public List<InscripcionResponse> listar() {

        log.info("Listando todas las inscripciones");

        return inscripcionRepository.findAllByOrderByIdAsc()
                .stream()
                .map(inscripcionMapper::entidadAResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public InscripcionResponse obtenerPorId(Long id) {

        log.info("Consultando inscripción con id {}", id);

        Inscripcion inscripcion = obtenerInscripcion(id);

        return inscripcionMapper.entidadAResponse(inscripcion);
    }


    private Inscripcion obtenerInscripcion(Long id) {

        return ServiceUtils.ObtenerEntidadOException(
                inscripcionRepository,
                id,
                Inscripcion.class
        );
    }


    private Alumno obtenerAlumno(Long id) {

        return ServiceUtils.ObtenerEntidadOException(
                alumnoRepository,
                id,
                Alumno.class
        );
    }


    private Grupo obtenerGrupo(Long id) {

        return ServiceUtils.ObtenerEntidadOException(
                grupoRepository,
                id,
                Grupo.class
        );
    }


    @Override
    public InscripcionResponse registrar(InscripcionRequest request) {

        log.info("Registrando nueva inscripción...");

        Alumno alumno = obtenerAlumno(request.idAlumno());

        Grupo grupo = obtenerGrupo(request.idGrupo());


        if (inscripcionRepository.existsByAlumnoIdAndGrupoId(
                alumno.getId(),
                grupo.getId()
        )) {

            throw new IllegalArgumentException(
                    "El alumno ya está inscrito en este grupo"
            );
        }


        Inscripcion inscripcion = inscripcionMapper.requestAEntidad(
                alumno,
                grupo
        );


        inscripcion = inscripcionRepository.save(inscripcion);


        log.info(
                "Inscripción con id {} registrada correctamente",
                inscripcion.getId()
        );


        return inscripcionMapper.entidadAResponse(inscripcion);
    }


    @Override
    public InscripcionResponse actualizar(
            InscripcionRequest request,
            Long id
    ) {

        Inscripcion inscripcion = obtenerInscripcion(id);

        log.info(
                "Actualizando inscripción con id {}",
                id
        );


        Alumno alumno = obtenerAlumno(request.idAlumno());

        Grupo grupo = obtenerGrupo(request.idGrupo());


        // Validar que la combinación Alumno + Grupo
        // no exista en otra inscripción.
        if (inscripcionRepository.existsByAlumnoIdAndGrupoIdAndIdNot(
                alumno.getId(),
                grupo.getId(),
                inscripcion.getId()
        )) {

            throw new IllegalArgumentException(
                    "El alumno ya está inscrito en este grupo"
            );
        }


        inscripcion.actualizar(
                alumno,
                grupo
        );


        log.info(
                "Inscripción con id {} actualizada correctamente",
                id
        );


        return inscripcionMapper.entidadAResponse(inscripcion);
    }


    @Override
    public void eliminar(Long id) {

        Inscripcion inscripcion = obtenerInscripcion(id);

        log.info(
                "Eliminando inscripción con id {}",
                id
        );


        // Una inscripción con calificación
        // no puede ser eliminada.
        if (inscripcion.getCalificacion() != null) {

            throw new EntidadRelacionadaException(
                    "No se puede eliminar la inscripción porque tiene una calificación asociada"
            );
        }


        inscripcionRepository.delete(inscripcion);


        log.info(
                "Inscripción con id {} eliminada correctamente",
                id
        );
    }
}