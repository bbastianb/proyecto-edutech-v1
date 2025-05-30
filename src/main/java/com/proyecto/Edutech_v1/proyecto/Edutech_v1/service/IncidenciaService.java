package com.proyecto.Edutech_v1.proyecto.Edutech_v1.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Incidencia;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.LogicaSoporte;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Usuario;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.repository.IncidenciaRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class IncidenciaService {

    @Autowired
    private IncidenciaRepository incidenciaRepository;
    
    @Autowired
    private LogicaSoporteService soporteService;
    
    @Autowired
    private UsuarioService usuarioService;

    public Incidencia crearIncidencia(Incidencia incidencia, Long usuarioId) {
        Usuario reportador = usuarioService.findById(usuarioId);
        incidencia.setReportadoPor(reportador);
        incidencia.setFechaReporte(new Date());
        incidencia.setEstado("NUEVO");
        return incidenciaRepository.save(incidencia);
    }

    public Incidencia asignarIncidencia(Long incidenciaId, Long soporteId) {
        Incidencia incidencia = obtenerIncidenciaPorId(incidenciaId);
        LogicaSoporte soporte = soporteService.obtenerPorId(soporteId);
        
        incidencia.setAsignadoA(soporte);
        incidencia.setEstado("ASIGNADA");
        return incidenciaRepository.save(incidencia);
    }

    public Incidencia actualizarEstado(Long id, String nuevoEstado, String comentarios) {
        Incidencia incidencia = obtenerIncidenciaPorId(id);
        incidencia.setEstado(nuevoEstado);
        
        if ("RESUELTA".equals(nuevoEstado)) {
            incidencia.setFechaResolucion(new Date());
            if (incidencia.getAsignadoA() != null) {
                soporteService.actualizarIncidentes(incidencia.getAsignadoA().getId(), 
                    incidencia.getAsignadoA().getIncidentesResueltos() + 1);
            }
        }
        
        // Registrar histórico
        String historico = String.format("[%s] Estado cambiado a %s - %s", 
            new Date(), nuevoEstado, comentarios);
        incidencia.setDescripcion(incidencia.getDescripcion() + "\n\n" + historico);
        
        return incidenciaRepository.save(incidencia);
    }

    public List<Incidencia> obtenerIncidenciasPorEstado(String estado) {
        return incidenciaRepository.findByEstado(estado);
    }

    public List<Incidencia> obtenerIncidenciasPorSoporte(Long soporteId) {
        return incidenciaRepository.findBySoporteAsignado(soporteId);
    }

    public Incidencia obtenerIncidenciaPorId(Long id) {
        return incidenciaRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("Incidencia no encontrada con ID: " + id));
    }

    public List<Incidencia> listarTodas() {
        return incidenciaRepository.findAll();
    }

    public void eliminarIncidencia(Long id) {
        incidenciaRepository.deleteById(id);
    }

    public List<Incidencia> obtenerIncidenciasResueltasEnPeriodo(Date inicio, Date fin) {
        return incidenciaRepository.findByEstadoAndFechaResolucionBetween("RESUELTA", inicio, fin);
    }

    public List<Object[]> obtenerEstadisticasPorEstado() {
        return incidenciaRepository.countByEstado();
    }
}