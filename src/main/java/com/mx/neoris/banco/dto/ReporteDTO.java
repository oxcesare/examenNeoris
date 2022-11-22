package com.mx.neoris.banco.dto;

public class ReporteDTO {

	private String fechaInicial;
	private String fechaFinal;
	private Long numCuenta;
	
	public ReporteDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getFechaInicial() {
		return fechaInicial;
	}

	public void setFechaInicial(String fechaInicial) {
		this.fechaInicial = fechaInicial;
	}

	public String getFechaFinal() {
		return fechaFinal;
	}

	public void setFechaFinal(String fechaFinal) {
		this.fechaFinal = fechaFinal;
	}

	public Long getNumCuenta() {
		return numCuenta;
	}

	public void setNumCuenta(Long numCuenta) {
		this.numCuenta = numCuenta;
	}
	
	

}
