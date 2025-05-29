package com.proyecto.Edutech_v1.proyecto.Edutech_v1.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Curso;

@Repository // Anotación para indicar que esta interfaz es un repositorio
public interface CursoRepository extends CrudRepository<Curso, Long> {


}
