package com.api.credenciales.exceptions;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Component
@NoArgsConstructor
public class CustomUnsupportedMediaType extends RuntimeException {
  
  public CustomUnsupportedMediaType( String message ) {
    super( message ) ;
  }
  
}
