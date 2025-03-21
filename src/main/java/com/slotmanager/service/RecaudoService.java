package com.slotmanager.service;

import com.slotmanager.entity.HistorialRecaudo;
import com.slotmanager.entity.Maquina;
import com.slotmanager.entity.Recaudo;
import com.slotmanager.repository.HistorialRecaudoRepository;
import com.slotmanager.repository.MaquinaRepository;
import com.slotmanager.repository.RecaudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
//import java.util.Date;
import java.util.List;

@Service
public class RecaudoService {

    @Autowired
    private RecaudoRepository recaudoRepository;

    @Autowired
    private HistorialRecaudoRepository historialRecaudoRepository;

    @Autowired
    private MaquinaRepository maquinaRepository;

    @Transactional
    public Recaudo registrarRecaudo(Recaudo recaudo) {
        Maquina maquina = maquinaRepository.findById(recaudo.getMaquina().getId())
                .orElseThrow(() -> new RuntimeException("La máquina no existe"));

        recaudo.setMaquina(maquina);  // 🔥 Asegura que el objeto máquina esté completamente cargado
        return recaudoRepository.save(recaudo);
    }


    private void guardarHistorial(Maquina maquina, List<Recaudo> recaudos) {
        Double totalRecaudado = recaudos.stream().mapToDouble(r -> (r.getContadorIn() - r.getContadorOut()) * 10).sum();
        Double gananciaSocio = (totalRecaudado - 35000) / 2;

        HistorialRecaudo historial = new HistorialRecaudo();
        historial.setMaquina(maquina);
        historial.setFechaInicio(recaudos.get(0).getFecha());  // Fecha del primer recaudo
        historial.setFechaFin(recaudos.get(recaudos.size() - 1).getFecha());  // Fecha del último recaudo
        historial.setTotalRecaudado(totalRecaudado);
        historial.setGananciaSocio1(gananciaSocio);
        historial.setGananciaSocio2(gananciaSocio);

        historialRecaudoRepository.save(historial);
    }

    public List<Recaudo> obtenerRecaudosPorMaquina(Long maquinaId) {
        return recaudoRepository.findByMaquinaId(maquinaId);
    }
}
