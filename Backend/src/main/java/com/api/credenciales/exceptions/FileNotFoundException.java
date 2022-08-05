package com.api.credenciales.exceptions;

import org.springframework.stereotype.Component;

import lombok.NoArgsConstructor;

@SuppressWarnings("serial")
@Component
@NoArgsConstructor
public class FileNotFoundException extends RuntimeException {
  
  public FileNotFoundException( String message ) {
    super( message ) ;
  }

}
