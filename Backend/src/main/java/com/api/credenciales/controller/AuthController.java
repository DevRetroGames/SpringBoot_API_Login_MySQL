package com.api.credenciales.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
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
  
  
  @Autowired
  private AuthenticationManager authenticationManager ;
  
  
  @PostMapping( "/login" )
  @ResponseStatus( HttpStatus.OK )
  public JwtAuthResponse createRole( 
      @Valid @RequestBody JwtAuthRequest request ) throws Exception {
    
     Authentication auth ;
    
    try {
      
      auth = this.authenticationManager
          .authenticate( 
              new UsernamePasswordAuthenticationToken( 
                  request.getUsername() , 
                  request.getPassword() ) ) 
          ;
      
      SecurityContextHolder
        .getContext()
        .setAuthentication( auth ) 
        ;
      
    } catch( BadCredentialsException e ) {
      throw new BadCredentialsException( "Credentials invalid." ) ;
    }
    
    return null ;
    
  }
  
  
}