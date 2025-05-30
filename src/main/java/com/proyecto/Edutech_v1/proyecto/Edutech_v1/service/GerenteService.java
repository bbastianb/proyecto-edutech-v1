package com.proyecto.Edutech_v1.proyecto.Edutech_v1.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Gerente;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.repository.GerenteRepository;

@Service
public class GerenteService {

    @Autowired
    private GerenteRepository gerenteRepository;

    public Gerente guardarGerente(Gerente gerente) {
        return gerenteRepository.save(gerente);
    }

    public void eliminarGerente(Long id) {
        gerenteRepository.deleteById(id);
    }

    public List<Gerente> obtenerGerentesConCursos() {
        return gerenteRepository.findAll();
    }
}
