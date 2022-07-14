package com.api.credenciales.controller;

import javax.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.credenciales.security.JwtAuthRequest;
import com.api.credenciales.security.JwtAuthResponse;

@RestController
@CrossOrigin( origins = "http://localhost:8081" )
@RequestMapping( "/api/auth" )
public class AuthController {
  
  @PostMapping( "/login" )
  @ResponseStatus( HttpStatus.OK )
  public JwtAuthResponse createRole( 
      @Valid @RequestBody JwtAuthRequest request ) throws Exception {
    
    return null ;
    
  }

  
  
}