package com.api.credenciales.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
public interface IJwtService {
  
  public String generateToken( UserDetails userDetails ) ;
  
  public boolean isValidToken( String token ) ;
  
  public String getUsernameFromToken( String token ) ;
  
  public boolean usernameNotNull( String username ) ;
  
  public boolean validateToken( String token , UserDetails userDetails ) ;
  
  public void setAuthenticationToken( UserDetails userDetails , HttpServletRequest request ) ;
  
}
