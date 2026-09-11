    package com.carlos.escuela.services.calificaciones;

    import com.carlos.escuela.dto.calificaciones.CalificacionRequest;
    import com.carlos.escuela.dto.calificaciones.CalificacionResponse;
    import com.carlos.escuela.entities.Calificacion;
    import com.carlos.escuela.entities.Inscripcion;
    import com.carlos.escuela.mappers.CalificacionMapper;
    import com.carlos.escuela.repositories.CalificacionRepository;
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
    public class CalificacionServiceImpl implements CalificacionService {

        private final CalificacionRepository calificacionRepository;
        private final InscripcionRepository inscripcionRepository;
        private final CalificacionMapper calificacionMapper;

        @Override
        @Transactional(readOnly = true)
        public List<CalificacionResponse> listar() {

            log.info("Listando todas las calificaciones");

            return calificacionRepository.findAllByOrderByIdAsc()
                    .stream()
                    .map(calificacionMapper::entidadAResponse)
                    .toList();
        }

        @Override
        @Transactional(readOnly = true)
        public CalificacionResponse obtenerPorId(Long id) {

            log.info("Consultando calificación con id {}", id);

            Calificacion calificacion = obtenerCalificacion(id);

            return calificacionMapper.entidadAResponse(calificacion);
        }

        private Calificacion obtenerCalificacion(Long id) {

            return ServiceUtils.ObtenerEntidadOException(
                    calificacionRepository,
                    id,
                    Calificacion.class
            );
        }

        private Inscripcion obtenerInscripcion(Long id) {

            return ServiceUtils.ObtenerEntidadOException(
                    inscripcionRepository,
                    id,
                    Inscripcion.class
            );
        }

        @Override
        public CalificacionResponse registrar(CalificacionRequest request) {

            log.info(
                    "Registrando calificación para inscripción con id {}",
                    request.idInscripcion()
            );

            Inscripcion inscripcion = obtenerInscripcion(
                    request.idInscripcion()
            );

            if (calificacionRepository.existsByInscripcionId(
                    inscripcion.getId()
            )) {

                throw new IllegalStateException(
                        "La inscripción ya tiene una calificación asociada"
                );
            }

            Calificacion calificacion = Calificacion.builder()
                    .inscripcion(inscripcion)
                    .calificacion(request.calificacion())
                    .build();

            calificacion = calificacionRepository.save(calificacion);

            log.info(
                    "Calificación con id {} registrada correctamente",
                    calificacion.getId()
            );

            return calificacionMapper.entidadAResponse(calificacion);
        }

        @Override
        public CalificacionResponse actualizar(
                CalificacionRequest request,
                Long id
        ) {

            Calificacion calificacion = obtenerCalificacion(id);

            log.info(
                    "Actualizando calificación con id {}",
                    id
            );

            calificacion.actualizar(
                    request.calificacion()
            );

            log.info(
                    "Calificación con id {} actualizada correctamente",
                    id
            );

            return calificacionMapper.entidadAResponse(calificacion);
        }

        @Override
        public void eliminar(Long id) {

            Calificacion calificacion = obtenerCalificacion(id);

            log.info(
                    "Eliminando calificación con id {}",
                    id
            );

            calificacionRepository.delete(calificacion);

            log.info(
                    "Calificación con id {} eliminada correctamente",
                    id
            );
        }
    }