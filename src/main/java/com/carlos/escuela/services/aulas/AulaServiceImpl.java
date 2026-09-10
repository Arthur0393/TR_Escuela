package com.carlos.escuela.services.aulas;


import com.carlos.escuela.dto.aulas.AulaRequest;
import com.carlos.escuela.dto.aulas.AulaResponse;
import com.carlos.escuela.entities.Aula;
import com.carlos.escuela.exceptions.EntidadRelacionadaException;
import com.carlos.escuela.mappers.AulaMapper;
import com.carlos.escuela.repositories.AulaRepository;
import com.carlos.escuela.repositories.GrupoRepository;
import com.carlos.escuela.services.aulas.AulaService;
import com.carlos.escuela.utils.ServiceUtils;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j

public class AulaServiceImpl implements AulaService {

    private final AulaRepository aulaRepository;

    private final GrupoRepository grupoRepository;

    private final AulaMapper aulaMapper;


    //Manda a traer todos los datos del aula
    @Override
    @Transactional(readOnly = true)
    public List<AulaResponse> listar(){
        log.info("Listando todas la aulas");

        return aulaRepository.findAllByOrderByIdAsc().stream()
                .map(aulaMapper::entidadAResponse)
                .toList();
    }

    //Manda a traer el metodo obtenerPorId
    @Override
    public AulaResponse obtenerPorId(Long id){ return aulaMapper
            .entidadAResponse(obtenerAula(id));
    }

    //Metodo para registrar
    @Override
    public AulaResponse registrar(AulaRequest request) {

        log.info("Registrando nueva aula...");
        Aula aula = aulaMapper.requestAEntidad(request);
        aulaRepository.save(aula);

        log.info("Nueva aula {} registrada correctamente", aula.getNombre());

        return  aulaMapper.entidadAResponse(aula);
    }

    //Metodo para actualizar

    @Override
    public AulaResponse actualizar(AulaRequest request, Long id) {

        Aula aula = obtenerAula(id);

        log.info("Actualizando aula con id {}", id);

        aula.actualizar(
                request.nombre(),
                request.capacidad()
        );

        log.info("Aula {} actualizada correctamente", aula.getNombre());

        return aulaMapper.entidadAResponse(aula);

    }

    @Override
    public void eliminar(Long id) {

        Aula aula = obtenerAula(id);
        log.info("Eliminando aula con id {}", id);
        if(grupoRepository.existsByAulaId(id))
            throw new EntidadRelacionadaException("No se puede eliminar un aula que ya tiene grupos asignados");

        aulaRepository.delete(aula);
        log.info("Aula con id {} eliminada correctamente", id);
    }


/*
    @Override
    public void eliminar(Long id) {

        Aula aula = obtenerAula(id);

        log.info("PASO 1 - Aula encontrada: {}", id);

        if (grupoRepository.existsByAulaId(id)) {

            log.info("PASO 2 - El aula tiene grupos");

            throw new EntityNotFoundException(
                    "No se puede eliminar el aula porque tiene grupos asignados"
            );
        }

        log.info("PASO 3 - El aula NO tiene grupos");

        aulaRepository.delete(aula);

        log.info("PASO 4 - Aula eliminada");
    }
*/
    private Aula obtenerAula(Long id){
        return ServiceUtils.ObtenerEntidadOException(
                aulaRepository,
                id,
                Aula.class
        );


    }



}
