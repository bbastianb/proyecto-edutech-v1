package com.proyecto.Edutech_v1.proyecto.Edutech_v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Instructor;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.repository.InstructorRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class InstructorService {

    @Autowired
    private InstructorRepository instructorRepository;

    public List<Instructor> listarTodos() {
        return instructorRepository.findAll();
    }

    public Instructor findById(Long id) {
        return instructorRepository.findById(id).orElse(null);
    }

    public Instructor guardar(Instructor instructor) {
        return instructorRepository.save(instructor);
    }

    public void delete(Long id) {
        instructorRepository.deleteById(id);
    }
}
