package com.slotmanager.service;

import com.slotmanager.entity.Recaudo;
import com.slotmanager.repository.RecaudoRepository;
import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.util.JRLoader;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;

import java.io.InputStream;
import java.util.*;

@Service
public class ReporteService {

    private final RecaudoRepository recaudoRepository;

    public ReporteService(RecaudoRepository recaudoRepository) {
        this.recaudoRepository = recaudoRepository;
    }

    public byte[] generarReportePDF(Date fechaInicio, Date fechaFin, Long maquinaId) throws Exception {
        InputStream reportStream = new ClassPathResource("reports/reporte_recaudos.jasper").getInputStream();

        // Cargar el reporte compilado
        JasperReport jasperReport = (JasperReport) JRLoader.loadObject(reportStream);

        // Obtener datos desde la BD según los parámetros de filtro
        List<Recaudo> recaudos;
        if (maquinaId != null) {
            recaudos = recaudoRepository.findByMaquinaId(maquinaId);
        } else if (fechaInicio != null && fechaFin != null) {
            recaudos = recaudoRepository.findByFechaBetween(fechaInicio, fechaFin);
        } else {
            recaudos = recaudoRepository.findAll(); // Si no hay filtros, traer todos
        }

        // Convertir los datos a un formato compatible con JasperReports
        JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(recaudos);

        // Parámetros para el reporte
        Map<String, Object> parametros = new HashMap<>();
        parametros.put("titulo", "Reporte de Recaudos");
        parametros.put("fechaInicio", fechaInicio);
        parametros.put("fechaFin", fechaFin);

        // Rellenar el reporte con datos
        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, parametros, dataSource);

        // Exportar a PDF
        return JasperExportManager.exportReportToPdf(jasperPrint);
    }
}
