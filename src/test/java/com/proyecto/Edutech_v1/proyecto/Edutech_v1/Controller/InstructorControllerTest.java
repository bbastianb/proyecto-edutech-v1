package com.proyecto.Edutech_v1.proyecto.Edutech_v1.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.assembler.InstructorAssembler;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.dto.InstructorDTO;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.exception.ResourceNotFoundException;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Instructor;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.service.InstructorService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(InstructorController.class)
public class InstructorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private InstructorService instructorService;

    @Mock
    private InstructorAssembler instructorAssembler;

    @InjectMocks
    private InstructorController instructorController;

    private Instructor instructor;
    private InstructorDTO instructorDTO;
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(instructorController).build();
        objectMapper = new ObjectMapper();

        instructor = new Instructor();
        instructor.setIdIntructor(1L);
        instructor.setNombreIn("John");
        instructor.setApellidoIn("Doe");
        instructor.setEmailIn("john.doe@example.com");
        instructor.setContraseñaIn("password123");
        instructor.setTelefonoIn("123456789");
        instructor.setFechaRegistro(LocalDate.now());
        instructor.setEspecialidad("Math");
        instructor.setCursosImpartidos(3);
        instructor.setNomCursosImpartidos("Course 1, Course 2");
        instructor.setCertificado(true);

        instructorDTO = new InstructorDTO();
        instructorDTO.setIdIntructor(1L);
        instructorDTO.setNombreIn("John");
        instructorDTO.setApellidoIn("Doe");
        instructorDTO.setEmailIn("john.doe@example.com");
        instructorDTO.setContraseñaIn("password123");
        instructorDTO.setTelefonoIn("123456789");
        instructorDTO.setFechaRegistro(LocalDate.now());
        instructorDTO.setEspecialidad("Math");
        instructorDTO.setCursosImpartidos(3);
        instructorDTO.setNomCursosImpartidos("Course 1, Course 2");
        instructorDTO.setCertificado(true);
    }

    @Test
    public void testGetAllInstructors() throws Exception {
        List<Instructor> instructors = Arrays.asList(instructor);
        when(instructorService.obtenerTodosLosInstructores()).thenReturn(instructors);
        when(instructorAssembler.toDTO(instructor)).thenReturn(instructorDTO);

        mockMvc.perform(get("/api/instructores")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombreIn").value("John"))
                .andExpect(jsonPath("$[0].apellidoIn").value("Doe"));
    }

    @Test
    public void testGetInstructorById() throws Exception {
        when(instructorService.findById(1L)).thenReturn(instructor);
        when(instructorAssembler.toDTO(instructor)).thenReturn(instructorDTO);

        mockMvc.perform(get("/api/instructores/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombreIn").value("John"))
                .andExpect(jsonPath("$.apellidoIn").value("Doe"));
    }

    @Test
    public void testGetInstructorByIdNotFound() throws Exception {
        when(instructorService.findById(99L)).thenReturn(null);

        mockMvc.perform(get("/api/instructores/{id}", 99L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testAddInstructor() throws Exception {
        when(instructorService.guardarInstructor(any(Instructor.class))).thenReturn(instructor);
        when(instructorAssembler.toDTO(instructor)).thenReturn(instructorDTO);

        mockMvc.perform(post("/api/instructores/agregar")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(instructorDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nombreIn").value("John"))
                .andExpect(jsonPath("$.contraseñaIn").value("password123"));
    }

    @Test
    public void testActualizarInstructorExistente() {
        InstructorDTO datosActualizados = new InstructorDTO();
        datosActualizados.setNombreIn("María");

        when(instructorAssembler.fromDTO(any(InstructorDTO.class))).thenReturn(instructor);
        when(instructorService.actualizarInstructor(eq(1L), any(Instructor.class))).thenReturn(instructor);
        when(instructorAssembler.toDTO(any(Instructor.class))).thenReturn(datosActualizados);

        ResponseEntity<InstructorDTO> respuesta = instructorController.actualizarInstructor(1L, instructor);

        assertEquals(HttpStatus.OK, respuesta.getStatusCode());
        assertEquals("María", respuesta.getBody().getNombreIn());
    }

    @Test
    public void testActualizarInstructorNoExistente() {
        when(instructorService.actualizarInstructor(eq(99L), any(Instructor.class)))
                .thenThrow(new ResourceNotFoundException("Instructor no encontrado"));

        assertThrows(ResourceNotFoundException.class, () -> {
            instructorController.actualizarInstructor(99L, instructor);
        });
    }

    @Test
    public void testDeleteInstructor() throws Exception {
        doNothing().when(instructorService).eliminarInstructor(1L);

        mockMvc.perform(delete("/api/instructores/eliminar/{id}", 1L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());

        verify(instructorService, times(1)).eliminarInstructor(1L);
    }

    @Test
    public void testDeleteInstructorNotFound() throws Exception {
        doThrow(new RuntimeException("Instructor no encontrado")).when(instructorService).eliminarInstructor(99L);

        mockMvc.perform(delete("/api/instructores/eliminar/{id}", 99L)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is5xxServerError());
    }
}
