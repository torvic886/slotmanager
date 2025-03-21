package com.slotmanager.controller;

import com.slotmanager.service.ReporteService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/api/reporte")
public class ReporteController {

    private final ReporteService reporteService;

    public ReporteController(ReporteService reporteService) {
        this.reporteService = reporteService;
    }

    @GetMapping("/pdf")
    public ResponseEntity<byte[]> generarReportePDF(
            @RequestParam(required = false) String fechaInicio,
            @RequestParam(required = false) String fechaFin,
            @RequestParam(required = false) Long maquinaId
    ) {
        try {
            SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            Date inicio = fechaInicio != null ? dateFormat.parse(fechaInicio) : null;
            Date fin = fechaFin != null ? dateFormat.parse(fechaFin) : null;

            byte[] reportePDF = reporteService.generarReportePDF(inicio, fin, maquinaId);

            return ResponseEntity.ok()
                    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=reporte.pdf")
                    .contentType(MediaType.APPLICATION_PDF)
                    .body(reportePDF);

        } catch (Exception e) {
            return ResponseEntity.internalServerError().body(null);
        }
    }
}
