package com.api.credenciales.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

@Component
public class JwtUtil {
  
  
  public boolean isNullToken( String token ) {
    
    return StringUtils.isNotBlank( token ) && 
        StringUtils.isNotEmpty( token ) ;
    
  }
  
  
  public boolean validateSecret( Jws< Claims > token ) {
    
    return token != null ;
    
  }

}
