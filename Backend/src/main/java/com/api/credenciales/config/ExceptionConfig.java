package com.api.credenciales.config;

import java.util.HashMap;
import java.util.Map;

import org.apache.tomcat.util.http.fileupload.impl.FileSizeLimitExceededException;
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
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.multipart.MultipartException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;

import com.api.credenciales.exceptions.CustomNotFoundException;
import com.api.credenciales.exceptions.CustomServerException;
import com.api.credenciales.exceptions.CustomUnsupportedMediaType;
import com.api.credenciales.exceptions.FileNotFoundException;

@ControllerAdvice( annotations = RestController.class )
public class ExceptionConfig {
  
  
  private static final String CAUSE = "cause" ;
	
	
	
	@ExceptionHandler({ 
	  MethodArgumentNotValidException.class 
	})
	public ResponseEntity< Map< String , Object > > valid( 
	    MethodArgumentNotValidException ex ) {
		
		Map< String , Object > resp = new HashMap<>() ;
		
		ex
		  .getBindingResult()
		  .getAllErrors()
		  .forEach( 
		    error -> {
			
    			String fieldName = ( (FieldError) error ).getField() ;
    			String message = error.getDefaultMessage() ;
    			
    			resp.put( fieldName , message ) ;
    			resp.put( "code" , HttpStatus.BAD_REQUEST.value() ) ;
			
		    }) 
		  ;
		
		return new ResponseEntity<>( resp , HttpStatus.BAD_REQUEST ) ;
		
	}
	
	
	
	@ExceptionHandler({ 
		MissingServletRequestParameterException.class ,
    UnsatisfiedServletRequestParameterException.class ,
    HttpRequestMethodNotSupportedException.class ,
    ServletRequestBindingException.class ,
    HttpMessageNotReadableException.class ,
    MissingServletRequestPartException.class ,
    MultipartException.class
	})
	@ResponseStatus( value = HttpStatus.BAD_REQUEST )
	public @ResponseBody Map<String, Object> handleRequestException( Exception ex ) {
		
	    Map< String , Object >  map = new HashMap<>() ;
	    
	    map.put( CAUSE , ex.getMessage() ) ;
	    map.put( "code" , HttpStatus.BAD_REQUEST.value() ) ;
	    
	    return map ;
	    
	}
	
	
	
	@ExceptionHandler({
	  MaxUploadSizeExceededException.class ,
	  FileSizeLimitExceededException.class ,
	  IllegalStateException.class ,
	  CustomServerException.class
  })
  @ResponseStatus( value = HttpStatus.INTERNAL_SERVER_ERROR )
  public @ResponseBody Map<String, Object> handleMaxUploadSizeExceeded( Exception ex ) {
    
      Map< String , Object >  map = new HashMap<>() ;
      
      map.put( CAUSE , ex.getMessage() ) ;
      map.put( "code" , HttpStatus.INTERNAL_SERVER_ERROR.value() ) ;
      
      return map ;
      
  }
	
	
	
	@ExceptionHandler({ 
	  FileNotFoundException.class ,
	  CustomNotFoundException.class 
  })
  @ResponseStatus( value = HttpStatus.NOT_FOUND )
  public @ResponseBody Map< String , Object > handleFileNotFoundException( Exception e ) {
    
      Map< String , Object >  map = new HashMap<>() ;
      
      map.put( CAUSE , e.getMessage() ) ;
      map.put( "code" , HttpStatus.NOT_FOUND.value() ) ;
      
      return map ;
      
  }
	
	
	
	@ExceptionHandler({ 
	  CustomUnsupportedMediaType.class 
  })
  @ResponseStatus( value = HttpStatus.UNSUPPORTED_MEDIA_TYPE )
  public @ResponseBody Map< String , Object > handleCustomUnsupportedMediaType( Exception e ) {
    
      Map< String , Object >  map = new HashMap<>() ;
      
      map.put( CAUSE , e.getMessage() ) ;
      map.put( "code" , HttpStatus.UNSUPPORTED_MEDIA_TYPE.value() ) ;
      
      return map ;
      
  }
	
  
}