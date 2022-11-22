package com.mx.neoris.banco.dto;

public class MovimientoResponseErrorDTO {

	private String mensaje;
	private Long numCuenta;

	public MovimientoResponseErrorDTO() {
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

}
