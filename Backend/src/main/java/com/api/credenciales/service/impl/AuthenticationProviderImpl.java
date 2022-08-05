package com.api.credenciales.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import com.api.credenciales.exceptions.NotFoundException;
import com.api.credenciales.model.Identity;
import com.api.credenciales.model.Information;
import com.api.credenciales.repository.IIdentityRepository;
import com.api.credenciales.repository.IInformationRepository;

@Component
public class AuthenticationProviderImpl implements AuthenticationProvider {
  
  
  @Autowired
  private IInformationRepository informationRepository ;
  
  @Autowired
  private IIdentityRepository identityRepository ;
  
  @Autowired
  @Lazy
  private PasswordEncoder passwordEncoder ;
  
  
  
  @Override
  public Authentication authenticate( 
      Authentication authentication ) throws AuthenticationException {
    
    String username = authentication.getName() ;
    String password = authentication.getCredentials().toString() ;
    
    Information information =
        this.informationRepository
            .findByEmail( username )
            .orElseThrow( () -> new NotFoundException( "User" , "email" , username ) )
            ;
    
    Identity identity =
        this.identityRepository
            .findByInformation( information )
            .orElseThrow( () -> new UsernameNotFoundException( "User not found." ) )
            ;
    
    if( this.passwordEncoder.matches( password , identity.getKeyword() ) ) {
      
      List< GrantedAuthority > authorities = new ArrayList<>() ;
      
      identity
        .getListRoles()
        .forEach( role -> {
          authorities
            .add( 
                new SimpleGrantedAuthority( 
                    role.getName() ) ) ; })
        ;
      
      
      return new UsernamePasswordAuthenticationToken(
          username , 
          password , 
          authorities ) ;
      
    } else {
      
      throw new BadCredentialsException( "Invalid credentials." ) ;
      
    }
    
    
  }
  
  
  
  @Override
  public boolean supports( Class<?> authentication ) {
    
    return authentication.equals( UsernamePasswordAuthenticationToken.class ) ;
    
  }
  
  
}
