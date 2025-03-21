package com.slotmanager.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Entity
@Getter
@Setter
@Table(name = "recaudos")
public class Recaudo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "maquina_id", nullable = false)
    private Maquina maquina;

    @Temporal(TemporalType.DATE)
    @Column(nullable = false)
    private Date fecha;

    @Column(nullable = false)
    private Double contadorIn;

    @Column(nullable = false)
    private Double contadorOut;

    @Column(nullable = false)
    private Double gananciaSocio1;

    @Transient
    private Double totalRecaudado; // Campo calculado, no se almacena en la BD

    // Constructor vacío (necesario para JPA)
    public Recaudo() {}

    // Constructor con parámetros
    public Recaudo(Maquina maquina, Date fecha, Double contadorIn, Double contadorOut, Double gananciaSocio1) {
        this.maquina = maquina;
        this.fecha = fecha;
        this.contadorIn = contadorIn;
        this.contadorOut = contadorOut;
        this.gananciaSocio1 = gananciaSocio1;
        calcularTotalRecaudado();
    }

    // Método para calcular el total recaudado
    public void calcularTotalRecaudado() {
        this.totalRecaudado = (contadorIn - contadorOut) - gananciaSocio1;
    }

    // Getters y Setters adicionales
    public Double getTotalRecaudado() {
        return (contadorIn - contadorOut) - gananciaSocio1;
    }

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

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Double getContadorIn() {
		return contadorIn;
	}

	public void setContadorIn(Double contadorIn) {
		this.contadorIn = contadorIn;
	}

	public Double getContadorOut() {
		return contadorOut;
	}

	public void setContadorOut(Double contadorOut) {
		this.contadorOut = contadorOut;
	}

	public Double getGananciaSocio1() {
		return gananciaSocio1;
	}

	public void setGananciaSocio1(Double gananciaSocio1) {
		this.gananciaSocio1 = gananciaSocio1;
	}

	public void setTotalRecaudado(Double totalRecaudado) {
		this.totalRecaudado = totalRecaudado;
	}
    
    
}
