package com.proyecto.Edutech_v1.proyecto.Edutech_v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Instructor;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.repository.InstructorRepository;

import jakarta.transaction.Transactional;

@Transactional // Anotación para indicar que los métodos de este servicio deben ser transaccionales 
// es decir, que se ejecutan dentro de una transacción de base de datos

@Service
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository; // Inyección de dependencia del repositorio de instructores

    public List<Instructor> obtenerInstructoresConCursos() {
        return instructorRepository.findAllWithCursos();
    }

    public Instructor guardarInstructor(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public Instructor actualizarInstructor(Long id, Instructor datosInstructor) {
        Instructor instructor = instructorRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Instructor no encontrado"));
        instructor.setEspecialidad(datosInstructor.getEspecialidad());
        instructor.setCalificacionPromedio(datosInstructor.getCalificacionPromedio());
        instructor.setCursosImpartidos(datosInstructor.getCursosImpartidos());
        instructor.setNomCursosImpartidos(datosInstructor.getNomCursosImpartidos());
        instructor.setCertificado(datosInstructor.getCertificado());
        // Actualiza otros campos si es necesario
        return instructorRepository.save(instructor);
    }

    public void eliminarInstructor(Long id) {
        instructorRepository.deleteById(id);
    }
}
