package com.api.credenciales.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import com.api.credenciales.helper.JwtHelper;
import com.api.credenciales.service.IJwtService;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import lombok.extern.log4j.Log4j2;

@Service
@Qualifier( "JwtServiceImpl" )
@Log4j2
public class JwtServiceImpl implements IJwtService {
  
  
  @Autowired
  private JwtHelper jwtHelper ;
  
  
  
  @Override
  public String generateToken( UserDetails userDetails ) {
    return this.jwtHelper.generateToken( userDetails ) ;
  }
  
  
  
  @Override
  public boolean isValidToken( String token ) {
    
    return 
      this.isFormat( token ) &&
      this.isBearer( token )
      ;
    
  }
  
  
  
  private boolean isFormat( String token ) {
    return this.jwtHelper.isFormat( token ) ;
  }
  
  
  
  private boolean isBearer( String token ) {
    return this.jwtHelper.isBearer( token ) ;
  }
  
  
  
  @Override
  public String getUsernameFromToken( String token ) {
    
    String username =  null ;
    
    try {
      username = this.jwtHelper.getUsernameFromToken( token ) ;
    } catch( IllegalArgumentException e ) {
      log.warn( "Unable to get JWT token." ) ;
    } catch( ExpiredJwtException e ) {
      log.warn( "JWT token has expired." ) ;
    } catch( MalformedJwtException e ) {
      log.warn( "Invalid JWT." ) ;
    }
    
    return username ;
    
  }
  
  
  
  @Override
  public boolean usernameNotNull( String username ) {
    
    return
      username != null &&
      SecurityContextHolder
        .getContext()
        .getAuthentication() == null
        ;
    
  }
  
  
  
  @Override
  public boolean validateToken( String token , UserDetails userDetails ) {
    return this.jwtHelper.validateToken( token , userDetails ) ;
  }
  
  
  
  @Override
  public void setAuthenticationToken( 
      UserDetails userDetails , 
      HttpServletRequest request ) {
    
    UsernamePasswordAuthenticationToken 
      usernamePasswordAuthenticationToken = 
        new UsernamePasswordAuthenticationToken( 
            userDetails , 
            null , 
            userDetails.getAuthorities() ) ; 
    
    usernamePasswordAuthenticationToken
      .setDetails( 
          new WebAuthenticationDetailsSource()
            .buildDetails( request ) ) 
      ;
    
    SecurityContextHolder
      .getContext()
      .setAuthentication( 
        usernamePasswordAuthenticationToken ) 
      ;
    
  }
  
  
}