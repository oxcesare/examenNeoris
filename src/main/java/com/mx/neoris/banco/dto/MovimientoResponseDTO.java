package com.mx.neoris.banco.dto;

import java.math.BigDecimal;

public class MovimientoResponseDTO {
	
	
	private String mensaje;
	
	private Long numCuenta;
	
	private BigDecimal saldoDisponible;
	
	public MovimientoResponseDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public String getMensaje() {
		return mensaje;
	}
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	public Long getNumCuenta() {
		return numCuenta;
	}
	public void setNumCuenta(Long numCuenta) {
		this.numCuenta = numCuenta;
	}
	public BigDecimal getSaldoDisponible() {
		return saldoDisponible;
	}
	public void setSaldoDisponible(BigDecimal saldoDisponible) {
		this.saldoDisponible = saldoDisponible;
	}

}
