package com.proyecto.Edutech_v1.proyecto.Edutech_v1.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity // Anotación de JPA para indicar que esta clase es una entidad que se mapeará a una tabla en la base de datos
@Table(name = "estudiante") // Anotación de JPA para especificar el nombre de la tabla en la base de datos
@Data // Anotación de Lombok para generar automáticamente los métodos getter, setter, toString, equals y hashCode
@NoArgsConstructor // Anotación de Lombok para generar un constructor sin parámetros
@AllArgsConstructor// Anotación de Lombok para generar un constructor con todos los parámetros

public class Estudiante {

    // Atributos de la clase Estudiante
    //Elimina el id y extiende el Usuario
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)// Generación automática del ID de estudiante
    private Long idEstudiante;

    @Column(name = "metodo_pago", nullable = false)
    private String metodoPago;

    @Column(name = "cursos_inscrito", nullable = false)
    private int cursoIncrito;

    @Column(name = "ultima_inscripcion", nullable = true)
    private String ultimaInscripcion;

    @Column(name = "saldo_disponible", nullable = true)
    private double saldoDisponible;

    @ManyToOne
    @JoinColumn(name = "codigo_curso") // Relación muchos a uno con la entidad Curso
    private Curso curso;

    //@ManyToOne // Relación muchos a uno con la entidad Usuario
    //@JoinColumn(name = "id_usuario", nullable = false)
    //private Usuario usuario; //Relación con la entidad Usuario, asumiendo que existe una clase Usuario
}
