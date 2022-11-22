package com.mx.neoris.banco.exception;

public class CuentaNoExistente extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CuentaNoExistente(String mensaje) {
		super(mensaje);
	}

}
