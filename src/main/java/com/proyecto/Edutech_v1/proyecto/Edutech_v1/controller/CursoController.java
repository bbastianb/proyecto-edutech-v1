package com.proyecto.Edutech_v1.proyecto.Edutech_v1.controller;

import java.util.ArrayList;
import java.util.Optional;

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
@RequestMapping("/api/cursos")

public class CursoController {
    //el controlador maneja las solicitudes HTTP relacionadas con los cursos

    @Autowired
    CursoService cursoService; // Inyección de dependencia del servicio de curso

    @GetMapping()
    public ArrayList<Curso> obtenerCursos() {
        return cursoService.obtenerCursos();
        // Llama al método obtenerCursos del servicio para obtener todos los cursos
    }

    @PostMapping("/{cursoId}/inscribir")
    public ResponseEntity<String> inscribirEstudiante(
            @PathVariable String cursoId,
            @RequestBody Estudiante estudiante) {

        Optional<Curso> cursoOpt = cursoService.buscarPorId(cursoId);
        if (cursoOpt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("No se inscribió: Ourrio un error al buscar el curso con ID: " + cursoId);
        }

        cursoService.inscribirEstudiante(cursoOpt.get(), estudiante);
        return ResponseEntity.ok("Estudiante inscrito correctamente");

        // Llama al método inscribirEstudiante del servicio para inscribir un estudiante en un curso
    }

    @PostMapping("/guardarcurso")
    public Curso guardarCurso(@RequestBody Curso curso) {
        return cursoService.guardarCurso(curso);
        // Llama al método guardarCurso del servicio para guardar un curso
    }

    @GetMapping("/buscar/{id}")
    public ResponseEntity<?> buscarPorId(@PathVariable String id) {
        Optional<Curso> cursoOpt = cursoService.buscarPorId(id);
        if (cursoOpt.isPresent()) {
            return ResponseEntity.ok(cursoOpt.get());
        } else {
            return ResponseEntity.status(404).body("El curso con ID " + id + " no fue encontrado.");
        }
        // Llama al método buscarPorId del servicio para buscar un curso por su ID
    }

}
