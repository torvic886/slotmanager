package com.slotmanager;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class TestBCrypt {
    public static void main(String[] args) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        // Hash almacenado en la base de datos
        String storedHash = "$2a$10$aPRp32OmXnN5Homzuao/1Ot1n85T83YBXYss56VWKfUEbI5ikAGmG";
        
        // Contraseña que estás enviando en Postman
        String rawPassword = "operador123";

        // Verificar si la contraseña en texto plano coincide con la encriptada
        boolean matches = encoder.matches(rawPassword, storedHash);

        if (matches) {
            System.out.println("✅ La contraseña es válida.");
        } else {
            System.out.println("❌ La contraseña NO coincide.");
        }
    }
}

