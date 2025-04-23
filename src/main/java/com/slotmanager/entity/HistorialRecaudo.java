package com.slotmanager.entity;

import jakarta.persistence.*;
//import lombok.Getter;
//import lombok.Setter;
import java.util.Date;

@Entity
//@Getter
//@Setter
@Table(name = "historial_recaudos")
public class HistorialRecaudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "maquina_id", nullable = false)
    private Maquina maquina;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaInicio;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fechaFin;

    @Column(nullable = false)
    private Double totalRecaudado;

    @Column(nullable = false)
    private Double gananciaSocio1;

    @Column(nullable = false)
    private Double gananciaSocio2;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Maquina getMaquina() {
		return maquina;
	}

	public void setMaquina(Maquina maquina) {
		this.maquina = maquina;
	}

	public Date getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Date fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public Date getFechaFin() {
		return fechaFin;
	}

	public void setFechaFin(Date fechaFin) {
		this.fechaFin = fechaFin;
	}

	public Double getTotalRecaudado() {
		return totalRecaudado;
	}

	public void setTotalRecaudado(Double totalRecaudado) {
		this.totalRecaudado = totalRecaudado;
	}

	public Double getGananciaSocio1() {
		return gananciaSocio1;
	}

	public void setGananciaSocio1(Double gananciaSocio1) {
		this.gananciaSocio1 = gananciaSocio1;
	}

	public Double getGananciaSocio2() {
		return gananciaSocio2;
	}

	public void setGananciaSocio2(Double gananciaSocio2) {
		this.gananciaSocio2 = gananciaSocio2;
	}
    
    
    
}
