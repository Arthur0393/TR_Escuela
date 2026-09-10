package com.carlos.escuela.entities;


import com.carlos.escuela.utils.StringCustomUtils;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "AULAS")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Getter
public class Aula {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID_AULA")
    private Long id;

    @Column(name = "NOMBRE", nullable = false, length = 100, unique = true)
    private String nombre;

    @Column(name = "CAPACIDAD", nullable = false)
    private Integer capacidad;

    @Builder.Default
    @OneToMany(mappedBy = "aula", fetch = FetchType.LAZY)
    private List<Grupo> grupos = new ArrayList<>();

    //Metodo para invocar metodos
    private void validarDatos(String nombre, Integer capacidad){
        //Invocacion de metodo para validarDatos
        StringCustomUtils.validarTamanio(nombre, 1, 100,
                "El nombre es requerido y debe tener entre 1 y 100 caracteres");
        //Invocacion de metodo para validarRango
        StringCustomUtils.validarRango(capacidad, 1, 50,
                "El rango es requerido y debe tener entre 1 y 50 personas");
    }

    //Crea un metodo para actualizar
        public void actualizar (String nombre, Integer capacidad) {

        validarDatos(nombre, capacidad);

        this.nombre = nombre;
        this.capacidad = capacidad;
        }

}
