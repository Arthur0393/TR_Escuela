package com.carlos.escuela.services.maestro;

import com.carlos.escuela.dto.maestros.MaestroRequest;
import com.carlos.escuela.dto.maestros.MaestroResponse;
import com.carlos.escuela.entities.Maestro;
import com.carlos.escuela.mappers.MaestroMapper;
import com.carlos.escuela.repositories.MaestroRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MaestroServiceImpl implements MaestroServices {

    private final MaestroRepository maestroRepository;
    private final MaestroMapper maestroMapper;

    @Override
    @Transactional
    public MaestroResponse crear(MaestroRequest maestroRequest) {
        if (maestroRepository.existsByEmail(maestroRequest.email())) {
            throw new IllegalStateException("Ya existe un maestro con ese email");
        }

        if (maestroRepository.existsByTelefono(maestroRequest.telefono())) {
            throw new IllegalStateException("Ya existe un maestro con ese telefono");
        }

        Maestro maestro = maestroMapper.maestroRequestToMaestro(maestroRequest);

        Maestro maestroGuardado = maestroRepository.save(maestro);

        return maestroMapper.maestroToMaestroResponse(maestroGuardado);
    }

    @Override
    @Transactional(readOnly = true)
    public MaestroResponse buscarPorId(Long idMaestro) {
        Maestro maestro = maestroRepository.findById(idMaestro)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "No existe un maestro con id: " + idMaestro
                        )
                );

        return maestroMapper.maestroToMaestroResponse(maestro);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MaestroResponse> listarTodos() {
        return maestroRepository.findAll()
                .stream()
                .map(maestroMapper::maestroToMaestroResponse)
                .toList();
    }

    @Override
    @Transactional
    public void eliminar(Long idMaestro) {
        if (!maestroRepository.existsById(idMaestro)) {
            throw new EntityNotFoundException(
                    "No existe un maestro con id: " + idMaestro
            );
        }

        maestroRepository.deleteById(idMaestro);
    }
}