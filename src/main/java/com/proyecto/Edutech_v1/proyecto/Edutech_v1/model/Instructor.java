package com.proyecto.Edutech_v1.proyecto.Edutech_v1.model;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.*;

@Entity
@Table(name = "instructor")
public class Instructor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_instructor")
    private Long idIntructor;

    @Column(name = "nombre_instructor", nullable = false, length = 100)
    private String nombreIn;

    @Column(name = "apellido_instructor", nullable = false, length = 100)
    private String apellidoIn;

    @Column(name = "email_instructor", nullable = false, unique = true)
    private String emailIn;

    @Column(name = "contraseña_instructor", nullable = false, length = 100)
    private String contraseñaIn;

    @Column(name = "telefono_instructor", length = 20)
    private String telefonoIn;

    @Column(name = "fecha_registro_ins", nullable = false)
    private LocalDate fechaRegistro;

    @Column(name = "especialidad", nullable = false)
    private String especialidad;

    @Column(name = "cursos_impartidos")
    private Integer cursosImpartidos;

    @Column(name = "nombres_cursos_impartidos")
    private String nomCursosImpartidos;

    @Column(name = "certificado")
    private Boolean certificado;

    @OneToMany(mappedBy = "instructor")
    @JsonManagedReference("instructor-curso")
    private List<Curso> cursos = new ArrayList<>();

    // ==== CONSTRUCTOR COMPLETO ====
    public Instructor(Long idIntructor, String nombreIn, String apellidoIn, String emailIn, String contraseñaIn,
            String telefonoIn, LocalDate fechaRegistro, String especialidad, Integer cursosImpartidos,
            String nomCursosImpartidos, Boolean certificado, List<Curso> cursos) {
        this.idIntructor = idIntructor;
        this.nombreIn = nombreIn;
        this.apellidoIn = apellidoIn;
        this.emailIn = emailIn;
        this.contraseñaIn = contraseñaIn;
        this.telefonoIn = telefonoIn;
        this.fechaRegistro = fechaRegistro;
        this.especialidad = especialidad;
        this.cursosImpartidos = cursosImpartidos;
        this.nomCursosImpartidos = nomCursosImpartidos;
        this.certificado = certificado;
        this.cursos = cursos;
    }

    // ==== CONSTRUCTOR VACÍO ====
    public Instructor() {
    }

    // ==== GETTERS & SETTERS ====
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

    public String getContraseñaIn() {
        return contraseñaIn;
    }

    public void setContraseñaIn(String contraseñaIn) {
        this.contraseñaIn = contraseñaIn;
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

    public List<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(List<Curso> cursos) {
        this.cursos = cursos;
    }
}
