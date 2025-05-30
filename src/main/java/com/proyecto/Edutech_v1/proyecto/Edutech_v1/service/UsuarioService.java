package com.proyecto.Edutech_v1.proyecto.Edutech_v1.service;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.controller.UsuarioController;
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

    public UsuarioController.SoporteResponse solicitarSoporteConRespuesta(Long id, String titulo, String mensaje) {
        Usuario usuario = usuarioRepository.findById(id).orElse(null);
        if (usuario == null)
            throw new IllegalArgumentException("Usuario no encontrado");
        if (usuario.getEmail() == null || usuario.getEmail().trim().isEmpty())
            throw new IllegalStateException("Usuario no validado, no puede solicitar soporte");
        if (mensaje == null || mensaje.trim().isEmpty())
            throw new IllegalArgumentException("El mensaje de soporte no puede estar vacío");
        if (titulo == null || titulo.trim().isEmpty())
            throw new IllegalArgumentException("El título no puede estar vacío");

        String incidenciaId = "INC-" + System.currentTimeMillis() + "-" + usuario.getId();
        String fechaEnvio = new java.text.SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new java.util.Date());

        return new UsuarioController.SoporteResponse(
            titulo,
            mensaje,
            fechaEnvio,
            incidenciaId
        );
    }

    public Usuario registrarUsuario(Usuario usuario) {
        return usuarioRepository.save(usuario);
    }

    public Usuario findById(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Usuario no encontrado con ID: " + id));
    }
}