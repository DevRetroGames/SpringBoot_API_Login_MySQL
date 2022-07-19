package com.api.credenciales.serviceImpl;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.api.credenciales.exceptions.NotFoundException;
import com.api.credenciales.model.Identity;
import com.api.credenciales.model.Information;
import com.api.credenciales.repository.IIdentityRepository;
import com.api.credenciales.repository.IInformationRepository;

@Service
@Qualifier("customUserDetailsService")
public class UserDetailsServiceImpl implements UserDetailsService {
  
  
  @Autowired
  private IInformationRepository informationRepository ;
  
  @Autowired
  private IIdentityRepository identityRepository ;
  
  
  
  @Override
  public UserDetails loadUserByUsername( 
      String username ) throws UsernameNotFoundException {
    
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
    
    return new User( 
        information.getEmail() , 
        identity.getKeyword() , 
        new ArrayList<>() ) ;
    
  }
  

}
