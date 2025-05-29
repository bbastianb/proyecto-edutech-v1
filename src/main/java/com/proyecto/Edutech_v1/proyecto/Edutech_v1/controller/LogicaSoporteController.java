package com.proyecto.Edutech_v1.proyecto.Edutech_v1.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.LogicaSoporte;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.service.LogicaSoporteService;


@RestController
@RequestMapping("/api/logica-soporte")
public class LogicaSoporteController {



    @Autowired
    private LogicaSoporteService logicaSoporteService;

    @GetMapping
    public ResponseEntity<List<LogicaSoporte>> listarTodos() {
        List<LogicaSoporte> soportes = logicaSoporteService.listarTodos();
        return ResponseEntity.ok(soportes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LogicaSoporte> obtenerPorId(@PathVariable Long id) {
        try {
            LogicaSoporte soporte = logicaSoporteService.obtenerPorId(id);
            return ResponseEntity.ok(soporte);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<LogicaSoporte> crear(@RequestBody LogicaSoporte soporte) {
        LogicaSoporte nuevoSoporte = logicaSoporteService.guardar(soporte);
        return ResponseEntity.status(HttpStatus.CREATED).body(nuevoSoporte);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LogicaSoporte> actualizar(
            @PathVariable Long id, 
            @RequestBody LogicaSoporte soporte) {
        try {
            LogicaSoporte existente = logicaSoporteService.obtenerPorId(id);
            
            // Actualizar campos
            existente.setTurno(soporte.getTurno());
            existente.setIncidentesResueltos(soporte.getIncidentesResueltos());
            existente.setHerramientasSoporte(soporte.getHerramientasSoporte());
            
            LogicaSoporte actualizado = logicaSoporteService.guardar(existente);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable Long id) {
        try {
            logicaSoporteService.eliminar(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/turno/{turno}")
    public ResponseEntity<List<LogicaSoporte>> buscarPorTurno(@PathVariable String turno) {
        List<LogicaSoporte> soportes = logicaSoporteService.buscarPorTurno(turno);
        return ResponseEntity.ok(soportes);
    }

    @GetMapping("/incidentes/{minIncidentes}")
    public ResponseEntity<List<LogicaSoporte>> buscarPorMinIncidentes(
            @PathVariable Integer minIncidentes) {
        List<LogicaSoporte> soportes = logicaSoporteService.buscarPorMinIncidentes(minIncidentes);
        return ResponseEntity.ok(soportes);
    }

    @PatchMapping("/{id}/incidentes")
    public ResponseEntity<LogicaSoporte> actualizarIncidentes(
            @PathVariable Long id,
            @RequestParam Integer nuevosIncidentes) {
        try {
            LogicaSoporte actualizado = logicaSoporteService
                    .actualizarIncidentes(id, nuevosIncidentes);
            return ResponseEntity.ok(actualizado);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

}
