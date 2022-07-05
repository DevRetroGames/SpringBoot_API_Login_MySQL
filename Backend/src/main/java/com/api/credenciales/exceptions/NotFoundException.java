package com.api.credenciales.exceptions;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Component
@NoArgsConstructor
public class NotFoundException extends RuntimeException {
	
	String resourceName ;
	String fieldName ;
	Object fieldValue ;

	public NotFoundException( String resourceName , String fieldName , Object fieldValue ) {
		super( String.format( "%s not found with %s : %s" , resourceName , fieldName , fieldValue ) ) ;
		this.resourceName = resourceName ;
		this.fieldName = fieldName ;
		this.fieldValue = fieldValue ;
	}
	
}
