package com.slotmanager.service;

import com.slotmanager.entity.Maquina;
import com.slotmanager.repository.MaquinaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaquinaService {
    @Autowired
    private MaquinaRepository repository;

    public List<Maquina> listarMaquinas() {
        return repository.findAll();
    }

    public Maquina guardarMaquina(Maquina maquina) {
        return repository.save(maquina);
    }

    public Optional<Maquina> obtenerMaquinaPorId(Long id) {
        return repository.findById(id);
    }

    public void eliminarMaquina(Long id) {
        repository.deleteById(id);
    }
}
