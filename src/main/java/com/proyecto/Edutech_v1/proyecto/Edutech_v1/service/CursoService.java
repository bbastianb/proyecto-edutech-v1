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

    @Autowired
    CursoRepository cursoRepository; // Inyección de dependencia del repositorio de cursos  

    public ArrayList<Curso> obtenerCursos() {
        return (ArrayList<Curso>) cursoRepository.findAll();
        // Llama al método findAll del repositorio para obtener todos los cursos
    }

    public void inscribirEstudiante(Curso curso, Estudiante estudiante) {
        if (curso.getEstudiantesInscritos() != null && estudiante != null && !curso.getEstudiantesInscritos().contains(estudiante)) {
            curso.getEstudiantesInscritos().add(estudiante);
            estudiante.setCurso(curso);
            cursoRepository.save(curso); // Guarda los cambios en la base de datos
        }
    }

    public Curso buscarPorId(Long id) {
        Optional<Curso> cursoOpt = cursoRepository.findById(id);
        return cursoOpt.orElse(null);
    }

}
