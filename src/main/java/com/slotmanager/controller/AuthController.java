package com.slotmanager.controller;

import com.slotmanager.entity.Usuario;
import com.slotmanager.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private AuthService authService;

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> credentials) {
        String token = authService.login(credentials.get("email"), credentials.get("password"));
        return Map.of("token", token);
    }

    @PostMapping("/register")
    public Usuario register(@RequestBody Usuario usuario) {
        return authService.register(usuario);
    }
}

