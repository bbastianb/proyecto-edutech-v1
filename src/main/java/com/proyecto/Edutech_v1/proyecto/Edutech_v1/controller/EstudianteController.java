package com.proyecto.Edutech_v1.proyecto.Edutech_v1.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Estudiante;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.service.EstudianteService;

@RestController
@RequestMapping("/api/estudiantes")
public class EstudianteController {

    @Autowired
    EstudianteService estudianteService;// Inyección de dependencia del servicio de usuario

    @GetMapping()
    public ArrayList<Estudiante> obtenerEstudiantes() {
        return estudianteService.obtenerEstudiantes();
        // Llama al método obtenerEstudiantes del servicio para obtener todos los estudiantes
    }

    @PostMapping("/guardar")
    public Estudiante guardarEstudiante(@RequestBody Estudiante estudiante) {
        return this.estudianteService.guardarEstudiante(estudiante);
        // Llama al método guardarEstudiante del servicio para guardar un estudiante
    }

    @PutMapping("/actualizar/{id}")
    public Estudiante actualizarEstudiante(@PathVariable Long id, @RequestBody Estudiante datosEstudiante) {
        return estudianteService.actualizarEstudiante(id, datosEstudiante);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarEstudiante(@PathVariable Long id) {
        estudianteService.eliminarEstudiante(id);
    }

    @GetMapping("/allinfo")
    public List<Estudiante> obtenerEstudiantesConInstructorYCursos() {
        return estudianteService.obtenerEstudiantesConInstructorYCursos();
    }
}
