package com.slotmanager.service;

import com.slotmanager.entity.Usuario;
import com.slotmanager.repository.UsuarioRepository;
import com.slotmanager.security.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;


@Service
public class AuthService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private JwtUtil jwtUtil;

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public String login(String email, String password) {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByEmail(email);
        
        if (usuarioOpt.isPresent()) {
            Usuario usuario = usuarioOpt.get();
            
            // Comparar contraseñas con BCrypt
            if (passwordEncoder.matches(password, usuario.getPassword())) {
                return jwtUtil.generateToken(email, usuario.getRole().name());
            }
        }
        
         throw new BadCredentialsException("Credenciales incorrectas");
//        throw new RuntimeException("Credenciales incorrectas");
    }

    public Usuario register(Usuario usuario) {
        // Encriptar contraseña antes de guardarla en la base de datos
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuarioRepository.save(usuario);
    }
    
    
}
