package com.carlos.escuela.repositories;

import com.carlos.escuela.dto.maestros.MaestroResponse;
import com.carlos.escuela.entities.Maestro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaestroRepository extends JpaRepository<Maestro, Long> {

    boolean existsByEmail(String email);

    boolean existsByTelefono(String telefono);

}