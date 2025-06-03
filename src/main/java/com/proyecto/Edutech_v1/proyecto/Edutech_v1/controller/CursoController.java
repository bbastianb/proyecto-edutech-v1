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
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.service.CursoService;

@RestController
@RequestMapping("/api/cursos")

public class CursoController {
    // el controlador maneja las solicitudes HTTP relacionadas con los cursos

    @Autowired
    CursoService cursoService; // Inyección de dependencia del servicio de curso

    @GetMapping()
    public ArrayList<Curso> obtenerCursos() {
        return cursoService.obtenerCursos();
        // Llama al método obtenerCursos del servicio para obtener todos los cursos
    }

    @PostMapping("/{cursoId}/asignar-gerente/{gerenteId}")
    public ResponseEntity<String> asignarGerente(
            @PathVariable String cursoId,
            @PathVariable Long gerenteId) {

        Optional<Curso> cursoOpt = cursoService.buscarPorId(cursoId);
        if (cursoOpt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("No se asignó: No se encontró el curso con ID: " + cursoId);
        }

        boolean resultado = cursoService.asignarGerentePorId(cursoOpt.get(), gerenteId);
        if (!resultado) {
            return ResponseEntity.badRequest().body("El gerente no existe o ya está asignado a este curso.");
        }
        return ResponseEntity.ok("Gerente asignado correctamente al curso.");
    }

    @PostMapping("/{cursoId}/asignar-instructor/{instructorId}") // aprobado
    public ResponseEntity<String> asignarInstructor(
            @PathVariable String cursoId,
            @PathVariable Long instructorId) {

        Optional<Curso> cursoOpt = cursoService.buscarPorId(cursoId);
        if (cursoOpt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("No se asignó: No se encontró el curso con ID: " + cursoId);
        }

        boolean resultado = cursoService.asignarInstructorPorId(cursoOpt.get(), instructorId);
        if (!resultado) {
            return ResponseEntity.badRequest().body("El instructor no existe o ya está asignado a este curso.");
        }
        return ResponseEntity.ok("Instructor asignado correctamente al curso.");
    }

    @PostMapping("/{cursoId}/inscribir/{estudianteId}") // aprobado
    public ResponseEntity<String> inscribirEstudiante(
            @PathVariable String cursoId,
            @PathVariable Long estudianteId) {

        Optional<Curso> cursoOpt = cursoService.buscarPorId(cursoId);
        if (cursoOpt.isEmpty()) {
            return ResponseEntity.status(404)
                    .body("No se inscribió: No se encontró el curso con ID: " + cursoId);
        }

        boolean resultado = cursoService.inscribirEstudiantePorId(cursoOpt.get(), estudianteId);
        if (!resultado) {
            return ResponseEntity.badRequest().body("El estudiante ya está inscrito o no existe.");
        }
        return ResponseEntity.ok("Estudiante inscrito correctamente");
    }

    @PostMapping("/guardarcurso") // aprobado
    public Curso guardarCurso(@RequestBody Curso curso) {
        return cursoService.guardarCurso(curso);
        // Llama al método guardarCurso del servicio para guardar un curso
    }

    @GetMapping("/buscar/{id}") // aprobado
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
