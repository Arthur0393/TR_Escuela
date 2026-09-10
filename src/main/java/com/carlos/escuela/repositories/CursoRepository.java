package com.carlos.escuela.repositories;

import com.carlos.escuela.entities.Curso;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CursoRepository extends JpaRepository<Curso, Long> {

    boolean existsByNombreIgnoreCase(String nombre);


    List<Curso> findAllByOrderByIdAsc();
}
