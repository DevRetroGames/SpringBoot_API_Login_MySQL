package com.api.credenciales.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.api.credenciales.service.IJwtService;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class JwtAuthenticationFilter extends OncePerRequestFilter {
  
  
  @Autowired
  @Qualifier( "UserDetailsServiceImpl" )
  private UserDetailsService userDetailsService ;
  
  @Autowired
  @Qualifier( "JwtServiceImpl" )
  private IJwtService jwtService ;
  
  
  
  @Override
  protected void doFilterInternal( 
      HttpServletRequest request , 
      HttpServletResponse response , 
      FilterChain filterChain 
    ) throws ServletException , IOException {
    
    String requestToken = request.getHeader( "Authorization" ) ;
    String username = null ;
    
    
    
    if( this.jwtService.isValidToken( requestToken ) ) {
      
      username = 
        this.jwtService
            .getUsernameFromToken( requestToken ) 
            ;
      
    } else {
      log.warn( "Token not valid." ) ;
      // here throw
    }
    
    
    
    if( this.jwtService.usernameNotNull( username ) ) {
      
      UserDetails userDetails = 
          this.userDetailsService
              .loadUserByUsername( username ) ;
      
      
      
      if( this.jwtService.validateToken( requestToken , userDetails ) ) {
        
        this.jwtService.setAuthenticationToken( userDetails , request ) ;
        
      } else {
        log.warn( "Invalid JWT token." ) ;
        // here throw
      }
      
      
      
    } else {
      log.warn( "Username is null or context is not null." ) ;
      // here throw
    }
    
    
    
    filterChain.doFilter( request , response ) ;
    
    
  }
  
  
}