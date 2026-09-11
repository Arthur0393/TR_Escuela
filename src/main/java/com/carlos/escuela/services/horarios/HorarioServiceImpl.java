package com.carlos.escuela.services.horarios;

import com.carlos.escuela.dto.horarios.HorarioRequest;
import com.carlos.escuela.dto.horarios.HorarioResponse;
import com.carlos.escuela.entities.Grupo;
import com.carlos.escuela.entities.Horario;
import com.carlos.escuela.enums.DiaSemana;
import com.carlos.escuela.mappers.HorarioMapper;
import com.carlos.escuela.repositories.GrupoRepository;
import com.carlos.escuela.repositories.HorarioRepository;
import com.carlos.escuela.utils.ServiceUtils;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@AllArgsConstructor
@Transactional
@Slf4j
public class HorarioServiceImpl implements HorarioService {

    private final HorarioRepository horarioRepository;
    private final GrupoRepository grupoRepository;
    private final HorarioMapper horarioMapper;


    @Override
    @Transactional(readOnly = true)
    public List<HorarioResponse> listar() {

        log.info("Listando todos los horarios");

        return horarioRepository.findAllByOrderByIdAsc()
                .stream()
                .map(horarioMapper::entidadAResponse)
                .toList();
    }


    @Override
    @Transactional(readOnly = true)
    public HorarioResponse obtenerPorId(Long id) {

        log.info("Consultando horario con id {}", id);

        Horario horario = obtenerHorario(id);

        return horarioMapper.entidadAResponse(horario);
    }


    private Horario obtenerHorario(Long id) {
        return ServiceUtils.ObtenerEntidadOException(
                horarioRepository,
                id,
                Horario.class
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
    public HorarioResponse registrar(HorarioRequest request) {

        log.info("Registrando nuevo horario...");

        Grupo grupo = obtenerGrupo(request.idGrupo());

        DiaSemana dia = DiaSemana.obtenerDiaSemanaPorDescripcion(request.dia());

        LocalTime horaInicio;
        LocalTime horaFin;

        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");

            horaInicio = LocalTime.parse(request.horaInicio(), formato);
            horaFin = LocalTime.parse(request.horaFin(), formato);

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Las horas deben tener el formato HH:mm"
            );
        }

        if (!horaFin.isAfter(horaInicio)) {
            throw new IllegalArgumentException(
                    "La hora de fin debe ser posterior a la hora de inicio"
            );
        }

        if (horarioRepository.existeTraslapePorGrupo(
                grupo.getId(),
                dia,
                request.horaInicio(),
                request.horaFin()
        )) {
            throw new IllegalArgumentException(
                    "El grupo ya tiene un horario asignado que se traslapa"
            );
        }

        if (horarioRepository.existeTraslapePorAula(
                grupo.getAula().getId(),
                dia,
                request.horaInicio(),
                request.horaFin()
        )) {
            throw new IllegalArgumentException(
                    "El aula ya está ocupada en ese horario"
            );
        }

        Horario horario = horarioMapper.requestAEntidad(
                request,
                grupo,
                dia,
                request.horaInicio(),
                request.horaFin()
        );

        horario = horarioRepository.save(horario);

        log.info("Horario con id {} registrado correctamente", horario.getId());

        return horarioMapper.entidadAResponse(horario);
    }


    @Override
    public HorarioResponse actualizar(HorarioRequest request, Long id) {

        Horario horario = obtenerHorario(id);

        log.info("Actualizando horario con id {}", id);

        Grupo grupo = obtenerGrupo(request.idGrupo());

        DiaSemana dia = DiaSemana.obtenerDiaSemanaPorDescripcion(request.dia());

        LocalTime horaInicio;
        LocalTime horaFin;

        try {
            DateTimeFormatter formato = DateTimeFormatter.ofPattern("HH:mm");

            horaInicio = LocalTime.parse(request.horaInicio(), formato);
            horaFin = LocalTime.parse(request.horaFin(), formato);

        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException(
                    "Las horas deben tener el formato HH:mm"
            );
        }

        if (!horaFin.isAfter(horaInicio)) {
            throw new IllegalArgumentException(
                    "La hora de fin debe ser posterior a la hora de inicio"
            );
        }

        if (horarioRepository.existeTraslapePorGrupoExcluyendo(
                id,
                grupo.getId(),
                dia,
                request.horaInicio(),
                request.horaFin()
        )) {
            throw new IllegalArgumentException(
                    "El grupo ya tiene un horario asignado que se traslapa"
            );
        }

        if (horarioRepository.existeTraslapePorAulaExcluyendo(
                id,
                grupo.getAula().getId(),
                dia,
                request.horaInicio(),
                request.horaFin()
        )) {
            throw new IllegalArgumentException(
                    "El aula ya está ocupada en ese horario"
            );
        }

        horario.actualizar(
                grupo,
                dia,
                request.horaInicio(),
                request.horaFin()
        );

        log.info("Horario con id {} actualizado correctamente", id);

        return horarioMapper.entidadAResponse(horario);
    }


    @Override
    public void eliminar(Long id) {

        Horario horario = obtenerHorario(id);

        log.info("Eliminando horario con id {}", id);

        horarioRepository.delete(horario);

        log.info("Horario con id {} eliminado correctamente", id);
    }
}