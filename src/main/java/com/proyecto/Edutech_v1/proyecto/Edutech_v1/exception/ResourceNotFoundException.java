package com.proyecto.Edutech_v1.proyecto.Edutech_v1.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String mensaje) {
        super(mensaje);
    }
}
