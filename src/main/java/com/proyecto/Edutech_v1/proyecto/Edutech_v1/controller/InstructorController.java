package com.proyecto.Edutech_v1.proyecto.Edutech_v1.controller;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.assembler.InstructorAssembler;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.dto.InstructorDTO;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Instructor;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.service.InstructorService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/instructores")
public class InstructorController {

    @Autowired
    private InstructorService instructorService;

    @Autowired
    private InstructorAssembler instructorAssembler;

    @GetMapping(produces = {"application/hal+json", "application/json"})

    public ResponseEntity<CollectionModel<InstructorDTO>> all() {
        List<Instructor> instructores = instructorService.obtenerTodosLosInstructores();
        List<InstructorDTO> dtos = instructores.stream()
                .map(instructorAssembler::toModel)
                .collect(Collectors.toList());

        return ResponseEntity.ok(CollectionModel.of(dtos));
    }

    @PostMapping("/agregar")
    public ResponseEntity<InstructorDTO> agregarInstructor(@RequestBody InstructorDTO instructorDTO) {
        Instructor instructor = instructorAssembler.fromDTO(instructorDTO);
        Instructor nuevo = instructorService.guardarInstructor(instructor);
        InstructorDTO respuesta = instructorAssembler.toModel(nuevo);
        return ResponseEntity.status(HttpStatus.CREATED).body(respuesta);
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<InstructorDTO> actualizarInstructor(@PathVariable Long id, @RequestBody Instructor datosInstructor) {
        Instructor actualizado = instructorService.actualizarInstructor(id, datosInstructor);
        InstructorDTO respuesta = instructorAssembler.toModel(actualizado);
        return ResponseEntity.ok(respuesta);
    }

    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<EntityModel<String>> eliminarInstructor(@PathVariable Long id) {
        instructorService.eliminarInstructor(id);
        EntityModel<String> response = EntityModel.of("Instructor eliminado con éxito");
        response.add(linkTo(methodOn(InstructorController.class).all()).withRel("instructores"));
        return ResponseEntity.ok(response);
    }

    @GetMapping(value = "/{id}", produces = {"application/hal+json", "application/json"})

    public ResponseEntity<EntityModel<InstructorDTO>> one(@PathVariable Long id) {
        Instructor instructor = instructorService.findById(id);
        InstructorDTO dto = instructorAssembler.toModel(instructor);

        EntityModel<InstructorDTO> model = EntityModel.of(dto);
        model.add(dto.getLinks());

        return ResponseEntity.ok(model);
    }
}
