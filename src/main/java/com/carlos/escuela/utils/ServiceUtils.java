package com.carlos.escuela.utils;

import com.carlos.escuela.exceptions.RecursoNoEncontradoException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.jpa.repository.JpaRepository;

@Slf4j
public class ServiceUtils {

    public static <E, ID> E ObtenerEntidadOException(
            JpaRepository<E, ID> repository,
            ID id,
            Class<E> clase
    ){

        String nombreEntidad = clase.getSimpleName();

        log.info("Buscando {} con id: {}", nombreEntidad, id);

        return repository.findById(id).orElseThrow(()->
            new RecursoNoEncontradoException(nombreEntidad + " no concontrado con id: " +id));


    }
}
