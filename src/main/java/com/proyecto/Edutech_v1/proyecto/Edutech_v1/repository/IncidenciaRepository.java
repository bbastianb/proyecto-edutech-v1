package com.proyecto.Edutech_v1.proyecto.Edutech_v1.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Incidencia;

@Repository
public interface IncidenciaRepository extends JpaRepository<Incidencia, Long> {

    // Buscar incidencias por estado
    List<Incidencia> findByEstado(String estado);
    
    // Buscar incidencias por prioridad
    List<Incidencia> findByPrioridad(String prioridad);
    
    // Buscar incidencias asignadas a un soporte específico
    @Query("SELECT i FROM Incidencia i WHERE i.asignadoA.id = :soporteId")
    List<Incidencia> findBySoporteAsignado(Long soporteId);
    
    // Buscar incidencias reportadas por un usuario específico
    @Query("SELECT i FROM Incidencia i WHERE i.reportadoPor.id = :usuarioId")
    List<Incidencia> findByUsuarioReportador(Long usuarioId);
    
    // Contar incidencias por estado
    @Query("SELECT i.estado, COUNT(i) FROM Incidencia i GROUP BY i.estado")
    List<Object[]> countByEstado();
    
    // Buscar incidencias resueltas en un período
    List<Incidencia> findByEstadoAndFechaResolucionBetween(String estado, Date inicio, Date finin);
}
