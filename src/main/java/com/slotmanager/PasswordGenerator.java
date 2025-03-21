package com.slotmanager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordGenerator {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String rawPassword = "operador123"; // Cambia la contraseña aquí si es necesario
        String encodedPassword = encoder.encode(rawPassword);
        System.out.println("Nueva contraseña encriptada: " + encodedPassword);
    }
}

