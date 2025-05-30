package com.proyecto.Edutech_v1.proyecto.Edutech_v1.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Estudiante;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Instructor;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.model.Usuario;
import com.proyecto.Edutech_v1.proyecto.Edutech_v1.service.UsuarioService;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService usuarioService;

    // Iniciar sesión
    @PostMapping("/login")
    public ResponseEntity<String> iniciarSesion(@RequestBody LoginRequest loginRequest) {
        String mensaje = usuarioService.iniciarSesion(loginRequest.getEmail(), loginRequest.getContraseña());
        return ResponseEntity.ok(mensaje);
    }

    public static class LoginRequest {

        private String email;
        private String contraseña;

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getContraseña() {
            return contraseña;
        }

        public void setContraseña(String contraseña) {
            this.contraseña = contraseña;
        }
    }

    // Actualizar perfil
    @PutMapping("/{id}/perfil")
    public ResponseEntity<?> actualizarPerfil(
            @PathVariable Long id,
            @RequestBody PerfilRequest perfilRequest) {
        Usuario actualizado = usuarioService.actualizarPerfil(
                id,
                perfilRequest.getNombre(),
                perfilRequest.getApellido(),
                perfilRequest.getTelefono(),
                perfilRequest.getEmail(),
                perfilRequest.getContraseña());
        if (actualizado == null) {
            return ResponseEntity.notFound().build();
        }
        Map<String, Object> response = new HashMap<>();
        response.put("id", actualizado.getId());
        response.put("nombre", actualizado.getNombre());
        response.put("apellido", actualizado.getApellido());
        response.put("email", actualizado.getEmail());
        response.put("telefono", actualizado.getTelefono());
        return ResponseEntity.ok(response);
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Data
    public static class PerfilRequest {

        private String nombre;
        private String apellido;
        private String telefono;
        private String contraseña;
        private String email;
    }

    // Solicitar soporte
    @PostMapping("/{id}/soporte")
    public ResponseEntity<?> solicitarSoporte(@PathVariable Long id, @RequestBody SoporteRequest request) {
        try {
            SoporteResponse respuesta = usuarioService.solicitarSoporteConRespuesta(
                    id,
                    request.getTitulo(),
                    request.getMensaje());
            return ResponseEntity.ok(respuesta);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @Data
    public static class SoporteRequest {
        private String titulo;
        private String mensaje;
    }

    @Data
    @AllArgsConstructor
    public static class SoporteResponse {
        private String titulo;
        private String mensaje;
        private String fechaEnvio;
        private String numeroIncidencia;
    }

    @PostMapping("/registro")
    public ResponseEntity<?> registrarUsuario(@RequestBody RegistroRequest request) {
        if (request.getEmail() == null || request.getContraseña() == null) {
            return ResponseEntity.badRequest().body("Email y contraseña son obligatorios.");
        }

        Usuario usuario;
        if ("estudiante".equalsIgnoreCase(request.getTipo())) {
            usuario = new Estudiante();
        } else if ("instructor".equalsIgnoreCase(request.getTipo())) {
            usuario = new Instructor();
        } else {
            return ResponseEntity.badRequest().body("Tipo de usuario inexistente.");
        }

        usuario.setNombre(request.getNombre());
        usuario.setApellido(request.getApellido());
        usuario.setEmail(request.getEmail());
        usuario.setContraseña(request.getContraseña());
        usuario.setTelefono(request.getTelefono());
        usuario.setFechaRegistro(new java.util.Date());
        usuario.setActivo(true);

        Usuario guardado = usuarioService.registrarUsuario(usuario);
        return ResponseEntity.ok(guardado);
    }

    @Data
    @NoArgsConstructor
    @AllArgsConstructor
    public static class RegistroRequest {
        private String nombre;
        private String apellido;
        private String email;
        private String contraseña;
        private String telefono;
        private String tipo; // "estudiante" o "instructor"
    }
}
