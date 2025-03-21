package com.slotmanager;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import java.nio.charset.StandardCharsets;

public class TestJWT {
    public static void main(String[] args) {
        String token = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJhZG1pbkBjYXNpbm8uY29tIiwicm9sZSI6IkFETUlOIiwiaWF0IjoxNzQyMzEyMjE0LCJleHAiOjE3NDIzOTg2MTR9.ygOXX9RHsLs0-HUDVfuqb8Ch_EALQZdsIHzaxY7oP-M"; // Copia el token de Postman aquí

        String secretKey = "eYiKTzNPsH8SSY4StsJ+1peYeTmij27Sz2TnYjNOIis="; // Usa la misma clave de tu application.properties

        Claims claims = Jwts.parser()
                .verifyWith(Keys.hmacShaKeyFor(secretKey.getBytes(StandardCharsets.UTF_8)))
                .build()
                .parseSignedClaims(token)
                .getPayload();

        System.out.println("Email: " + claims.getSubject());
        System.out.println("Rol: " + claims.get("role"));
    }
}

