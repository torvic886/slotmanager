package com.slotmanager.entity;

import jakarta.persistence.*;
import java.util.List;  // 🔥 IMPORTANTE: Agregar esta línea
import jakarta.persistence.*;


@Entity
@Table(name = "maquinas")
public class Maquina {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nombre;
    private String fechaInstalacion;
    private String estado;
    
    @OneToMany(mappedBy = "maquina", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    private List<Recaudo> recaudos;  // 🔥 Evita cargas innecesarias de recaudos en la máquina
    

    // Métodos Getters
    public Long getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFechaInstalacion() {
        return fechaInstalacion;
    }

    public String getEstado() {
        return estado;
    }

    // Métodos Setters
    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setFechaInstalacion(String fechaInstalacion) {
        this.fechaInstalacion = fechaInstalacion;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}


