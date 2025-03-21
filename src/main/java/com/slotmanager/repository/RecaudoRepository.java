package com.slotmanager.repository;

import com.slotmanager.entity.Recaudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Date;

@Repository
public interface RecaudoRepository extends JpaRepository<Recaudo, Long> {

    // Filtrar recaudos por fecha (opcional)
    List<Recaudo> findByFechaBetween(Date fechaInicio, Date fechaFin);

    // Filtrar recaudos por máquina
    List<Recaudo> findByMaquinaId(Long maquinaId);
}
