package com.api.credenciales.exceptions;

import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@NoArgsConstructor
public class BadRequestException extends RuntimeException {

	public BadRequestException( String message ) {
		super( message ) ;
	}
	
}
