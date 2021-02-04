package com.eftomi.tcp.service.exception;

public class CargoItemNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public CargoItemNotFoundException(int id) {
		super(String.valueOf(id));
	}
}
