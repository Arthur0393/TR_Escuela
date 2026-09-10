package com.carlos.escuela.services.grupos;

import com.carlos.escuela.dto.grupos.GrupoRequest;
import com.carlos.escuela.dto.grupos.GrupoResponse;
import com.carlos.escuela.entities.Aula;
import com.carlos.escuela.entities.Curso;
import com.carlos.escuela.entities.Grupo;
import com.carlos.escuela.entities.Maestro;
import com.carlos.escuela.exceptions.EntidadRelacionadaException;
import com.carlos.escuela.exceptions.RecursoNoEncontradoException;
import com.carlos.escuela.mappers.GrupoMapper;
import com.carlos.escuela.repositories.*;
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
public class GrupoServiceImpl implements GrupoService {

    private final GrupoRepository grupoRepository;
    private final CursoRepository cursoRepository;
    private final MaestroRepository maestroRepository;
    private final AulaRepository aulaRepository;
    private final InscripcionRepository inscripcionRepository;
    private final GrupoMapper grupoMapper;


    @Override
    @Transactional(readOnly = true)
    public List<GrupoResponse> listar() {

        log.info("Listando todos los grupos");

        return grupoRepository.findAllByOrderByIdAsc().stream()
                .map(grupoMapper::entidadAResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public GrupoResponse obtenerPorId(Long id) {

        log.info("Consultando grupo con id {}", id);

        Grupo grupo = obtenerGrupo(id);

        return grupoMapper.entidadAResponse(grupo);
    }


    @Override
    public GrupoResponse registrar(GrupoRequest request) {

        log.info("Registrando nuevo grupo...");

        if (grupoRepository.existsByCursoIdAndMaestroIdAndAulaIdAndPeriodo(
                request.idCurso(),
                request.idMaestro(),
                request.idAula(),
                request.periodo()
        )) {
            throw new RecursoNoEncontradoException(
                    "Ya existe un grupo con el mismo curso, maestro, aula y periodo"
            );
        }

        Curso curso = obtenerCurso(request.idCurso());
        Maestro maestro = obtenerMaestro(request.idMaestro());
        Aula aula = obtenerAula(request.idAula());

        Grupo grupo = grupoMapper.requestAEntidad(
                request,
                curso,
                maestro,
                aula
        );

        grupoRepository.save(grupo);

        log.info("Grupo con id {} registrado correctamente", grupo.getId());

        return grupoMapper.entidadAResponse(grupo);
    }


    private Grupo obtenerGrupo(Long id) {
        return ServiceUtils.ObtenerEntidadOException(
                grupoRepository,
                id,
                Grupo.class
        );
    }


    private Curso obtenerCurso(Long id) {
        return ServiceUtils.ObtenerEntidadOException(
                cursoRepository,
                id,
                Curso.class
        );
    }


    private Maestro obtenerMaestro(Long id) {
        return ServiceUtils.ObtenerEntidadOException(
                maestroRepository,
                id,
                Maestro.class
        );
    }


    private Aula obtenerAula(Long id) {
        return ServiceUtils.ObtenerEntidadOException(
                aulaRepository,
                id,
                Aula.class
        );
    }


    @Override
    public GrupoResponse actualizar(GrupoRequest request, Long id) {

        Grupo grupo = obtenerGrupo(id);

        log.info("Actualizando grupo con id {}", id);

        if (grupoRepository.existsByCursoIdAndMaestroIdAndAulaIdAndPeriodoAndIdNot(
                request.idCurso(),
                request.idMaestro(),
                request.idAula(),
                request.periodo(),
                id
        )) {
            throw new RecursoNoEncontradoException(
                    "Ya existe otro grupo con el mismo curso, maestro, aula y periodo"
            );
        }
        Curso curso = obtenerCurso(request.idCurso());
        Maestro maestro = obtenerMaestro(request.idMaestro());
        Aula aula = obtenerAula(request.idAula());

        grupo.actualizar(
                curso,
                maestro,
                aula,
                request.periodo()
        );

        log.info("Grupo {} actualizado correctamente", id);

        return grupoMapper.entidadAResponse(grupo);
    }

    @Override
    public void eliminar(Long id) {

        Grupo grupo = obtenerGrupo(id);

        log.info("Eliminando grupo con id {}", id);

        if (!grupo.getHorarios().isEmpty()) {
            throw new EntidadRelacionadaException(
                    "No se puede eliminar un grupo que tiene horarios asociados"
            );
        }

        if (inscripcionRepository.existsByGrupoId(id)) {
            throw new EntidadRelacionadaException(
                    "No se puede eliminar un grupo que tiene inscripciones asociadas"
            );
        }

        grupoRepository.delete(grupo);

        log.info("Grupo con id {} eliminado correctamente", id);

    }
}