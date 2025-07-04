package com.proyecto.Edutech_v1.proyecto.Edutech_v1.assembler;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.controller.InstructorController;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.dto.InstructorDTO;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Instructor;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.stereotype.Component;
import org.springframework.hateoas.Link;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
public class InstructorAssembler implements RepresentationModelAssembler<Instructor, InstructorDTO> {

    @Override
    public InstructorDTO toModel(Instructor instructor) {
        InstructorDTO dto = new InstructorDTO();

        dto.setIdIntructor(instructor.getIdIntructor());
        dto.setNombreIn(instructor.getNombreIn());
        dto.setApellidoIn(instructor.getApellidoIn());
        dto.setEmailIn(instructor.getEmailIn());
        dto.setContraseñaIn(instructor.getContraseñaIn());
        dto.setTelefonoIn(instructor.getTelefonoIn());
        dto.setFechaRegistro(instructor.getFechaRegistro());
        dto.setEspecialidad(instructor.getEspecialidad());
        dto.setCursosImpartidos(instructor.getCursosImpartidos());
        dto.setNomCursosImpartidos(instructor.getNomCursosImpartidos());
        dto.setCertificado(instructor.getCertificado());

        // Links HATEOAS
        dto.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(InstructorController.class)
                        .one(instructor.getIdIntructor()))
                .withSelfRel());

        dto.add(WebMvcLinkBuilder.linkTo(
                WebMvcLinkBuilder.methodOn(InstructorController.class)
                        .all())
                .withRel("instructores"));

        return dto;
    }

    // Alias para mantener compatibilidad con los tests que usan toDTO
    public InstructorDTO toDTO(Instructor instructor) {
        return toModel(instructor);
    }

    public WebMvcLinkBuilder linkToAllInstructors() {
        return WebMvcLinkBuilder.linkTo(WebMvcLinkBuilder.methodOn(InstructorController.class).all());
    }

    public Instructor fromDTO(InstructorDTO dto) {
        Instructor instructor = new Instructor();
        instructor.setIdIntructor(dto.getIdIntructor());
        instructor.setNombreIn(dto.getNombreIn());
        instructor.setApellidoIn(dto.getApellidoIn());
        instructor.setEmailIn(dto.getEmailIn());
        instructor.setContraseñaIn(dto.getContraseñaIn());
        instructor.setTelefonoIn(dto.getTelefonoIn());
        instructor.setFechaRegistro(dto.getFechaRegistro());
        instructor.setEspecialidad(dto.getEspecialidad());
        instructor.setCursosImpartidos(dto.getCursosImpartidos());
        instructor.setNomCursosImpartidos(dto.getNomCursosImpartidos());
        instructor.setCertificado(dto.getCertificado());
        return instructor;
    }
}
