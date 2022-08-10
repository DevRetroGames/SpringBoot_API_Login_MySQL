package com.api.credenciales.helper;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import com.api.credenciales.util.JwtUtil;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;

@Component
public class JwtHelper {
  
  
  @Autowired
  private JwtUtil jwtUtil ;
  
  
  private static final String USER = "USER" ;
  private static final String ROLES = "ROLES" ;
  private static final String ISSUER = "CREDENTIALS" ;
  private static final String BEARER = "Bearer " ;
  
  @Value( "${jwt.expired}" )
  private long jwtExpired ;
  
  @Value( "${jwt.secret}" )
  private String jwtSecret ;
  
  
  
  public String generateToken( UserDetails userDetails ) {
    
    List< String > roles = new ArrayList<>() ; 
    userDetails
      .getAuthorities()
      .stream()
      .forEach( role -> roles.add( role.getAuthority() ) )
      ;
    
    return JWT
        .create()
        .withIssuer( ISSUER )
        .withIssuedAt( new Date() )
        .withNotBefore( new Date() )
        .withExpiresAt( new Date( System.currentTimeMillis() + this.jwtExpired ) )
        .withClaim( USER , userDetails.getUsername() )
        .withArrayClaim( ROLES , roles.toArray( new String[0] ) )
        .sign( Algorithm.HMAC512( this.jwtSecret ) )
        ;
    
  }
  
  
  
  public boolean isFormat( String token ) {  
    
    return this.jwtUtil.isNullToken( token ) && 
        token.split( "\\." ).length == 3 ;
    
  }
  
  
  
  public boolean isBearer( String token ) {
    
    return this.jwtUtil.isNullToken( token ) &&  
        token.startsWith( BEARER ) ;
    
  }
  
  
  
  public boolean validateToken( String token , UserDetails userDetails ) {
    
    final String username = this.getUsernameFromToken( token ) ;
    
    return ( 
        username.equals( userDetails.getUsername() ) && 
        !this.isTokenExpired( token ) ) ;
    
  }
  
  
  
  private boolean isTokenExpired( String token ) {
    
    final Date expiration = 
        this.getExpirationDateFromToken( token ) ;
    
    return expiration.before( new Date() ) ;
    
  }
  
  
  
  private Date getExpirationDateFromToken( String token ) {
    
    return this.verify( token )
        .getExpiresAt()
        ;
    
  }
  
  
  
  public String getUsernameFromToken( String token ) {
    
    return this.verify( token )
        .getClaim( USER )
        .asString() 
        ;
    
  }
  
  
  
  public List< String > getRolesFromToken( String token ) {
    
    return Arrays
        .asList( 
            this.verify( token )
                .getClaim( ROLES )
                .asArray( String.class )
          ) 
        ;
    
  }
  
  
  
  private DecodedJWT verify( String token ) {
    
    return JWT
        .require( Algorithm.HMAC512( this.jwtSecret ) )
        .withIssuer( ISSUER )
        .build()
        .verify( token.substring( BEARER.length() ) )
        ;
    
  }
  
  
}