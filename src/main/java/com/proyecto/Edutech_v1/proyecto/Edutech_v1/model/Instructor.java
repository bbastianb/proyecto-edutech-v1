package com.proyecto.Edutech_v1.proyecto.Edutech_v1.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "instructor")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Instructor extends Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instructor")
    private Long idIntructor;

    @Column(name = "especialidad", nullable = false)
    private String especialidad;

    @Column(name = "calificacion_promedio")
    private Double calificacionPromedio;

    @Column(name = "cursos_impartidos")
    private Integer cursosImpartidos;

    @Column(name = "certificado")
    private Boolean certificado;

   

    public void asignarCurso(Curso curso) {
        cursos.add(curso);
        curso.setInstructor(this);
    }

    // Remover un curso de su gestión
    public void removerCurso(Curso curso) {
        cursos.remove(curso);
        curso.setInstructor(null);
    }

    // Ver cursos gestionados
    public List<Curso> obtenerCursosGestionados() {
        return cursos;
    }

    // Buscar cursos por palabra clave en el título
    public List<Curso> buscarCursosPorTitulo(String palabraClave) {
        return cursos.stream().filter(c -> c.getTituloCurso().toLowerCase().contains(palabraClave.toLowerCase())).collect(Collectors.toList());
    }
    
    @OneToMany(mappedBy = "instructor", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Curso> cursos;
}
