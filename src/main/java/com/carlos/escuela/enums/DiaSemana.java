package com.carlos.escuela.enums;
import com.carlos.escuela.utils.StringCustomUtils;


import com.carlos.escuela.exceptions.RecursoNoEncontradoException;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Getter
public enum DiaSemana {

    LUNES("Lunes"),
    MARTES("Marte"),
    MIERCOLES("Miercoles"),
    JUEVES("Jueves"),
    VIERNES("Viernes"),
    SABADO("Sabado");

    private final String descripcion;

    public static DiaSemana obtenerDiaSemanaPorDescripcion(String descripcion) {

        StringCustomUtils.validarNoVacio(descripcion, "La descripcion es requerida");

        String descripcionNormalizado = StringCustomUtils.quitarAcentos(descripcion);

        for(DiaSemana diaSemana : values()){
            if(StringCustomUtils.quitarAcentos(diaSemana.descripcion).equalsIgnoreCase(descripcionNormalizado))
                return diaSemana;
        }
        throw new RecursoNoEncontradoException("No existe el categoria con ese descripcion" + descripcion);

    }


}


