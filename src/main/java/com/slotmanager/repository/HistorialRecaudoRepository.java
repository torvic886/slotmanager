package com.slotmanager.repository;

import com.slotmanager.entity.HistorialRecaudo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface HistorialRecaudoRepository extends JpaRepository<HistorialRecaudo, Long> {
    List<HistorialRecaudo> findByMaquinaId(Long maquinaId);
}

