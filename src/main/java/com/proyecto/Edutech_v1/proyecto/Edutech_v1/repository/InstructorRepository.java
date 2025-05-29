package com.proyecto.Edutech_v1.proyecto.Edutech_v1.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Instructor;

    @Repository
public interface InstructorRepository extends JpaRepository<Instructor, Long> {
}
