package com.proyecto.Edutech_v1.proyecto.Edutech_v1.controller;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Curso;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Estudiante;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.service.CursoService;

@RestController
@RequestMapping("/api/v1/cursos")

public class CursoController {

    @Autowired
    CursoService cursoService; // Inyección de dependencia del servicio de curso

    @GetMapping()
    public ArrayList<Curso> obtenerCursos() {
        return cursoService.obtenerCursos();
        // Llama al método obtenerCursos del servicio para obtener todos los cursos
    }

    @PostMapping("/{cursoId}/inscribir")
    public ResponseEntity<String> inscribirEstudiante(
            @PathVariable Long cursoId,
            @RequestBody Estudiante estudiante) {

        Curso curso = cursoService.buscarPorId(cursoId);
        if (curso == null) {
            return ResponseEntity.notFound().build();
        }

        cursoService.inscribirEstudiante(curso, estudiante);
        return ResponseEntity.ok("Estudiante inscrito correctamente");
        // Llama al método inscribirEstudiante del servicio para inscribir un estudiante en un curso
    }
}
