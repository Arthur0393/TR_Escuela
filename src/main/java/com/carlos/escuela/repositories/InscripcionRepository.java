package com.carlos.escuela.repositories;

import com.carlos.escuela.entities.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    boolean existsByAlumnoId(Long idAlumno);

    boolean existsByGrupoId(Long idGrupo);
}
