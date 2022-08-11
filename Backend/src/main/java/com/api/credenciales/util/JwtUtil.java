package com.api.credenciales.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class JwtUtil {
  
  
  public boolean isNullToken( String token ) {
    
    return StringUtils.isNotBlank( token ) && 
        StringUtils.isNotEmpty( token ) ;
    
  }

}
