package com.proyecto.Edutech_v1.proyecto.Edutech_v1.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Usuario;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.repository.UsuarioRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class UsuarioService {
    @Autowired
    private UsuarioRepository usuarioRepository;

    public String iniciarSesion(String email, String contraseña) {
        Usuario usuario = usuarioRepository.findByEmail(email);
        if (usuario == null) {
            return "Inicio de sesión fallido: usuario no encontrado";
        }
        if (!usuario.getContraseña().equals(contraseña)) {
            return "Inicio de sesión fallido: contraseña incorrecta";
        }
        usuario.setUltimoAcceso(new Date());
        usuarioRepository.save(usuario);
        return "Inicio de sesión exitoso";
    }

    public Usuario actualizarPerfil(Long id, String nombre, String apellido, String telefono, String email,
            String contraseña) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario != null) {
            if (nombre != null)
                usuario.setNombre(nombre);
            if (apellido != null)
                usuario.setApellido(apellido);
            if (telefono != null)
                usuario.setTelefono(telefono);
            if (email != null)
                usuario.setEmail(email);
            if (contraseña != null)
                usuario.setContraseña(contraseña);
            return usuarioRepository.save(usuario);
        }
        return null;
    }

    public String solicitarSoporte(Long id, String mensaje) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null)
            throw new IllegalArgumentException("Usuario no encontrado");
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty())
            throw new IllegalStateException("Usuario no validado, no puede solicitar soporte");
        if (!Boolean.TRUE.equals(usuario.getActivo()))
            throw new IllegalStateException("Usuario inactivo, no puede solicitar soporte");
        if (mensaje == null || mensaje.trim().isEmpty())
            throw new IllegalArgumentException("El mensaje de soporte no puede estar vacío");

        String incidenciaId = "INC-" + System.currentTimeMillis() + "-" + usuario.getId();
        // Aquí podrías guardar la incidencia en la base de datos si lo deseas
        return incidenciaId;
    }
}