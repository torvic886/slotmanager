package com.slotmanager.controller;

import com.slotmanager.entity.Maquina;
import com.slotmanager.service.MaquinaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import org.springframework.security.access.prepost.PreAuthorize;

@RestController
@RequestMapping("/api/maquinas")
//@CrossOrigin(origins = "*")
@CrossOrigin(origins = "http://localhost:4200")
public class MaquinaController {

    @Autowired
    private MaquinaService service;

    @GetMapping
    // Permitir a admin y operador ver la lista
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_OPERADOR')")
    public List<Maquina> obtenerMaquinas() {
        return service.listarMaquinas();
    }

    @PostMapping
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // Solo admin puede crear
    public Maquina crearMaquina(@RequestBody Maquina maquina) {
        return service.guardarMaquina(maquina);
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // Solo admin puede actualizar
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
    @PreAuthorize("hasAuthority('ROLE_ADMIN')") // Solo admin puede eliminar
    public void eliminarMaquina(@PathVariable Long id) {
        service.eliminarMaquina(id);
    }
}