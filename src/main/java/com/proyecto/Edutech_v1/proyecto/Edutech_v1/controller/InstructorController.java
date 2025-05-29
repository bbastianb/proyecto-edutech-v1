package com.proyecto.Edutech_v1.proyecto.Edutech_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Instructor;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.service.InstructorService;

@RestController
@RequestMapping("/api/v1/instructores")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;
    // Inyección de dependencia del servicio de instructor

    @GetMapping
    public ResponseEntity<List<Instructor>> listar() {
        List<Instructor> instructores = instructorService.listarTodos();
        return instructores.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(instructores);
    }

    @PostMapping
    public ResponseEntity<Instructor> guardar(@RequestBody Instructor instructor) {
        Instructor nuevo = instructorService.guardar(instructor);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevo);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Instructor> buscar(@PathVariable Long id) {
        Instructor instructor = instructorService.findById(id);
        return instructor != null ? ResponseEntity.ok(instructor) : ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Instructor> actualizar(@PathVariable Long id, @RequestBody Instructor instructor) {
        Instructor existente = instructorService.findById(id);
        if (existente == null) {
            return ResponseEntity.notFound().build();
        }

        instructor.setId(id);
        return ResponseEntity.ok(instructorService.guardar(instructor));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> eliminar(@PathVariable Long id) {
        instructorService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
