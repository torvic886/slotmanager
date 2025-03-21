package com.slotmanager.controller;

import com.slotmanager.dto.RecaudoResponse;
import com.slotmanager.entity.Recaudo;
import com.slotmanager.service.RecaudoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/recaudos")
public class RecaudoController {

    @Autowired
    private RecaudoService recaudoService;

    /**
     * Registrar un nuevo recaudo.
     * Disponible para ADMIN y OPERADOR.
     */
    @PostMapping
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_OPERADOR')")
    public ResponseEntity<RecaudoResponse> registrarRecaudo(@RequestBody Recaudo recaudo) {
        Recaudo nuevoRecaudo = recaudoService.registrarRecaudo(recaudo);
        return ResponseEntity.ok(convertirARecaudoResponse(nuevoRecaudo));
    }

    /**
     * Obtener todos los recaudos de una máquina específica.
     * Disponible para ADMIN y OPERADOR.
     */
    @GetMapping("/{maquinaId}")
    @PreAuthorize("hasAnyAuthority('ROLE_ADMIN', 'ROLE_OPERADOR')")
    public ResponseEntity<List<RecaudoResponse>> obtenerRecaudos(@PathVariable Long maquinaId) {
        List<Recaudo> recaudos = recaudoService.obtenerRecaudosPorMaquina(maquinaId);
        List<RecaudoResponse> response = recaudos.stream()
                .map(this::convertirARecaudoResponse)
                .collect(Collectors.toList());
        return ResponseEntity.ok(response);
    }

    /**
     * Método para convertir una entidad Recaudo en un DTO RecaudoResponse.
     */
    private RecaudoResponse convertirARecaudoResponse(Recaudo recaudo) {
        RecaudoResponse dto = new RecaudoResponse();
        dto.setId(recaudo.getId());
        dto.setMaquinaId(recaudo.getMaquina().getId());
        dto.setMaquinaNombre(recaudo.getMaquina().getNombre());  // 🔥 Ahora sí carga el nombre
        dto.setEstado(recaudo.getMaquina().getEstado());  // 🔥 Ahora sí carga el estado
        dto.setFecha(recaudo.getFecha());
        dto.setContadorIn(recaudo.getContadorIn());
        dto.setContadorOut(recaudo.getContadorOut());
        return dto;
    }

}
