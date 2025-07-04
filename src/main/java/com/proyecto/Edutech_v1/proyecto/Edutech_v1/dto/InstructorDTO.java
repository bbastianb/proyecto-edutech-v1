package com.proyecto.Edutech_v1.proyecto.Edutech_v1.dto;

import java.time.LocalDate;
import org.springframework.hateoas.RepresentationModel;

public class InstructorDTO extends RepresentationModel<InstructorDTO> {

    private Long idIntructor;
    private String nombreIn;
    private String apellidoIn;
    private String emailIn;
    private String telefonoIn;
    private LocalDate fechaRegistro;
    private String especialidad;
    private Integer cursosImpartidos;
    private String nomCursosImpartidos;
    private Boolean certificado;
    private String contraseñaIn;

    // Getters y Setters
    public Long getIdIntructor() {
        return idIntructor;
    }

    public void setIdIntructor(Long idIntructor) {
        this.idIntructor = idIntructor;
    }

    public String getNombreIn() {
        return nombreIn;
    }

    public void setNombreIn(String nombreIn) {
        this.nombreIn = nombreIn;
    }

    public String getApellidoIn() {
        return apellidoIn;
    }

    public void setApellidoIn(String apellidoIn) {
        this.apellidoIn = apellidoIn;
    }

    public String getEmailIn() {
        return emailIn;
    }

    public void setEmailIn(String emailIn) {
        this.emailIn = emailIn;
    }

    public String getTelefonoIn() {
        return telefonoIn;
    }

    public void setTelefonoIn(String telefonoIn) {
        this.telefonoIn = telefonoIn;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public Integer getCursosImpartidos() {
        return cursosImpartidos;
    }

    public void setCursosImpartidos(Integer cursosImpartidos) {
        this.cursosImpartidos = cursosImpartidos;
    }

    public String getNomCursosImpartidos() {
        return nomCursosImpartidos;
    }

    public void setNomCursosImpartidos(String nomCursosImpartidos) {
        this.nomCursosImpartidos = nomCursosImpartidos;
    }

    public Boolean getCertificado() {
        return certificado;
    }

    public void setCertificado(Boolean certificado) {
        this.certificado = certificado;
    }

    public String getContraseñaIn() {
        return contraseñaIn;
    }

    public void setContraseñaIn(String contraseñaIn) {
        this.contraseñaIn = contraseñaIn;
    }
}
