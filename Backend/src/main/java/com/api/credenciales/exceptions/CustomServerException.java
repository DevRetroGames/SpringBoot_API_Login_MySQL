package com.api.credenciales.exceptions;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Component
@NoArgsConstructor
public class CustomServerException extends RuntimeException {

  public CustomServerException( String message ) {
    super( message ) ;
  }
  
}
