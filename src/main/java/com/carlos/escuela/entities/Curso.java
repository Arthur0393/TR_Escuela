package com.carlos.escuela.entities;

import com.carlos.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "CURSOS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_CURSO")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100)
    private String nombre;

    @Column(name = "DESCRIPCION", nullable = false, length = 200)
    private String descripcion;

    @Column(name = "CREDITOS", nullable = false)
    private Integer creditos;

    @Builder.Default
    @OneToMany(mappedBy = "curso", fetch = FetchType.LAZY)
    private List<Grupo> grupos = new ArrayList<>();

    //Metodo para invocar metodos
    private void validarDatos(String nombre, String descripcion, Integer creditos) {
        //Invocacion de metodo para validarDatos
        StringCustomUtils.validarTamanio(nombre, 1, 100,
                "El nombre es requerido y debe tener entre 1 y 100 caracteres");
        //Invocacion de metodo para validarRango
        StringCustomUtils.validarTamanio(descripcion, 1, 200,
                "La descripcion es requerida y debe tener entre 1 y 200 caracteres");
        StringCustomUtils.validarRango(creditos, 1, 10,
                "La descripcion es requerida y debe ser entre el rago 0 - 10");

    }

    //Crea un metodo para actualizar
    public void actualizar (String nombre,
                            String descripcion,
                            Integer creditos) {

        validarDatos(nombre, descripcion, creditos);

        this.nombre = nombre;
        this.descripcion = descripcion;
        this.creditos = creditos;
    }
}
