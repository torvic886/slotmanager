package com.slotmanager.dto;

import lombok.Getter;
import lombok.Setter;
import java.util.Date;

@Getter
@Setter
public class RecaudoResponse {
    private Long id;
    private Long maquinaId;
    private String maquinaNombre;
    private String estado;
    private Date fecha;
    private Double contadorIn;
    private Double contadorOut;
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public Long getMaquinaId() {
		return maquinaId;
	}
	public void setMaquinaId(Long maquinaId) {
		this.maquinaId = maquinaId;
	}
	public String getMaquinaNombre() {
		return maquinaNombre;
	}
	public void setMaquinaNombre(String maquinaNombre) {
		this.maquinaNombre = maquinaNombre;
	}
	public String getEstado() {
		return estado;
	}
	public void setEstado(String estado) {
		this.estado = estado;
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
    
    
    
}

