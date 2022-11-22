package com.mx.neoris.banco.exception;

public class TransaccionNoEncontrada extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public TransaccionNoEncontrada(String message) {
        super(message);
    }
}
