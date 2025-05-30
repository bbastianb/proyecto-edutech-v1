package com.proyecto.Edutech_v1.proyecto.Edutech_v1.model;



import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "gerente") // Anotación de JPA para especificar el nombre de la tabla en la base de datos
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Gerente { //extiende Usuario
    // Clase que representa a un gerente en el sistema, extendiendo la clase Usuario

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_gerente")
    private Long idGerente;

    @Column(name = "area_especialidad", nullable = false)
    private String especialidad;

    @Column(name = "cursos_gestionados")
    private Double cursosGestionados;

    @OneToMany(mappedBy = "gerente")
    private List<Curso> cursos = new ArrayList<>();
   }
