package com.censo21.exceptions;

public class Censo21Exceptions extends Exception {

	private static final long serialVersionUID = 1L;
	private String mensaje;
	
	public Censo21Exceptions(String mensaje) {
		this.mensaje = mensaje;
	}
	
	@Override
	public String getMessage() {
		return mensaje;
	}
}
