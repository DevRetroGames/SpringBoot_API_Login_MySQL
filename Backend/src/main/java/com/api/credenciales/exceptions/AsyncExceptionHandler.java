package com.api.credenciales.exceptions;

import java.lang.reflect.Method;

import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.stereotype.Component;

import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
public class AsyncExceptionHandler implements AsyncUncaughtExceptionHandler  {
	
	@Override
	public void handleUncaughtException( Throwable ex , Method method , Object... params ) {
		
		log.info( "Exception message - " + ex.getMessage() ) ;
		log.info( "Method name - " + method.getName() ) ;
        
        for( Object param : params ) {
        	log.info( "Parameter value - " + param ) ;
        }
		
	}

}