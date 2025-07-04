package com.proyecto.Edutech_v1.proyecto.Edutech_v1.service;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.exception.ResourceNotFoundException;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Instructor;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.repository.InstructorRepository;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.dto.InstructorDTO;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class InstructorServiceTest {

    @Mock
    private InstructorRepository instructorRepository;

    @InjectMocks
    private InstructorService instructorService;

    @BeforeEach
    void setUp() {
        // This method is called before each test to initialize mocks
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindById_ExisteInstructor() {
        Instructor instructor = new Instructor();
        instructor.setIdIntructor(1L);
        instructor.setNombreIn("Reimel");
        instructor.setApellidoIn("González");
        instructor.setEmailIn("reimel@edutech.cl");

        when(instructorRepository.findById(1L)).thenReturn(Optional.of(instructor));

        Instructor resultado = instructorService.findById(1L);

        assertNotNull(resultado);
        assertEquals("Reimel", resultado.getNombreIn());
        assertEquals("González", resultado.getApellidoIn());
    }

    @Test
    void testFindById_NoExisteInstructor() {
        when(instructorRepository.findById(99L)).thenReturn(Optional.empty());

        Exception exception = assertThrows(ResourceNotFoundException.class, () -> {
            instructorService.findById(99L);
        });

        assertEquals("Instructor no encontrado con ID: 99", exception.getMessage());
    }
}
