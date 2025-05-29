package com.proyecto.Edutech_v1.proyecto.Edutech_v1.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Curso;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Estudiante;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.repository.CursoRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional

public class CursoService {
    // El servicio maneja la lógica de negocio relacionada con los cursos

    @Autowired
    CursoRepository cursoRepository; // Inyección de dependencia del repositorio de cursos  

    public ArrayList<Curso> obtenerCursos() {
        return (ArrayList<Curso>) cursoRepository.findAll();
        // Llama al método findAll del repositorio para obtener todos los cursos
        //metodo para obtener todos los cursos
        // Devuelve una lista de cursos como un ArrayList
    }

    public void inscribirEstudiante(Curso curso, Estudiante estudiante) {
        if (curso.getEstudiantesInscritos() != null && estudiante != null && !curso.getEstudiantesInscritos().contains(estudiante)) {
            curso.getEstudiantesInscritos().add(estudiante);
            estudiante.setCurso(curso);
            cursoRepository.save(curso);
        }

        //metodo para inscribir un estudiante en un curso
        // Verifica que el curso y el estudiante no sean nulos y que el estudiante no esté ya inscrito en el curso
    }

    public Optional<Curso> buscarPorId(String id) {
        return cursoRepository.findById(id);
        //metodo para buscar un curso por su ID
        // Llama al método findById del repositorio para buscar un curso por su ID
    }

    public Curso guardarCurso(Curso curso) {
        return cursoRepository.save(curso);
        //metodo para guardar un curso
        // Llama al método save del repositorio para guardar un curso
    }

}
