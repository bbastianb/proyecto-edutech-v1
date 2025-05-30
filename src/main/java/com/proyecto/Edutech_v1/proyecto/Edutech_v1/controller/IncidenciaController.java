package com.proyecto.Edutech_v1.proyecto.Edutech_v1.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Incidencia;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.service.IncidenciaService;

@RestController
@RequestMapping("/api/incidencias")
public class IncidenciaController {

    @Autowired
    private IncidenciaService incidenciaService;

    @PostMapping
    public ResponseEntity<Incidencia> crearIncidencia(
            @RequestBody Incidencia incidencia,
            @RequestParam Long usuarioId) {
        Incidencia nuevaIncidencia = incidenciaService.crearIncidencia(incidencia, usuarioId);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevaIncidencia);
    }

    @PutMapping("/{id}/asignar")
    public ResponseEntity<Incidencia> asignarIncidencia(
            @PathVariable Long id,
            @RequestParam Long soporteId) {
        Incidencia incidencia = incidenciaService.asignarIncidencia(id, soporteId);
        return ResponseEntity.ok(incidencia);
    }

    @PutMapping("/{id}/estado")
    public ResponseEntity<Incidencia> actualizarEstado(
            @PathVariable Long id,
            @RequestParam String estado,
            @RequestParam(required = false) String comentarios) {
        Incidencia incidencia = incidenciaService.actualizarEstado(id, estado, comentarios != null ? comentarios : "");
        return ResponseEntity.ok(incidencia);
    }

    @GetMapping
    public ResponseEntity<List<Incidencia>> listarTodas() {
        List<Incidencia> incidencias = incidenciaService.listarTodas();
        return ResponseEntity.ok(incidencias);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Incidencia> obtenerPorId(@PathVariable Long id) {
        Incidencia incidencia = incidenciaService.obtenerIncidenciaPorId(id);
        return ResponseEntity.ok(incidencia);
    }

    @GetMapping("/estado/{estado}")
    public ResponseEntity<List<Incidencia>> obtenerPorEstado(@PathVariable String estado) {
        List<Incidencia> incidencias = incidenciaService.obtenerIncidenciasPorEstado(estado);
        return ResponseEntity.ok(incidencias);
    }

    @GetMapping("/soporte/{soporteId}")
    public ResponseEntity<List<Incidencia>> obtenerPorSoporte(@PathVariable Long soporteId) {
        List<Incidencia> incidencias = incidenciaService.obtenerIncidenciasPorSoporte(soporteId);
        return ResponseEntity.ok(incidencias);
    }

    @GetMapping("/estadisticas")
    public ResponseEntity<List<Object[]>> obtenerEstadisticas() {
        List<Object[]> estadisticas = incidenciaService.obtenerEstadisticasPorEstado();
        return ResponseEntity.ok(estadisticas);
    }

    @GetMapping("/resueltas")
    public ResponseEntity<List<Incidencia>> obtenerResueltasEnPeriodo(
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date inicio,
            @RequestParam @DateTimeFormat(pattern = "yyyy-MM-dd") Date fin) {
        List<Incidencia> incidencias = incidenciaService.obtenerIncidenciasResueltasEnPeriodo(inicio, fin);
        return ResponseEntity.ok(incidencias);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarIncidencia(@PathVariable Long id) {
        incidenciaService.eliminarIncidencia(id);
        return ResponseEntity.noContent().build();
    }
}
