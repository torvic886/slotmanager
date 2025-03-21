package com.slotmanager.controller;

import com.slotmanager.entity.Maquina;
import com.slotmanager.service.MaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/maquinas")
@CrossOrigin(origins = "*")
public class MaquinaController {

    @Autowired
    private MaquinaService service;

    @GetMapping
    public List<Maquina> obtenerMaquinas() {
        return service.listarMaquinas();
    }

    @PostMapping
    public Maquina crearMaquina(@RequestBody Maquina maquina) {
        return service.guardarMaquina(maquina);
    }

    @PutMapping("/{id}")
    public Maquina actualizarMaquina(@PathVariable Long id, @RequestBody Maquina maquinaActualizada) {
        Optional<Maquina> maquinaExistente = service.obtenerMaquinaPorId(id);
        
        if (maquinaExistente.isPresent()) {
            Maquina maquina = maquinaExistente.get();
            maquina.setNombre(maquinaActualizada.getNombre());
            maquina.setFechaInstalacion(maquinaActualizada.getFechaInstalacion());
            maquina.setEstado(maquinaActualizada.getEstado());
            return service.guardarMaquina(maquina);
        } else {
            throw new RuntimeException("Máquina no encontrada con ID: " + id);
        }
    }

    @DeleteMapping("/{id}")
    public void eliminarMaquina(@PathVariable Long id) {
        service.eliminarMaquina(id);
    }
}
