package com.api.credenciales.exceptions;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Component
@NoArgsConstructor
public class CustomNotFoundException extends RuntimeException {
  
  public CustomNotFoundException( String message ) {
    super( message ) ;
  }
  
}
