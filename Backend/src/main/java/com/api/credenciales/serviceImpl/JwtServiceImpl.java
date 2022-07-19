package com.api.credenciales.serviceImpl;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.api.credenciales.service.IJwtService;

@Service
@Qualifier( "JwtServiceImpl" )
public class JwtServiceImpl implements IJwtService {
  
  
}
