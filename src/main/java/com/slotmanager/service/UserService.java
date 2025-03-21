package com.slotmanager.service;

import com.slotmanager.dto.UserResponse;
import com.slotmanager.entity.Usuario;
import com.slotmanager.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<UserResponse> getAllUsers() {
        List<Usuario> usuarios = usuarioRepository.findAll();
        return usuarios.stream().map(usuario -> {
            UserResponse userResponse = new UserResponse();
            userResponse.setId(usuario.getId());
            userResponse.setNombre(usuario.getNombre());
            userResponse.setEmail(usuario.getEmail());
            userResponse.setRole(usuario.getRole().name());
            return userResponse;
        }).collect(Collectors.toList());
    }
}
