package com.api.credenciales.util;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

@Component
public class GeneralUtil {
  
  public boolean isNull( String token ) {
    
    return StringUtils.isNotBlank( token ) && 
        StringUtils.isNotEmpty( token ) ;
    
  }
  
  
}
