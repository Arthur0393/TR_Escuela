package com.carlos.escuela.repositories;

import com.carlos.escuela.entities.Grupo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GrupoRepository extends JpaRepository<Grupo, Long> {

    //"No puedes eliminar este maestro porque tiene grupos."
    boolean existsByMaestroId(Long idMaestro);

    //"No puedes eliminar esta aula porque tiene grupos."
    boolean existsByAulaId(Long idAula);

    //"No puedes eliminar este curso porque tiene grupos."
    boolean existsByCursoId(Long idCurso);

    // Curso + Maestro + Aula + Periodo debe ser único.
    boolean existsByCursoIdAndMaestroIdAndAulaIdAndPeriodo(
            Long idCurso,
            Long idMaestro,
            Long idAula,
            String periodo
    );

    //"Existe esa combinación PERO con un ID diferente al que estoy actualizando?"
    boolean existsByCursoIdAndMaestroIdAndAulaIdAndPeriodoAndIdNot(
            Long idCurso,
            Long idMaestro,
            Long idAula,
            String periodo,
            Long idGrupo
    );

    List<Grupo> findAllByOrderByIdAsc();


}
