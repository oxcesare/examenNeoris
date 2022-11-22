package com.mx.neoris.banco.exception;

public class SaldoNoDisponible extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public SaldoNoDisponible(String mensaje) {
		super(mensaje);
	}

}
