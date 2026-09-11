package com.carlos.escuela.repositories;

import com.carlos.escuela.entities.Horario;
import com.carlos.escuela.enums.DiaSemana;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HorarioRepository extends JpaRepository<Horario, Long> {

    List<Horario> findAllByOrderByIdAsc();

    @Query("""
            SELECT COUNT(h) > 0
            FROM Horario h
            WHERE h.grupo.id = :idGrupo
              AND h.diaSemana = :dia
              AND h.horaInicio < :horaFin
              AND h.horaFin > :horaInicio
            """)
    boolean existeTraslapePorGrupo(
            @Param("idGrupo") Long idGrupo,
            @Param("dia") DiaSemana dia,
            @Param("horaInicio") String horaInicio,
            @Param("horaFin") String horaFin
    );

    @Query("""
            SELECT COUNT(h) > 0
            FROM Horario h
            WHERE h.grupo.aula.id = :idAula
              AND h.diaSemana = :dia
              AND h.horaInicio < :horaFin
              AND h.horaFin > :horaInicio
            """)
    boolean existeTraslapePorAula(
            @Param("idAula") Long idAula,
            @Param("dia") DiaSemana dia,
            @Param("horaInicio") String horaInicio,
            @Param("horaFin") String horaFin
    );
    @Query("""
        SELECT COUNT(h) > 0
        FROM Horario h
        WHERE h.id <> :idHorario
          AND h.grupo.id = :idGrupo
          AND h.diaSemana = :dia
          AND h.horaInicio < :horaFin
          AND h.horaFin > :horaInicio
        """)
    boolean existeTraslapePorGrupoExcluyendo(
            @Param("idHorario") Long idHorario,
            @Param("idGrupo") Long idGrupo,
            @Param("dia") DiaSemana dia,
            @Param("horaInicio") String horaInicio,
            @Param("horaFin") String horaFin
    );


    @Query("""
        SELECT COUNT(h) > 0
        FROM Horario h
        WHERE h.id <> :idHorario
          AND h.grupo.aula.id = :idAula
          AND h.diaSemana = :dia
          AND h.horaInicio < :horaFin
          AND h.horaFin > :horaInicio
        """)
    boolean existeTraslapePorAulaExcluyendo(
            @Param("idHorario") Long idHorario,
            @Param("idAula") Long idAula,
            @Param("dia") DiaSemana dia,
            @Param("horaInicio") String horaInicio,
            @Param("horaFin") String horaFin
    );
}