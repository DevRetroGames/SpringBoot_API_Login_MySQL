package com.api.credenciales.helper;

import org.springframework.stereotype.Component;

@Component
public class JwtHelper {
  
  
  long tokenValidity = 5 * 60 * 60 ;
  String secret = "secret" ;
  
  
}
