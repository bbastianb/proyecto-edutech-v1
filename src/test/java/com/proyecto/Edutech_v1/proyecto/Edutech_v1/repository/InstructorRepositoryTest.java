package com.proyecto.Edutech_v1.proyecto.Edutech_v1.repository;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Instructor;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.repository.InstructorRepository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
public class InstructorRepositoryTest {

    @Autowired
    private InstructorRepository instructorRepository;

    private Instructor instructor;

    @BeforeEach
    public void setUp() {
        instructor = new Instructor();
        instructor.setNombreIn("John");
        instructor.setApellidoIn("Doe");
        instructor.setEmailIn("john.doe@example.com");
        instructor.setTelefonoIn("123456789");
        instructor.setEspecialidad("Math");
        instructor.setCursosImpartidos(3);
        instructor.setNomCursosImpartidos("Course 1, Course 2");
        instructor.setCertificado(true);
        instructor.setContraseñaIn("password123");
    }

    @Test
    public void testSaveInstructor() {
        Instructor savedInstructor = instructorRepository.save(instructor);
        assertNotNull(savedInstructor);
        assertEquals(instructor.getNombreIn(), savedInstructor.getNombreIn());
    }

    @Test
    public void testFindById() {
        Instructor savedInstructor = instructorRepository.save(instructor);
        Instructor foundInstructor = instructorRepository.findById(savedInstructor.getIdIntructor()).orElse(null);
        assertNotNull(foundInstructor);
        assertEquals(savedInstructor.getIdIntructor(), foundInstructor.getIdIntructor());
    }

    @Test
    public void testDeleteInstructor() {
        Instructor savedInstructor = instructorRepository.save(instructor);
        instructorRepository.deleteById(savedInstructor.getIdIntructor());
        Instructor deletedInstructor = instructorRepository.findById(savedInstructor.getIdIntructor()).orElse(null);
        assertNull(deletedInstructor);
    }
}
