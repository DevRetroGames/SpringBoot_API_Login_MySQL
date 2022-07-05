package com.api.credenciales.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.UnsatisfiedServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.api.credenciales.dto.ApiResponse;
import com.api.credenciales.exceptions.NotFoundException;

@ControllerAdvice( annotations = RestController.class )
public class ExceptionConfig {

	@ExceptionHandler({ NotFoundException.class })
	public ResponseEntity<?> notFoundException( Exception e ) {
		
		String message = e.getMessage() ;
		ApiResponse apiResponse = new ApiResponse( message , false ) ;
		
		return ResponseEntity
					.status( HttpStatus.NOT_FOUND )
					.body( apiResponse ) ;
	}

	
	@ExceptionHandler( MethodArgumentNotValidException.class )
	public ResponseEntity< Map<String,String> > valid( MethodArgumentNotValidException ex ) {
		
		Map<String,String> resp = new HashMap<>() ;
		
		ex.getBindingResult().getAllErrors().forEach( (error)->{
			
			String fieldName = ( (FieldError) error ).getField() ;
			String message = error.getDefaultMessage() ;
			resp.put( fieldName , message ) ;
			
		} ) ;
		
		return new ResponseEntity<>( resp , HttpStatus.BAD_REQUEST ) ;
		
	}
	
	@ExceptionHandler({ 
		MissingServletRequestParameterException.class ,
        UnsatisfiedServletRequestParameterException.class ,
        HttpRequestMethodNotSupportedException.class ,
        ServletRequestBindingException.class ,
        HttpMessageNotReadableException.class
	})
	@ResponseStatus( value = HttpStatus.BAD_REQUEST )
	public @ResponseBody Map<String, Object> handleRequestException( Exception ex ) {
		
	    Map< String , Object >  map = new HashMap<>() ;
	    
	    map.put( "error" , "Request Error" ) ;
	    map.put( "cause" , ex.getMessage() ) ;
	    
	    return map ;
	    
	}
	/*
	@ExceptionHandler({ 
		InterruptedException.class ,
		ExecutionException.class ,
		TimeoutException.class ,
		CompletionException.class ,
		NoSuchElementException.class ,
		InvalidDataAccessApiUsageException.class
	})
	@ResponseStatus( value = HttpStatus.INTERNAL_SERVER_ERROR )
	public @ResponseBody Map< String , Object > Async( Exception ex ) {
		
		Map< String , Object > resp = new HashMap<>() ;
		
		resp.put( "Error" , "Request Error" ) ;
		resp.put( "Cause" , ex.getMessage() ) ;
		
		return resp ;
		
	}*/
	
}