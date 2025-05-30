package com.proyecto.Edutech_v1.proyecto.Edutech_v1.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Gerente;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.service.GerenteService;

@RestController
@RequestMapping("/api/gerentes")
public class GerenteController {

    @Autowired
    private GerenteService gerenteService;

    @PostMapping("/agregar")
    public Gerente agregarGerente(@RequestBody Gerente gerente) {
        return gerenteService.guardarGerente(gerente);
    }

    @DeleteMapping("/eliminar/{id}")
    public void eliminarGerente(@PathVariable Long id) {
        gerenteService.eliminarGerente(id);
    }

    @GetMapping("/listar")
    public List<Gerente> listarGerentes() {
        return gerenteService.obtenerGerentesConCursos();
    }

}
