package com.eftomi.tcp.service.exception;

public class ItemNotFoundException extends RuntimeException{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public ItemNotFoundException(int id) {
		super(String.valueOf(id));
	}
}
