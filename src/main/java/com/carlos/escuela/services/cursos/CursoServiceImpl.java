package com.carlos.escuela.services.cursos;

import com.carlos.escuela.dto.cursos.CursoRequest;
import com.carlos.escuela.dto.cursos.CursoResponse;
import com.carlos.escuela.entities.Curso;
import com.carlos.escuela.exceptions.EntidadRelacionadaException;
import com.carlos.escuela.mappers.CursoMapper;
import com.carlos.escuela.repositories.CursoRepository;
import com.carlos.escuela.repositories.GrupoRepository;
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
public class CursoServiceImpl implements CursoService {

    private final CursoRepository cursoRepository;

    private final GrupoRepository grupoRepository;

    private final CursoMapper cursoMapper;

    //Manda a traer todos los datos del curso
    @Override
    @Transactional(readOnly = true)
    public List<CursoResponse> listar(){
        log.info("Listando todas los cursos");

        return cursoRepository.findAllByOrderByIdAsc().stream()
                .map(cursoMapper::entidadAResponse)
                .toList();
    }

    //Manda a traer el método obtenerPorId
    @Override
    public CursoResponse obtenerPorId(Long id){ return cursoMapper
            .entidadAResponse(obtenerCurso(id));
    }

    //Método para registrar
    @Override
    public CursoResponse registrar(CursoRequest request) {

        log.info("Registrando nuevo curso...");
        Curso curso = cursoMapper.requestAEntidad(request);
        cursoRepository.save(curso);

        log.info("Nuevo curso {} registrado correctamente", curso.getNombre());

        return  cursoMapper.entidadAResponse(curso);
    }

    //Método para actualizar

    @Override
    public CursoResponse actualizar(CursoRequest request, Long id) {

        Curso curso = obtenerCurso(id);

        log.info("Actualizando curso con id {}", id);

        curso.actualizar(
                request.nombre(),
                request.descripcion(),
                request.creditos()
        );

        log.info("Curso {} actualizado correctamente", curso.getNombre());

        return cursoMapper.entidadAResponse(curso);

    }

    @Override
    public void eliminar(Long id) {

        Curso curso = obtenerCurso(id);
        log.info("Eliminando curso con id {}", id);
        if(grupoRepository.existsByCursoId(id))
            throw new EntidadRelacionadaException("No se puede eliminar un curso que ya tiene grupos asignados");

        cursoRepository.delete(curso);
        log.info("Curso con id {} eliminado correctamente", id);
    }


    private Curso obtenerCurso(Long id) {
        return ServiceUtils.ObtenerEntidadOException(
                cursoRepository,
                id,
                Curso.class
        );
    }


}
