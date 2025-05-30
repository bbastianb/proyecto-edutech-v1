package com.proyecto.Edutech_v1.proyecto.Edutech_v1.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "instructor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor extends Usuario { //extends Usuario

    @Column(name = "especialidad", nullable = false)
    private String especialidad;

    @Column(name = "calificacion_promedio")
    private Double calificacionPromedio;

    @Column(name = "cursos_impartidos")
    private Integer cursosImpartidos;

    @Column(name = "nombres_cursos_impartidos")
    private String nomCursosImpartidos;

    @Column(name = "certificado")
    private Boolean certificado;

    @OneToMany(mappedBy = "instructor")
    private List<Curso> cursos = new ArrayList<>();
}
