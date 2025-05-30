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
    public ResponseEntity<String> solicitarSoporte(@PathVariable Long id, @RequestParam String mensaje) {
        try {
            String incidenciaId = usuarioService.solicitarSoporte(id, mensaje);
            return ResponseEntity.ok(incidenciaId);
        } catch (IllegalArgumentException | IllegalStateException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
