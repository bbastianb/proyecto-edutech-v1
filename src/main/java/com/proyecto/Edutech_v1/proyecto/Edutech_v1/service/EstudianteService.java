package com.proyecto.Edutech_v1.proyecto.Edutech_v1.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Estudiante;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.repository.EstudianteRepository;

@Service // Anotación para indicar que esta clase es un servicio
public class EstudianteService {

    @Autowired
    EstudianteRepository estudianteRepository; // Inyección de dependencia del repositorio de estudiantes

    public ArrayList<Estudiante> obtenerEstudiantes() {
        return (ArrayList<Estudiante>) estudianteRepository.findAll();
        // Llama al método findAll del repositorio para obtener todos los estudiantes  
    }

    public Estudiante guardarEstudiante(Estudiante estudiante) {
        return estudianteRepository.save(estudiante);
        // Llama al método save del repositorio para guardar un estudiante
    }

}
